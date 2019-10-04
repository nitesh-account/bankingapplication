package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
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

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;


    public Account save(String customerId, Account account) {
        return customerRepository.findById(customerId).map(customer -> {
            account.setCustomer(customer);
            account.setAccountStatus("Active");
            String createdBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
            if(!StringUtils.isEmpty(createdBy))
                account.setCreatedBy(createdBy);
            return accountRepository.save(account);

        }).orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));
    }

    public Page<Account> getAccountsByCustomerId(String customerId, Pageable pageable) {
        return accountRepository.findByCustomerId(customerId, pageable);
    }

    public ResponseEntity<?> deleteAccount(String customerId, String accountId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found");
        }

        return accountRepository.findById(accountId).map(account ->{
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }

    public ResponseEntity<Account> updateAccount(String customerId, String accountId, Account newAccount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

        return accountRepository.findById(accountId).map(account ->{
            newAccount.setCustomer(customer);
            accountRepository.save(newAccount);
            return ResponseEntity.ok(newAccount);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }

    public ResponseEntity<Account> closeAccount(String customerId, String accountId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found"));

        return accountRepository.findById(accountId).map(account ->{
            account.setCustomer(customer);
            account.setAccountStatus("Closed");
            accountRepository.save(account);
            return ResponseEntity.ok(account);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }
}
