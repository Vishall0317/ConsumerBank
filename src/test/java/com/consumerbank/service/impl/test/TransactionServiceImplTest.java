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

import com.consumerbank.dto.TransactionRequestDTO;
import com.consumerbank.entity.Account;
import com.consumerbank.entity.Transaction;
import com.consumerbank.exception.NotFoundException;
import com.consumerbank.repo.AccountRepository;
import com.consumerbank.repo.TransactionRepository;
import com.consumerbank.service.impl.TransactionServiceImpl;



@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@Mock
	TransactionRepository transactionRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;
	
	TransactionRequestDTO transactionRequestDTO;
	
	Transaction transaction;
	
	Transaction savedTransaction;
	
	Account account;
	
	@BeforeEach
	public void setUp() {
		transactionRequestDTO=new TransactionRequestDTO();
		transactionRequestDTO.setTransactionNumber("tx123");
		transactionRequestDTO.setAmount(1200);
		transactionRequestDTO.setTransactionType("SA");
		transactionRequestDTO.setReceiverAccountId(2);
		transactionRequestDTO.setSenderAccountId(1);
		transactionRequestDTO.setTransactionNumber("tx123");
		//transactionController.setTransactiondate("2021-12-21");
		
		account=new Account();
		account.setAccountNumber((long) 1234);
		account.setBalance(1200);
		account.setAccountType("SA");
		account.setAccountId(1);
		
	
	}
	
	@Test
	void saveTransactionDataTest_Positive() {
		
		Optional<Account> optionalAccount = Optional.of(account);
		when(accountRepository.findById(transactionRequestDTO.getAccountId())).thenReturn(optionalAccount);
		transactionServiceImpl.saveTransactionData(transactionRequestDTO);
		verify(accountRepository, times(1)).save(any(Account.class));

		
	}
	
	@Test
	void saveTransactionDataTest_Negative() {
	
		Optional<Account> optionalAccount = Optional.empty();
		when(accountRepository.findById(transactionRequestDTO.getAccountId())).thenReturn(optionalAccount);
		assertThrows(NotFoundException.class,
				() -> transactionServiceImpl.saveTransactionData(transactionRequestDTO));

		
	}

}
