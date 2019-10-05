package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.domain.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/customer", tags = "Customer", description = "Customer API's perform all customer's related CRUD operations")
public class CustomerController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(value = "/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "Create customer. user name is mandatory.")
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping(value = "/customers")
    @ApiOperation(value = "Get all accounts")
    public Page<Customer> all(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @GetMapping(value = "/customers/{customerId}")
    @ApiOperation(value = "Find customer based on customer id")
    public Customer findByCustomerId(@PathVariable String customerId) {
        return customerService.findByCustomerId(customerId);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    @ApiOperation(value = "Delete customer based on customer id")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping(value = "/customers/{customerId}")
    @ApiOperation(value = "Update customer based on customer id")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer newCustomer) {
        return customerService.updateCustomer(customerId, newCustomer);
    }

}
