package com.consumerbank.dto;

public class CustomerResponse {

	private String customerName;
	private String phoneNo;
	private String address;
	
	public CustomerResponse(String customerName, String phoneNo, String address) {
		super();
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.address = address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
