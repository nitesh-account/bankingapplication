package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TransactionController {

    @Autowired
    TransactionService transactionService;
}
