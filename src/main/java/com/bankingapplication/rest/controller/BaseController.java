package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.service.AccountService;
import com.bankingapplication.rest.service.CustomerService;
import com.bankingapplication.rest.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;
}
