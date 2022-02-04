package com.consumerbank.dto;

import java.sql.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TransactionRequestDTO {

	@NotEmpty(message="transaction number can not be empty")
	private String transactionNumber;
	
	@NotNull(message="amount can not be empty")
	@Min(value=100, message="transaction amount can not be less than 100")
	private double amount;
	
	@NotEmpty(message="transaction type can not be empty")
	private String transactionType;
	
	private Integer accountId;
	private Date transactiondate;
	
	@NotNull(message="sender account Id can not be empty")
	private Integer senderAccountId;
	
	@NotNull(message="receiver account Id can not be empty")
	private Integer receiverAccountId;
	
	public Integer getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(Integer senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	public Integer getReceiverAccountId() {
		return receiverAccountId;
	}
	public void setReceiverAccountId(Integer receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Date getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
}
