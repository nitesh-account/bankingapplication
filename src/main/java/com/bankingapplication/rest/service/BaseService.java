package com.bankingapplication.rest.service;

import com.bankingapplication.rest.repository.AccountRepository;
import com.bankingapplication.rest.repository.CustomerRepository;
import com.bankingapplication.rest.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;
}
