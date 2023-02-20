package com.microservice.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                    .path("/account/**")
                        .filters(f->f.addResponseHeader("X-Response-time", new Date().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(p->p
                        .path("/loan/**")
                        .filters(f->f.addResponseHeader("X-Response-time", new Date().toString()))
                        .uri("lb://LOANS"))
                .route(p->p
                        .path("/card/**")
                        .filters(f->f.addResponseHeader("X-Response-time", new Date().toString()))
                        .uri("lb://CARDS"))
                .build();
    }
}
