package com.consumerbank.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
	private String customerName;
	private String phoneNo;
	private String address;
	
	
//	@JsonProperty("address")
//	private Address address;
	
	
	/*
	 * we are able to show city, state and pincode column 
	 * for home and office in customer table itself using 
	 * below annotations 
	 * @Embedded, @AttributeOverrides, 
	 * @AttributeOverride and @Column
	 * 
	 * but the condition is whatever variables you write in 
	 * @AttributeOverrides only that variables present in 
	 * child class Address.
	 */
//	@Embedded
//	@AttributeOverrides({
//			@AttributeOverride(name="city", column= @Column(name="homeCity")),
//			@AttributeOverride(name="state", column= @Column(name="homeState")),
//			@AttributeOverride(name="pincode", column= @Column(name="homePincode"))})
//	private Address homeAddress;
//	
//	@Embedded
//	@AttributeOverrides({
//			@AttributeOverride(name="city", column= @Column(name="officeCity")),
//			@AttributeOverride(name="state", column= @Column(name="officeState")),
//			@AttributeOverride(name="pincode", column= @Column(name="officePincode"))})
//	private Address officeAddress;
 
	/*
	 * using @ElementCollection you can create another table 
	 * which is define relation between customer and its address.
	 */
//	@ElementCollection
//	List<Address> addressList=new ArrayList<>();
	
	
	/*
	 * using this mapping you can create another column 
	 * in the customer table which is display 
	 * accountId for respective customer.
	 */
//	@OneToOne
//	private Account account;
	
	/*
	 * using this mapping you can create another table 
	 * which is display account types for respective customerId.
	 */
	//@OneToMany(mappedBy="customer")
	@OneToMany
	private List<Account> account=new ArrayList<>();
	
	
	/*
	 * using this mapping you can create another table 
	 * which is display account-id from account table
	 * and respective customerId from customer table.
	 * but here we used mappedBy attribute customer so 
	 * only one table will be created. 
	 */
//	@ManyToMany(mappedBy="customer")
//	private List<Account> account=new ArrayList<>();
//	
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
//	public Account getAccount() {
//		return account;
//	}
//	public void setAccount(Account account) {
//		this.account = account;
//	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Customer(Integer customerId, String customerName, String phoneNo, String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
	}
	public Customer() {
		super();
		
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
//	
//	public List<Address> getAddressList() {
//		return addressList;
//	}
//	public void setAddressList(List<Address> addressList) {
//		this.addressList = addressList;
//	}
	
	
}
