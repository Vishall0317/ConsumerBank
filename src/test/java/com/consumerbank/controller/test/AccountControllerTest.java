package com.consumerbank.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.consumerbank.controller.AccountController;
import com.consumerbank.dto.AccountRequestDTO;
import com.consumerbank.service.AccountService;


@ExtendWith(MockitoExtension.class)
class AccountControllerTest {


	@Mock
	AccountService accountService;
	
	@InjectMocks
	AccountController accountController;
	
	AccountRequestDTO accountRequestDTO;
	
	@BeforeEach
	public void setUp() {
		accountRequestDTO=new AccountRequestDTO();
		accountRequestDTO.setAccountNumber((long) 1234);
		accountRequestDTO.setBalance(1200);
		accountRequestDTO.setAccountType("SA");
		accountRequestDTO.setCustomerId(1);
		
	}
	
	@Test
	@DisplayName("Save Account Data: Positive")
	void saveAccountDataTest_Positive() {
		
		//context
		when(accountService.saveAccountData(accountRequestDTO)).thenReturn(true);
		
		//event
		ResponseEntity<String> result=accountController.saveAccountData(accountRequestDTO);
		
		//outcome
		assertEquals("account data save successfully", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		
	}
	
	@Test
	@DisplayName("Save Account Data: Negative")
	void saveAccountDataTest_Negative() {
		
		//context
		when(accountService.saveAccountData(accountRequestDTO)).thenReturn(false);
		
		//event
		ResponseEntity<String> result=accountController.saveAccountData(accountRequestDTO);
		
		//outcome
		assertEquals("account data save unsuccessfull", result.getBody());
		assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
		
	}

}
