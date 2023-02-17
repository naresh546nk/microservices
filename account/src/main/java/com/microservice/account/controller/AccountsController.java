
package com.microservice.account.controller;

import com.microservice.account.model.Accounts;
import com.microservice.account.model.Customer;
import com.microservice.account.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}

	}

	@GetMapping("/accounts")
	public List<Accounts> getAllAccounts(){
		return  accountsRepository.findAll();
	}

}
