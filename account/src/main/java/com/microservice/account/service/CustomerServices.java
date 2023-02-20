package com.microservice.account.service;

import com.microservice.account.model.Cards;
import com.microservice.account.model.Customer;
import com.microservice.account.model.Loans;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Service
public class CustomerServices {
    @Value("${cards.baseUrl}")
    private String cardsBaseUrl;
    @Value("${loans.baseUrl}")
    private String loansBaseUrl;

    private  static final Logger logger= LoggerFactory.getLogger(CustomerServices.class);

    @Autowired
    private WebClient webClient;

    public Flux<Cards> getAllCards(Customer customer){
        logger.info("getAllCards() -->Started .");
        Flux<Cards> cardsFlux = webClient.post()
                .uri(cardsBaseUrl + "/card/myCards")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToFlux(Cards.class);
        logger.info("getAllCards() -->ended .");
        return cardsFlux;
    }
    public Flux<Loans> getAllLones(Customer customer){
        logger.info("getAllLones() -->Started .");
        Flux<Loans> LonesFlex = webClient.post()
                .uri(loansBaseUrl + "/loan/myLoans")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToFlux(Loans.class);
        logger.info("getAllLones() -->ended .");
        return LonesFlex;
    }

}
