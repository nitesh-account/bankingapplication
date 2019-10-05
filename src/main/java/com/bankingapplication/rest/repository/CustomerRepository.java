package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CustomerRepository is used to get customer related data from database
 *
 * @author Nitesh Kumar
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
