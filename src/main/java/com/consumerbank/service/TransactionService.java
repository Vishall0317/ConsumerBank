package com.consumerbank.service;

import java.sql.Date;
import java.util.List;

import com.consumerbank.dto.TransactionRequestDTO;
import com.consumerbank.dto.TransactionResponse;
import com.consumerbank.dto.TransactionResponseDTO;
import com.consumerbank.entity.Transaction;

public interface TransactionService {

	boolean saveTransactionData(TransactionRequestDTO transactionRequestDTO);

	List<TransactionResponseDTO> getTransactionsDetails();

	TransactionResponseDTO getTransactionsDetails(Integer transactionId);

	List<Transaction> getTransactionsByDate(Date startDate, Date endDate);

	List<Transaction> getTransactionsBydateBefore(Date date);

	List<TransactionResponse> getTransactionsByMonth(Date date);

}
