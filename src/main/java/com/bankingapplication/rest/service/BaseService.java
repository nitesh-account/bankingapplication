package com.bankingapplication.rest.service;

import com.bankingapplication.rest.repository.AccountRepository;
import com.bankingapplication.rest.repository.CustomerRepository;
import com.bankingapplication.rest.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    private Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;
}
