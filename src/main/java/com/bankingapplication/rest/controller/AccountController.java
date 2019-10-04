package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/customers/{customerId}/accounts")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Account save(@PathVariable String customerId, @RequestBody Account account) {
		return accountService.save(customerId, account);
	 }
	
	@GetMapping(value = "/customers/{customerId}/accounts")
	 public Page<Account> getAccountsByCustomerId (@PathVariable String customerId, Pageable pageable){
		return accountService.getAccountsByCustomerId(customerId,pageable);
	 } 
	
	@DeleteMapping(value = "/customers/{customerId}/accounts/{accountId}")
	public ResponseEntity<?> deleteAccount(@PathVariable String customerId, @PathVariable String accountId){
		return accountService.deleteAccount(customerId,accountId);
	}
	
	@PutMapping(value = "/customers/{customerId}/accounts/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable String customerId, @PathVariable String accountId, @RequestBody Account newAccount){
		return accountService.updateAccount(customerId,accountId,newAccount);
	}

	@PatchMapping(value = "/customers/{customerId}/closeAccount/{accountId}")
	public ResponseEntity<Account> closeAccount(@PathVariable String customerId, @PathVariable String accountId){
		return accountService.closeAccount(customerId,accountId);
	}

}
