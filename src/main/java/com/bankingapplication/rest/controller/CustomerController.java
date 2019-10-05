package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @GetMapping(value = "/customers")
    public Page<Customer> all(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @GetMapping(value = "/customers/{customerId}")
    public Customer findByCustomerId(@PathVariable String customerId) {
        return customerService.findByCustomerId(customerId);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping(value = "/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String customerId, @RequestBody Customer newCustomer) {
        return customerService.updateCustomer(customerId, newCustomer);
    }

}
