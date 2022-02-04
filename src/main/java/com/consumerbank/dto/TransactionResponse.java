package com.consumerbank.dto;

import java.sql.Date;

public interface TransactionResponse {

	String TransactionNumber();
	double Amount();
	String TransactionType();
	Integer AccountId();
	Date Transactiondate();
	Integer SenderAccountId();
	Integer ReceiverAccountId();
	
}
