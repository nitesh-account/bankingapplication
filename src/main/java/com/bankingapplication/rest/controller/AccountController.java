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
@RequestMapping(value = {"/api"})
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/accounts")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Account save(@RequestBody Account account) {
		return accountService.save(account);
	 }
	
	@GetMapping(value = "/accounts/{customerId}")
	 public Page<Account> getAccountsByCustomerId (@PathVariable String customerId, Pageable pageable){
		return accountService.getAccountsByCustomerId(customerId,pageable);
	 }

	@GetMapping(value = "/accounts")
	public Page<Account> getAll (Pageable pageable){
		return accountService.getAll(pageable);
	}

	@DeleteMapping(value = "/accounts/{accountId}")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountId){
		return accountService.deleteAccount(accountId);
	}
	
	@PutMapping(value = "/accounts/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable String accountId, @RequestBody Account newAccount){
		return accountService.updateAccount(accountId,newAccount);
	}

	@PatchMapping(value = "/closeAccount/{accountId}")
	public ResponseEntity<Account> closeAccount(@PathVariable String accountId){
		return accountService.closeAccount(accountId);
	}

}
