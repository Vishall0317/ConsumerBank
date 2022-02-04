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

import com.consumerbank.controller.TransactionController;
import com.consumerbank.dto.TransactionRequestDTO;
import com.consumerbank.service.TransactionService;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

	@Mock
	TransactionService transactionService;
	
	@InjectMocks
	TransactionController transactionController;
	
	TransactionRequestDTO transactionRequestDTO;
	
	@BeforeEach
	public void setUp() {
		transactionRequestDTO=new TransactionRequestDTO();
		transactionRequestDTO.setAccountId(1);
		transactionRequestDTO.setAmount(100);
		transactionRequestDTO.setTransactionType("SA");
		transactionRequestDTO.setReceiverAccountId(2);
		transactionRequestDTO.setSenderAccountId(1);
		transactionRequestDTO.setTransactionNumber("tx123");
		//transactionController.setTransactiondate("2021-12-21");
		
	}
	
	@Test
	@DisplayName("Save Transaction Data: Positive")
	void saveTransactionDataTest_Positive() {
		
		//context
		when(transactionService.saveTransactionData(transactionRequestDTO)).thenReturn(true);
		
		//event
		ResponseEntity<String> result=transactionController.saveTransactionData(transactionRequestDTO);
		
		//outcome
		assertEquals("transaction data save successfully", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		
	}
	
	@Test
	@DisplayName("Save Transaction Data: Negative")
	void saveTransactionDataTest_Negative() {
		
		//context
		when(transactionService.saveTransactionData(transactionRequestDTO)).thenReturn(false);
		
		//event
		ResponseEntity<String> result=transactionController.saveTransactionData(transactionRequestDTO);
		
		//outcome
		assertEquals("transaction data save unsuccessfull", result.getBody());
		assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
		
	
	}
}
