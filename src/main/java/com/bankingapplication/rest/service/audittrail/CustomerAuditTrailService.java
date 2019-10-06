package com.bankingapplication.rest.service.audittrail;

import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CustomerAuditTrailService class is used for logical implementation for customer related operations
 *
 * @author Nitesh Kumar
 */

@Service
public class CustomerAuditTrailService {
    private Logger logger = LoggerFactory.getLogger(CustomerAuditTrailService.class);

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Find customers based on customer created by
     *
     * @param createdBy user who created customer
     * @return {@link Customer} object
     *
     */
    public List<Customer> findCustomersByCreatedBy(String createdBy) {
        return customerRepository.findCustomerByCreatedBy(createdBy);
    }

    /**
     * Find customers based on customer created on
     *
     * @param createdDate user created on
     * @return {@link Customer} object
     *
     */
    public List<Customer> findCustomersByCreatedDate(Long createdDate) {
        return customerRepository.findCustomerByCreatedDate(createdDate);
    }
}
