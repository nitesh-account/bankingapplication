package com.bankingapplication.rest.service;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.Customer;
import com.bankingapplication.rest.domain.dtos.AccountSearchDTO;
import com.bankingapplication.rest.enums.HttpHeaders;
import com.bankingapplication.rest.exception.ResourceNotFoundException;
import com.bankingapplication.rest.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Optional;

/**
 * AccountService class is used for logical implementation for account related operations
 *
 * @author Nitesh Kumar
 */

@Service
public class AccountService extends BaseService{
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    /**
     * Account save operation saves account and customer related data in Account table
     *
     * @param account a valid {@link Account} object
     * @return New {@link Account} object
     */
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

    /**
     * Account delete operation delete account from Account table
     *
     * @param accountId
     * @return
     */
    public ResponseEntity<?> deleteAccount(String accountId) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountId);
        checkCustomerId(account);

        return accountRepository.findByAccountNumber(accountId).map(acct ->{
            accountRepository.delete(acct);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
    }

    /**
     * Checking customer id if customer id is not present than will throw {@link ResourceNotFoundException}
     *
     * @param account is valid {@link Account} object
     */
    private void checkCustomerId(Optional<Account> account) {
        if(account.isPresent()){
            Account acct = account.get();
            String customerId = acct.getCustomer().getId();
            if (!customerRepository.existsById(customerId)) {
                throw new ResourceNotFoundException("Customer [customerId="+customerId+"] can't be found");
            }
        }
    }

    /**
     * Update account data
     *
     * @param accountId unique id
     * @param newAccount valid {@link Account} object
     * @return updated {@link Account} object
     */
    public ResponseEntity<Account> updateAccount(String accountId, Account newAccount) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountId);
        Customer customer = new Customer();
        customer = getCustomer(account, customer);

        Customer finalCustomer = customer;
        return accountRepository.findByAccountNumber(accountId).map(acct ->{
            setNewAccount(newAccount, finalCustomer, acct);
            accountRepository.save(newAccount);
            return ResponseEntity.ok(newAccount);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountId+"] can't be found"));
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
     * Setting values in {@link Account} object
     *
     * @param newAccount a valid {@link Account} object
     * @param finalCustomer a valid {@link Customer} object
     * @param acct a valid {@link Account} object
     */
    private void setNewAccount(Account newAccount, Customer finalCustomer, Account acct) {
        newAccount.setCustomer(finalCustomer);
        newAccount.setAccountNumber(acct.getAccountNumber());
        newAccount.setCreatedBy(acct.getCreatedBy());
        String updatedBy = HttpUtils.getHeader(HttpHeaders.USER_NAME);
        if(!StringUtils.isEmpty(updatedBy))
            newAccount.setUpdatedBy(updatedBy);
    }

    /**
     * Update account status as "Closed"
     *
     * @param accountNumber unique account id
     * @return {@link Account} object
     */
    public ResponseEntity<Account> closeAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).map(account ->{
            account.setAccountStatus("Closed");
            account.setAccountNumber(accountNumber);
            account.setClosingDate(Instant.now().getEpochSecond());
            accountRepository.save(account);
            return ResponseEntity.ok(account);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountNumber="+accountNumber+"] can't be found"));
    }

    /**
     * Get all accounts in {@link Pageable}
     *
     * @param pageable
     * @return {@link Page<Account>}
     */
    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    /**
     * Construct {@link AccountSearchDTO} object
     *
     * @param accountNumber unique account id
     * @param pageable {@link Pageable}
     * @return {@link AccountSearchDTO} object
     */
    public ResponseEntity<AccountSearchDTO> getAccount(String accountNumber, Pageable pageable) {
        AccountSearchDTO accountSearchDTO = new AccountSearchDTO();
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        Customer customer = new Customer();

        customer = getCustomer(account, customer);

        Customer finalCustomer = customer;
        return accountRepository.findByAccountNumber(accountNumber).map(acct ->{
            setAccountSearchDTO(accountNumber, accountSearchDTO, finalCustomer, acct);
            return ResponseEntity.ok(accountSearchDTO);
        }).orElseThrow(() -> new ResourceNotFoundException("Account [accountId="+accountNumber+"] can't be found"));
    }

    /**
     * Set {@link AccountSearchDTO} object
     *
     * @param accountNumber unique account id
     * @param accountSearchDTO a valid {@link AccountSearchDTO} object
     * @param finalCustomer a vliad {@link Customer} object
     * @param acct a valid {@link Account} object
     */
    private void setAccountSearchDTO(String accountNumber, AccountSearchDTO accountSearchDTO, Customer finalCustomer, Account acct) {
        accountSearchDTO.setCustomerId(finalCustomer.getId());
        accountSearchDTO.setCustomerName(finalCustomer.getCustomerName());
        accountSearchDTO.setAccountNumber(accountNumber);
        accountSearchDTO.setAccountStatus(acct.getAccountStatus());
    }
}
