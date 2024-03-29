package com.bankingapplication.rest.controller;

import com.bankingapplication.rest.domain.Account;
import com.bankingapplication.rest.domain.dtos.AccountSearchDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AccountController is used to perform CRUD operations of account
 *
 * @author Nitesh Kumar
 */
@RestController
@CrossOrigin
@RequestMapping(value = {"/api"})
@Api(value = "/account", tags = "Account", description = "Account API's perform all account's related CRUD operations")
public class AccountController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PostMapping(value = "/accounts")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value = "Create account. user name is mandatory.")
	public Account save(@RequestBody Account account) {
		return accountService.save(account);
	 }

	@GetMapping(value = "/accounts/{accountNumber}")
	@ApiOperation(value = "Inquire account based on account number and return customer and account related data.")
	public ResponseEntity<AccountSearchDTO> getAccount (@PathVariable String accountNumber, Pageable pageable){
		return accountService.getAccount(accountNumber,pageable);
	}

	@GetMapping(value = "/accounts")
	@ApiOperation(value = "Get all accounts")
	public Page<Account> getAll (Pageable pageable){
		return accountService.getAll(pageable);
	}

	@DeleteMapping(value = "/accounts/{accountNumber}")
	@ApiOperation(value = "Delete account based on account number")
	public ResponseEntity<?> deleteAccount(@PathVariable String accountNumber){
		return accountService.deleteAccount(accountNumber);
	}
	
	@PutMapping(value = "/accounts/{accountNumber}")
	@ApiOperation(value = "Update account based on account number")
	public ResponseEntity<Account> updateAccount(@PathVariable String accountNumber, @RequestBody Account newAccount){
		return accountService.updateAccount(accountNumber,newAccount);
	}

	@PatchMapping(value = "/closeAccount/{accountNumber}")
	@ApiOperation(value = "Close account based on account number")
	public ResponseEntity<Account> closeAccount(@PathVariable String accountNumber){
		return accountService.closeAccount(accountNumber);
	}

}
