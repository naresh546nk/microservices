
package com.microservice.account.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.account.config.AccountsConfig;
import com.microservice.account.model.*;
import com.microservice.account.repository.AccountsRepository;
import com.microservice.account.service.CustomerServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("account")
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountsConfig accountsConfig;

	@Autowired
	private CustomerServices customerServices;

	@PostMapping("/myAccount")
	@Timed(value = "getAccountDetails.time", description = "Time taken to return account details")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@PostMapping("/customerDetails")
	@CircuitBreaker(name = "customerDetailsCircuit", fallbackMethod = "customerDetialsCircuitFallback")
	public CustomerDetials getCustomerDetails(@RequestHeader("banking-correlation-id") String correlationId ,  @RequestBody Customer customer){
		Accounts accountDetails = getAccountDetails(customer);
		List<Loans> loansList = customerServices.getAllLones(customer).collectList().block();
		List<Cards> cardsList = customerServices.getAllCards(customer).collectList().block();

		CustomerDetials customerDetials=new CustomerDetials();
		customerDetials.setAccounts(accountDetails);
		customerDetials.setCards(cardsList);
		customerDetials.setLoans(loansList);
		return  customerDetials;
	}

	private  CustomerDetials customerDetialsCircuitFallback(@RequestHeader("banking-correlation-id") String correlationId ,  Customer customer,Throwable throwable){
		CustomerDetials customerDetials=new CustomerDetials();
		Accounts accountDetails = getAccountDetails(customer);
		customerDetials.setAccounts(accountDetails);
		try{
			List<Loans> loansList = customerServices.getAllLones(customer).collectList().block();
			customerDetials.setLoans(loansList);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		try{
			List<Cards> cardsList = customerServices.getAllCards(customer).collectList().block();
			customerDetials.setCards(cardsList);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		return  customerDetials;
	}

	@GetMapping("/properties")
	public  Properties getPropertiesDetails() throws JsonProcessingException {
		Properties properties=new Properties(accountsConfig.getMsg(),accountsConfig.getBuildVersion(),accountsConfig.getMailDetails(),accountsConfig.getActiveBranches());
		return  properties;
	}

	@GetMapping("/")
	public List<Accounts> getAllAccounts(){
		return  accountsRepository.findAll();
	}


	@GetMapping("/hello")
	@RateLimiter(name = "helloRateLimiter",fallbackMethod ="helloRateLimiterFallback" )
	public String getHello(){
		return  "Hi All, This is the api to test RateLimiter you can only call this api twice in  20 second";
	}

	private  String helloRateLimiterFallback(Throwable throwable){
		return "You can not call this api more then twice in 20 second due to high cpu utilization";
	}

}
