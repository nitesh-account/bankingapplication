package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * CustomerService class is used for logical implementation for customer related operations
 *
 * @author Nitesh Kumar
 */

@Service
public class CustomerService extends BaseService{
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    /**
     * Save customer data in customer table
     *
     * @param customer a valid {@link Customer} object
     * @return newly created {@link Customer} object
     */
    public Customer save(Customer customer) {
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(createdBy))
            customer.setCreatedBy(createdBy);

        return customerRepository.save(customer);
    }

    /**
     * Get all customer data
     *
     * @param pageable
     * @return {@link Page<Customer}
     */
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    /**
     * Find customer based on customer id
     *
     * @param customerId unique customer id
     * @return {@link Customer} object
     *
     * @throws if customer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public Customer findByCustomerId(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
    }

    /**
     * Delete customer based on customer id
     *
     * @param customerId a unique customer id
     * @return
     *
     * @throws if customer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public ResponseEntity<?> deleteCustomer(String customerId) {
        return customerRepository.findById(customerId).map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
    }

    /**
     * Update customer based on customer id
     *
     * @param customerId a unique customer id
     * @param newCustomer a valid {@link Customer} object
     * @return {@link Customer} object
     *
     * @throws if customer id is not found than throw {@link ResourceNotFoundException} exception.
     */
    public ResponseEntity<Customer> updateCustomer(String customerId, Customer newCustomer) {
        return customerRepository.findById(customerId).map(customer -> {
            customer.setCustomerName(newCustomer.getCustomerName());
            customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
            String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            if(!StringUtils.isEmpty(updatedBy))
                customer.setUpdatedBy(updatedBy);

            customerRepository.save(customer);
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
    }
}
