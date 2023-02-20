package com.microservice.gateway.filter;



import com.microservice.gateway.util.FilterUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class CustomPreFilter  implements GlobalFilter {
    private  static  final Logger logger= LoggerFactory.getLogger(CustomPreFilter.class);

    @Autowired
    private FilterUtility filterUtility;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(headers)){
            logger.debug("Banking correlation id found : {}",
                    filterUtility.getCorrelationId(headers));
        }else{
            String correlationId=generateCorrelationId();
            filterUtility.setCorrelationId(exchange,correlationId);
            logger.debug("Banking correlation generated : {}",correlationId);
        }

        return chain.filter(exchange);
    }

    private  boolean isCorrelationIdPresent(HttpHeaders requestHeader){
        if (filterUtility.getCorrelationId(requestHeader) != null) {
            return true;
        } else {
            return false;
        }
    }

    private  String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }
}
