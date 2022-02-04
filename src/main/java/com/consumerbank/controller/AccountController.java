package com.consumerbank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.consumerbank.dto.AccountRequestDTO;
import com.consumerbank.dto.AccountResponseDTO;
import com.consumerbank.dto.AccountResponse;
import com.consumerbank.service.AccountService;

@RestController
@Validated
public class AccountController {

	@Autowired
	AccountService accountService;
	
	/*
	 * save account details
	 */
	
	@PostMapping("/accounts")
	public ResponseEntity<String> saveAccountData(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
		
		boolean response= accountService.saveAccountData(accountRequestDTO);
		if(response) {
			return new ResponseEntity<> ("account data save successfully",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<> ("account data save unsuccessfull",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/*
	 * get all account details
	 */
	
	@GetMapping("/accounts")
	public List<AccountResponseDTO> getAccountsDetails(){
		return accountService.getAccountsDetails();
	}
	
	/*
	 * get single account details by id
	 */
	
	@GetMapping("/accounts/accountId/{accountId}")
	public AccountResponseDTO getAccountsDetails(@PathVariable Integer accountId){
		return accountService.getAccountsDetails(accountId);
	}
	
	/*
	 * get single account details by account no
	 */
	
	@GetMapping("/accounts/{accountNumber}")
	public AccountResponse findAccountByAccountNumber(@NotEmpty(message="account number can not be empty")@PathVariable long accountNumber){
		return accountService.findAccountByAccountNumber(accountNumber);
	}
	
	
	
}
