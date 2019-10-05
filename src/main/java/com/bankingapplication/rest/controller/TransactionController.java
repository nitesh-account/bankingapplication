package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.dto.TransactionDTO;
import com.bankingapplication.rest.enums.TransactionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
public class TransactionController extends BaseController {

    @PostMapping("/deposit/{amount}")
    public ResponseEntity<TransactionDTO> deposit(@RequestParam String accountNumber,
                                                  @RequestParam("amount") Double amount) throws Exception {
        return transactionService.save(accountNumber, amount, TransactionType.DEPOSIT);
    }

    @PostMapping("/withdraw/{amount}")
    public ResponseEntity<TransactionDTO> withdraw(@RequestParam String accountNumber,
                                                   @RequestParam("amount") Double amount) throws Exception {
        return transactionService.save(accountNumber, amount, TransactionType.WITHDRAW);
    }

}
