package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.enums.AccountType;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.repository.AccountRepository;
import com.bankingapplication.rest.repository.CustomerRepository;
import com.bankingapplication.rest.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;


    public Account save(Account account) {
        String customerId = account.getCustomer().getId();
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            account.setAccountStatus("Active");
            account.setOpeningDate(Instant.now().getEpochSecond());
            String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            if(!StringUtils.isEmpty(createdBy))
                account.setCreatedBy(createdBy);
            return accountRepository.save(account);

        }).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
    }

    public Page<Account> getAccountsByCustomerId(String customerId, Pageable pageable) {
        return accountRepository.findByCustomerId(customerId, pageable);
    }

    public ResponseEntity<?> deleteAccount(String accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            if (!customerRepository.existsById(customerId)) {
                throw new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found");
            }
        }

        return accountRepository.findById(accountId).map(acct ->{
            accountRepository.delete(acct);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }

    public ResponseEntity<Account> updateAccount(String accountId, Account newAccount) {
        Optional<Account> account = accountRepository.findById(accountId);
        Customer customer = new Customer();
        if(account.isPresent()){
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
            }

        Customer finalCustomer = customer;
        return accountRepository.findById(accountId).map(acct ->{
            setNewAccount(newAccount, finalCustomer, acct);
            accountRepository.save(newAccount);
            return ResponseEntity.ok(newAccount);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }

    private void setNewAccount(Account newAccount, Customer finalCustomer, Account acct) {
        newAccount.setCustomer(finalCustomer);
        newAccount.setAccountNumber(acct.getAccountNumber());
        newAccount.setCreatedBy(acct.getCreatedBy());
        String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(updatedBy))
            newAccount.setUpdatedBy(updatedBy);
    }

    public ResponseEntity<Account> closeAccount(String accountNumber) {
        return accountRepository.findById(accountNumber).map(account ->{
            account.setAccountStatus("Closed");
            account.setAccountNumber(accountNumber);
            account.setClosingDate(Instant.now().getEpochSecond());
            accountRepository.save(account);
            return ResponseEntity.ok(account);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountNumber="+accountNumber+"] can't be found"));
    }

    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
