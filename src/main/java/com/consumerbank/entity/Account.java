package com.consumerbank.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer accountId;
	private Long accountNumber;
	private double balance;
	private Integer customerId;
	private String accountType;
	
	/*
	 * using this mapping you can create another column
	 * in account table which is display customerIds for respective account-type.
	 */
//	@ManyToOne
//	private Customer customer;
	
	/*
	 * using this mapping you can create another table 
	 * which is display account-id from account table
	 * and respective customerId from customer table.
	 * 
	 * @JoinTable can help to change the name of 
	 * column or table in DB.
	 */
//	@ManyToMany
//	@JoinTable(joinColumns= @JoinColumn(name="accountId"), inverseJoinColumns= @JoinColumn(name="customerId"))
//	private List<Customer> customer=new ArrayList<>();
//	
//	public List<Customer> getCustomer() {
//		return customer;
//	}
//	public void setCustomer(List<Customer> customer) {
//		this.customer = customer;
//	}
	
//	public Customer getCustomer() {
//		return customer;
//	}
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
