/**
 * 
 */
package com.microservice.loan.controller;

import java.util.List;

import com.microservice.loan.config.LoansConfig;
import com.microservice.loan.model.Customer;
import com.microservice.loan.model.Loans;
import com.microservice.loan.model.Properties;
import com.microservice.loan.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@Autowired
	private LoansConfig loansConfig;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}


	@GetMapping("loan/properties")
	public Properties getAllProperties(){
		return  new Properties(loansConfig.getMsg(),loansConfig.getBuildVersion(),loansConfig.getMailDetails(),loansConfig.getActiveBranches());

	}

}
