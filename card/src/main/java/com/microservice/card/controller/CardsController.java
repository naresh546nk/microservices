/**
 * 
 */
package com.microservice.card.controller;

import java.util.List;

import com.microservice.card.config.CardsConfig;
import com.microservice.card.model.Cards;
import com.microservice.card.model.Customer;
import com.microservice.card.model.Properties;
import com.microservice.card.repository.CardsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("card")
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@Autowired
	private CardsConfig cardsConfig;

	private  static  final Logger logger=LoggerFactory.getLogger(CardsController.class);

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		logger.info("getCardDetails() -> started ");
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		logger.info("getCardDetails() -> ended ");
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

	@GetMapping("/properties")
	public Properties getAllProperties(){
		logger.info("getAllProperties() -> started ");
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(), cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		logger.info("getAllProperties() -> ended ");
		return  properties;
	}


}
