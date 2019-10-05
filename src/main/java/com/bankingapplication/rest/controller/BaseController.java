package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.service.AccountService;
import com.bankingapplication.rest.service.CustomerService;
import com.bankingapplication.rest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    CustomerService customerService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;
}
