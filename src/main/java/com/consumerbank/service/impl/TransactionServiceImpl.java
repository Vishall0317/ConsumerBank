package com.consumerbank.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.consumerbank.dto.TransactionRequestDTO;
import com.consumerbank.dto.TransactionResponse;
import com.consumerbank.dto.TransactionResponseDTO;
import com.consumerbank.entity.Account;
import com.consumerbank.entity.Transaction;
import com.consumerbank.exception.NotFoundException;
import com.consumerbank.repo.AccountRepository;
import com.consumerbank.repo.CustomerRepository;
import com.consumerbank.repo.TransactionRepository;
import com.consumerbank.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public boolean saveTransactionData(TransactionRequestDTO transactionRequestDTO) {

		Optional<Account> optionalAccountId=accountRepository.findById(transactionRequestDTO.getAccountId());
		if(optionalAccountId.isEmpty()) {
			throw new NotFoundException("Account doesn't exist for the id "+transactionRequestDTO.getAccountId());
		}
		
		Optional<Account> optionalSenderAccountId=accountRepository.findById(transactionRequestDTO.getSenderAccountId());
		if(optionalSenderAccountId.isEmpty()) {
			throw new NotFoundException("Sender account doesn't exist for the id "+transactionRequestDTO.getSenderAccountId());
		}
		
		Optional<Account> optionalReceiverAccountId=accountRepository.findById(transactionRequestDTO.getReceiverAccountId());
		if(optionalReceiverAccountId.isEmpty()) {
			throw new NotFoundException("Receiver account doesn't exist for the id "+transactionRequestDTO.getReceiverAccountId());
		}
		
		var transaction= new Transaction();
		BeanUtils.copyProperties(transactionRequestDTO, transaction);
		
		var savedTransaction=transactionRepository.save(transaction);
		
		 if(ObjectUtils.isEmpty(savedTransaction)) {
			 return false;
		 }else {
			 return true;
		 }
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<TransactionResponseDTO> getTransactionsDetails() {
		List<TransactionResponseDTO> transactionResponseList=new ArrayList<>();
		var iterator=transactionRepository.findAll().iterator();
		
		while(iterator.hasNext()) {
			var responseDTO=new TransactionResponseDTO();
			BeanUtils.copyProperties(iterator.next(), responseDTO);
			transactionResponseList.add(responseDTO);
		}
		return transactionResponseList;
	}

	@Override
	public TransactionResponseDTO getTransactionsDetails(Integer transactionId) {
	
		var transaction= new Transaction();
		var transactionResponseDTO=new TransactionResponseDTO();
		
		Optional<Transaction> optionalTransaction=transactionRepository.findById(transactionId);
		
		if(optionalTransaction.isPresent()) {
			transaction=optionalTransaction.get();
		}
		
		BeanUtils.copyProperties(transaction, transactionResponseDTO);
		return transactionResponseDTO;
	}

	@Override
	public List<Transaction> getTransactionsByDate(Date startDate, Date endDate) {
		
		if (startDate.after(endDate)) {
			throw new NotFoundException("you enter wrong date... please enter proper date!");
		}
		
		List<Transaction> transactiondateBetweenList= transactionRepository.findByTransactiondateBetween(startDate, endDate);
		
		if (transactiondateBetweenList.isEmpty())
			throw new NotFoundException("transactions for the given dates are not available.");
		return transactiondateBetweenList;
	}

	@Override
	public List<Transaction> getTransactionsBydateBefore(Date date) {
	
		return transactionRepository.findByTransactiondateBefore(date);
	}

	@Override
	public List<TransactionResponse> getTransactionsByMonth(Date date) {
		
		return transactionRepository.findByTransactionsByMonth(date);
	}

}
