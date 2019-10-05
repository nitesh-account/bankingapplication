package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.dto.TransactionDTO;
import com.bankingapplication.rest.enums.TransactionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TransactionController is used to perform deposit and withdraw operations.
 *
 * @author Nitesh Kumar
 */

@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/transaction", tags = "Transaction", description = "Transaction API's perform deposit and withdraw operation")
public class TransactionController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/deposit/{amount}")
    @ApiOperation(value = "Deposit operation. user name is mandatory.")
    public ResponseEntity<TransactionDTO> deposit(@RequestParam String accountNumber,
                                                  @RequestParam("amount") Double amount) throws Exception {
        return transactionService.save(accountNumber, amount, TransactionType.DEPOSIT);
    }

    @PostMapping("/withdraw/{amount}")
    @ApiOperation(value = "Withdraw operation. user name is mandatory.")
    public ResponseEntity<TransactionDTO> withdraw(@RequestParam String accountNumber,
                                                   @RequestParam("amount") Double amount) throws Exception {
        return transactionService.save(accountNumber, amount, TransactionType.WITHDRAW);
    }

}
