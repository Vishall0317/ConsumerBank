package com.consumerbank.service.impl.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.entity.Customer;
import com.consumerbank.repo.CustomerRepository;
import com.consumerbank.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	CustomerRequestDTO customerRequestDTO;
	
	Customer customer;
	
	Customer savedCustomer;
	
	@BeforeEach
	public void setUp() {
		customerRequestDTO=new CustomerRequestDTO();
		customerRequestDTO.setCustomerName("pasha");
		customerRequestDTO.setPhoneNo("9090909090");
		customerRequestDTO.setAddress("nagpur");
		
//		customer=new Customer();
//		customer.setCustomerName("pasha");
//		customer.setPhoneNo("9090909090");
//		customer.setAddress("nagpur");
//		
//		savedCustomer=new Customer();
//		savedCustomer.setCustomerName("pasha");
//		savedCustomer.setPhoneNo("9090909090");
//		savedCustomer.setAddress("nagpur");
//		savedCustomer.setCustomerId(1);
	
	}
	
	@Test
	void saveCustomerDataTest_Positive() {
		
		//context
//		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
		
		when(customerRepository.save(any(Customer.class))).thenAnswer(i->{
			Customer customer=i.getArgument(0);
			customer.setCustomerId(1);
			return customer;
		});
	
		//event
		boolean result=customerServiceImpl.saveCustomerData(customerRequestDTO);
		
		//outcome
		assertTrue(result);
		
	}
	
	@Test
	void saveCustomerDataTest_Negative() {
		
		//context
		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
	
		//event
		boolean result=customerServiceImpl.saveCustomerData(customerRequestDTO);
		
		//outcome
		assertFalse(result);
		
	}

}
