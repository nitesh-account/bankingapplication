package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.domain.Transaction;
import com.bankingapplication.rest.dto.TransactionDTO;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.enums.TransactionType;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class TransactionService extends BaseService{

    public ResponseEntity<TransactionDTO> save(String accountNumber, Double amount) {
        TransactionDTO transactionDTO = new TransactionDTO();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Customer customer = new Customer();
        if(account.isPresent()){
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
        }

        Customer finalCustomer = customer;
        return accountRepository.findByAccountNumber(accountNumber).map(acct ->{
            Double currentBalance = acct.getBalance();
            Double balanceAfterDeposit = currentBalance + amount;
            acct.setBalance(balanceAfterDeposit);
            accountRepository.save(acct);

            String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);

            Transaction transaction = new Transaction(createdBy, acct, TransactionType.DEPOSIT, amount);
            Transaction savedTransaction = transactionRepository.save(transaction);

            setTransactionDTO(savedTransaction, accountNumber, transactionDTO, finalCustomer, acct, balanceAfterDeposit, amount);
            return ResponseEntity.ok(transactionDTO);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountNumber+"] can't be found"));
    }

    private void setTransactionDTO(Transaction savedTransaction, String accountNumber, TransactionDTO transactionDTO, Customer finalCustomer, Account acct, Double balanceAfterDeposit, Double amount) {
        transactionDTO.setTransactionId(savedTransaction.getId());
        transactionDTO.setCustomerId(finalCustomer.getId());
        transactionDTO.setCustomerName(finalCustomer.getCustomerName());
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setBalance(balanceAfterDeposit);
        transactionDTO.setTransactionAmount(amount);
    }
}
