package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.repository.CustomerRepository;
import com.bankingapplication.rest.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerService {
    @Autowired CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(createdBy))
            customer.setCreatedBy(createdBy);

        return customerRepository.save(customer);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer findByCustomerId(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
    }

    public ResponseEntity<?> deleteCustomer(String customerId) {
        return customerRepository.findById(customerId).map(customer -> {
                    customerRepository.delete(customer);
                    return ResponseEntity.ok().build();
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
    }

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
