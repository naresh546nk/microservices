
package com.microservice.account.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.account.config.AccountsConfig;
import com.microservice.account.model.Accounts;
import com.microservice.account.model.Customer;
import com.microservice.account.model.Properties;
import com.microservice.account.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping("account")
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountsConfig accountsConfig;


	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

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
