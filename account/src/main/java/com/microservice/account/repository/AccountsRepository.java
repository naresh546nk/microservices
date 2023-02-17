package com.microservice.account.repository;

import com.microservice.account.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Integer> {

	Accounts findByCustomerId(int customerId);
}
