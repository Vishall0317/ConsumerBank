package com.consumerbank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.dto.CustomerResponse;
import com.consumerbank.dto.CustomerResponseDTO;
import com.consumerbank.service.CustomerService;

@RestController
@Validated
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	/*
	 * save customer details
	 */
	
	@PostMapping("/customers")
	public ResponseEntity<String> saveCustomerData(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
	
		boolean response= customerService.saveCustomerData(customerRequestDTO);
		if(response) {
			return new ResponseEntity<> ("customer data save successfully",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<> ("customer data save unsuccessfull",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	/*
	 * get all customer details
	 */
	
	@GetMapping("/customers")
	public List<CustomerResponseDTO> getCustomersDetails(){

		return customerService.getCustomersDetails();
	}
	
	/*
	 * get single customer details by id
	 */
	
	@GetMapping("/customers/{customerId}")
	public CustomerResponseDTO getCustomersDetails(@PathVariable Integer customerId){
		return customerService.getCustomersDetails(customerId);
	}

	/*
	 * get single customer details by name 
	 */
	
	@GetMapping("/customers/name")
	public List<CustomerResponse> getCustomersDetails(@NotEmpty(message="name can not be empty") @RequestParam String name){
		
		 return customerService.getCustomersDetailsByName(name);
	}
	
	/*
	 * get similar or reference containing customer details by name
	 */
	
	@GetMapping("/customers/contains")
	public List<CustomerResponseDTO> getContainingCustomersDetails(@RequestParam String name){
		if(name!=null) {
			return customerService.getContainingCustomersDetails(name);
		}
		 return customerService.getCustomersDetails();
	}
	
	/*
	 * delete customer data
	 */

	@DeleteMapping("/customers/{customerId}")
	public  ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {

		boolean response=customerService.deleteCustomer(customerId);
		if(response) {
			return new ResponseEntity<> ("customer data delete successfully",HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<> ("customer data delete unsuccessfull",HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
}












