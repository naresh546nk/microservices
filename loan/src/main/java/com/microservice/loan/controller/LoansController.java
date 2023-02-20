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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("loan")
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@Autowired
	private LoansConfig loansConfig;

	private static final Logger logger= LoggerFactory.getLogger(LoansController.class);

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		logger.info("getLoansDetails() -> started ");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		logger.info("getLoansDetails() -> ended ");
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}


	@GetMapping("/properties")
	public Properties getAllProperties(){
		logger.info("getAllProperties() -> started ");
		Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(), loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		logger.info("getAllProperties() -> ended ");
		return  properties;
	}

}
