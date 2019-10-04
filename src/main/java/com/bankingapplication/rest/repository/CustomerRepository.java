package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
