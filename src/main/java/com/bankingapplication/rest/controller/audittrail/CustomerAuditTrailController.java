package com.bankingapplication.rest.controller.audittrail;

import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.service.audittrail.CustomerAuditTrailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CustomerAuditTrailController is used to get customer related history
 *
 * @author Nitesh Kumar
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/api/audittrail"})
@Api(value = "/audittrail", tags = "Audit Trail", description = "Banking application audit trail.")
public class CustomerAuditTrailController{
    private Logger logger = LoggerFactory.getLogger(CustomerAuditTrailController.class);

    @Autowired
    CustomerAuditTrailService customerAuditTrailService;

    @GetMapping(value = "/findCustomersByCreatedBy/{createdBy}")
    @ApiOperation(value = "Find all customers based on created by user")
    public List<Customer> findCustomersByCreatedBy(@PathVariable("createdBy") String createdBy) {
        return customerAuditTrailService.findCustomersByCreatedBy(createdBy);
    }

    @GetMapping(value = "/findCustomersByCreatedDate/{createdDate}")
    @ApiOperation(value = "Find all customers based on created date")
    public List<Customer> findCustomersByCreatedBy(@PathVariable("createdDate") Long createdDate) {
        return customerAuditTrailService.findCustomersByCreatedDate(createdDate);
    }
}
