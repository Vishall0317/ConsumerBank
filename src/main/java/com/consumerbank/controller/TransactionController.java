package com.consumerbank.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consumerbank.dto.TransactionRequestDTO;
import com.consumerbank.dto.TransactionResponse;
import com.consumerbank.dto.TransactionResponseDTO;
import com.consumerbank.entity.Transaction;
import com.consumerbank.service.TransactionService;

@RestController
@Validated
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	/*
	 * save Transaction details
	 */
	
	@PostMapping("/transactions")
	public ResponseEntity<String> saveTransactionData(@Valid @RequestBody TransactionRequestDTO transactionRequestDTO){
		
		boolean response = transactionService.saveTransactionData(transactionRequestDTO);
		if(response) {
			return new ResponseEntity<> ("transaction data save successfully",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<> ("transaction data save unsuccessfull",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	/*
	 * get all transactions details
	 */
	
	@GetMapping("/transactions")
	public List<TransactionResponseDTO> getTransactionsDetails(){
		return transactionService.getTransactionsDetails();
	}
	
	/*
	 * get single transaction details by id
	 */
	
	@GetMapping("/transactions/{transactionId}")
	public TransactionResponseDTO getTransactionsDetails(@PathVariable Integer transactionId){
		return transactionService.getTransactionsDetails(transactionId);
	}
	
	/*
	 * get transactions by Date
	 */
	
	@GetMapping("/transactions/date")
	public List<Transaction> getTransactionsByDate(@RequestParam Date startDate, @RequestParam Date endDate){
		return transactionService.getTransactionsByDate(startDate, endDate);
	}
	
	/*
	 * get transactions by dateBefore
	 */
	
	@GetMapping("/transactions/dateBefore")
	public List<Transaction> getTransactionsBydateBefore(@RequestParam Date date){
		return transactionService.getTransactionsBydateBefore(date);
	}
	
	/*
	 * get last transactions by month
	 */
	
	@GetMapping("/transactions/month")
	public List<TransactionResponse> getTransactionsByMonth(@RequestParam Date date){
		return transactionService.getTransactionsByMonth(date);
	}
}
