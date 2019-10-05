package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.domain.Transaction;
import com.bankingapplication.rest.dto.TransactionDTO;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.enums.TransactionType;
import com.bankingapplication.rest.exception.LowBalanceException;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.utils.HttpUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService extends BaseService {

    @Transactional
    public ResponseEntity<TransactionDTO> save(String accountNumber, Double amount, TransactionType transactionType) {
        TransactionDTO transactionDTO = new TransactionDTO();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Customer customer = new Customer();
        if (account.isPresent()) {
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
        }

        Customer finalCustomer = customer;
        return accountRepository.findByAccountNumber(accountNumber).map(acct -> {
            Double currentBalance = acct.getBalance();
            Double balanceAfterTransaction = 0.0;
            if (transactionType.equals(TransactionType.DEPOSIT)) {
                balanceAfterTransaction = currentBalance + amount;
            } else if (transactionType.equals(TransactionType.WITHDRAW)) {
                balanceAfterTransaction = currentBalance - amount;
                if (balanceAfterTransaction < 0.0)
                    throw new LowBalanceException("After withdrawal balance [balanceAfterTransaction=" + balanceAfterTransaction + "] is negative");
            }
            acct.setBalance(balanceAfterTransaction);
            accountRepository.save(acct);

            String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);

            Transaction transaction = new Transaction(createdBy, acct, TransactionType.DEPOSIT, amount);
            Transaction savedTransaction = transactionRepository.save(transaction);

            setTransactionDTO(savedTransaction, accountNumber, transactionDTO, finalCustomer, acct, balanceAfterTransaction, amount);
            return ResponseEntity.ok(transactionDTO);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId=" + accountNumber + "] can't be found"));
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
