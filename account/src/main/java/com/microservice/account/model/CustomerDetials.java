package com.microservice.account.model;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetials {
    private Accounts accounts;
    private List<Loans> loans;
    private List<Cards> cards;
}
