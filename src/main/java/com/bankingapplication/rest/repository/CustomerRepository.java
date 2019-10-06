package com.bankingapplication.rest.repository;

import com.bankingapplication.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CustomerRepository is used to get customer related data from database
 *
 * @author Nitesh Kumar
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findCustomerByCreatedBy(String createdBy);

    List<Customer> findCustomerByCreatedDate(Long createdDate);
}
