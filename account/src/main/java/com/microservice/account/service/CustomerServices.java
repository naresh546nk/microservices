package com.microservice.account.service;

import com.microservice.account.model.Cards;
import com.microservice.account.model.Customer;
import com.microservice.account.model.Loans;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
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
    private String  loansBaseUrl;

    private WebClient webClient= WebClient.builder().build();

    public void  log(String str){
        System.out.println(str);
    }

    public Flux<Cards> getAllCards(Customer customer){

        log("cardBaseUrl :"+cardsBaseUrl);
        log("cardBaseUrl :"+loansBaseUrl);


        Flux<Cards> cardsFlux = webClient.post()
                .uri(cardsBaseUrl + "/card/myCards")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToFlux(Cards.class);
        return cardsFlux;
    }
    public Flux<Loans> getAllLones(Customer customer){

        Flux<Loans> LonesFlex = webClient.post()
                .uri(loansBaseUrl + "/loan/myLoans")
                .body(Mono.just(customer), Customer.class)
                .retrieve()
                .bodyToFlux(Loans.class);
        return LonesFlex;
    }

}
