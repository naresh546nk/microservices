
package com.microservice.account.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.account.config.AccountsConfig;
import com.microservice.account.model.*;
import com.microservice.account.repository.AccountsRepository;
import com.microservice.account.service.CustomerServices;
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
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@PostMapping("/customerDetails")
	public CustomerDetials getCustomerDetails(@RequestBody Customer customer){

		Accounts accountDetails = getAccountDetails(customer);
		List<Loans> loansList = customerServices.getAllLones(customer).collectList().block();
		List<Cards> cardsList = customerServices.getAllCards(customer).collectList().block();

		CustomerDetials customerDetials=new CustomerDetials();
		customerDetials.setAccounts(accountDetails);
		customerDetials.setCards(cardsList);
		customerDetials.setLoans(loansList);

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

}
