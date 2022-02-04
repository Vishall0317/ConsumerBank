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

import com.consumerbank.controller.CustomerController;
import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;
	
	CustomerRequestDTO customerRequestDTO;
	
	@BeforeEach
	public void setUp() {
		customerRequestDTO=new CustomerRequestDTO();
		customerRequestDTO.setCustomerName("pasha");
		customerRequestDTO.setPhoneNo("9090909090");
		customerRequestDTO.setAddress("nagpur");
	}
	
	@Test
	@DisplayName("Save Customer Data: Positive")
	void saveCustomerDataTest_Positive() {
		
		//context
		when(customerService.saveCustomerData(customerRequestDTO)).thenReturn(true);
		
		//event
		ResponseEntity<String> result=customerController.saveCustomerData(customerRequestDTO);
		
		//outcome
		assertEquals("customer data save successfully", result.getBody());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
		
	}
	
	@Test
	@DisplayName("Save Customer Data: Negative")
	void saveCustomerDataTest_Negative() {
		
		//context
		when(customerService.saveCustomerData(customerRequestDTO)).thenReturn(false);
		
		//event
		ResponseEntity<String> result=customerController.saveCustomerData(customerRequestDTO);
		
		//outcome
		assertEquals("customer data save unsuccessfull", result.getBody());
		assertEquals(HttpStatus.NOT_ACCEPTABLE, result.getStatusCode());
		
	
	}
}

















