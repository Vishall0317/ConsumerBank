package com.consumerbank.service.impl.test;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.consumerbank.dto.AccountRequestDTO;
import com.consumerbank.entity.Account;
import com.consumerbank.entity.Customer;
import com.consumerbank.exception.NotFoundException;
import com.consumerbank.repo.AccountRepository;
import com.consumerbank.repo.CustomerRepository;
import com.consumerbank.service.impl.AccountServiceImpl;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	AccountServiceImpl accountServiceImpl;
	
	AccountRequestDTO accountRequestDTO;
	
	Account account;
	
	Customer customer;
	
	Account savedAccount;
	
	@BeforeEach
	public void setUp() {
		customer = new Customer(); 
		customer.setCustomerName("pasha");
		customer.setPhoneNo("8999880085");
		customer.setCustomerId(1);
		
		accountRequestDTO=new AccountRequestDTO();
		accountRequestDTO.setAccountNumber((long) 1234);
		accountRequestDTO.setBalance(1200);
		accountRequestDTO.setAccountType("SA");
		accountRequestDTO.setCustomerId(1);
	}
	
	@Test
	void saveAccountDataTest_Positive() {
		Optional<Customer> optionalCustomer= Optional.of(customer);
		when(customerRepository.findById(accountRequestDTO.getCustomerId())).thenReturn(optionalCustomer);
		accountServiceImpl.saveAccountData(accountRequestDTO);
		verify(accountRepository,times(1)).save(any(Account.class));
	}
	
	@Test
	void saveAccountDataTest_Negative() {
		
		Optional<Customer> optionalCustomer=Optional.empty();
		when(customerRepository.findById(accountRequestDTO.getCustomerId())).thenReturn(optionalCustomer);
		assertThrows(NotFoundException.class, ()->accountServiceImpl.saveAccountData(accountRequestDTO));

	}

}
