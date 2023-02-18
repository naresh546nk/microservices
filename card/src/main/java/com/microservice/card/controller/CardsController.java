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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@Autowired
	private CardsConfig cardsConfig;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

	@GetMapping("card/properties")
	public Properties getAllProperties(){
		return  new Properties(cardsConfig.getMsg(),cardsConfig.getBuildVersion(),cardsConfig.getMailDetails(),cardsConfig.getActiveBranches());

	}


}
