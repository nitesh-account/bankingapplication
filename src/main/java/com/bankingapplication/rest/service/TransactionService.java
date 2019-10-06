package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.domain.Transaction;
import com.bankingapplication.rest.domain.dtos.TransactionDTO;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.enums.TransactionType;
import com.bankingapplication.rest.exception.AccountCloseException;
import com.bankingapplication.rest.exception.LowBalanceException;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * TransactionService class is used for logical implementation for transaction related operations
 *
 * @author Nitesh Kumar
 */

@Service
public class TransactionService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(TransactionService.class);

    /**
     * Save transaction data and update account balance, if account number is not found than will throw {@link ResourceNotFoundException}
     * if balance becomes negative than will throw {@link LowBalanceException}
     *
     * @param accountNumber a unique account id
     * @param amount transaction amount
     * @param transactionType {@link TransactionType} enum
     * @return {@link TransactionDTO} object
     */
    @Transactional
    public ResponseEntity<TransactionDTO> save(String accountNumber, Double amount, TransactionType transactionType) {
        TransactionDTO transactionDTO = new TransactionDTO();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Customer customer = new Customer();
        customer = getCustomer(account, customer);

        Customer finalCustomer = customer;
        return accountRepository.findByAccountNumber(accountNumber).map(acct -> {
            if(acct.getAccountStatus().equalsIgnoreCase("Closed"))
                throw new AccountCloseException("Account [Account number=" +acct.getAccountNumber()+ "] is closed");
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

            Transaction transaction = new Transaction(createdBy, acct, transactionType, amount);
            Transaction savedTransaction = transactionRepository.save(transaction);

            setTransactionDTO(savedTransaction, accountNumber, transactionDTO, finalCustomer, acct, balanceAfterTransaction, amount);
            return ResponseEntity.ok(transactionDTO);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountNumber=" + accountNumber + "] can't be found"));
    }

    /**
     * Get customer information, if customer id is not present than will throw {@link ResourceNotFoundException} exception
     *
     * @param account a valid {@link Account} object
     * @param customer a valid {@link Customer} object
     * @return {@link Customer} object
     */
    private Customer getCustomer(Optional<Account> account, Customer customer) {
        if (account.isPresent()) {
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
        }
        return customer;
    }

    /**
     *
     * @param savedTransaction a valid {@link Transaction} object
     * @param accountNumber a unique account id
     * @param transactionDTO a valid {@link TransactionDTO} object
     * @param finalCustomer a valid {@link Customer} object
     * @param acct a valid {@link Account} object
     * @param balanceAfterDeposit balance amount
     * @param amount transaction amount
     */
    private void setTransactionDTO(Transaction savedTransaction, String accountNumber, TransactionDTO transactionDTO, Customer finalCustomer, Account acct, Double balanceAfterDeposit, Double amount) {
        transactionDTO.setTransactionId(savedTransaction.getId());
        transactionDTO.setCustomerId(finalCustomer.getId());
        transactionDTO.setCustomerName(finalCustomer.getCustomerName());
        transactionDTO.setAccountNumber(accountNumber);
        transactionDTO.setBalance(balanceAfterDeposit);
        transactionDTO.setTransactionAmount(amount);
    }
}
