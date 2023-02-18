package com.microservice.account.config;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "accounts")
@ToString
@Setter
@Getter
@Data
public class AccountsConfig {
    private  String msg;
    private  String buildVersion;
    private Map<String,String> mailDetails;
    private List<String> activeBranches;

}
