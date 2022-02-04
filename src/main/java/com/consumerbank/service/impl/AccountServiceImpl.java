package com.consumerbank.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.consumerbank.dto.AccountRequestDTO;
import com.consumerbank.dto.AccountResponseDTO;
import com.consumerbank.entity.Account;
import com.consumerbank.entity.Customer;
import com.consumerbank.exception.NotFoundException;
import com.consumerbank.repo.AccountRepository;
import com.consumerbank.dto.AccountResponse;
import com.consumerbank.repo.CustomerRepository;
import com.consumerbank.service.AccountService;

@Service		
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	

	@Override
	public boolean saveAccountData(AccountRequestDTO accountRequestDTO) {
		
		Optional<Customer> optionalCustomer=customerRepository.findById(accountRequestDTO.getCustomerId());
		if(optionalCustomer.isEmpty()) throw new NotFoundException("Customer doesn't exist for the id "+
				accountRequestDTO.getCustomerId()+" or you enter wrong id please enter proper id");
		
		var account= new Account();
		BeanUtils.copyProperties(accountRequestDTO, account);
		
		var savedAccount=accountRepository.save(account);
		 if(ObjectUtils.isEmpty(savedAccount)) {
			 return false;
		 }else {
			 return true;
		 }
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<AccountResponseDTO> getAccountsDetails() {
		List<AccountResponseDTO> accountResponseList=new ArrayList<>();
		var iterator=accountRepository.findAll().iterator();
		
		while(iterator.hasNext()) {
			var responseDTO=new AccountResponseDTO();
			BeanUtils.copyProperties(iterator.next(), responseDTO);
			accountResponseList.add(responseDTO);
		}
		return accountResponseList;
	}
	@Override
	public AccountResponseDTO getAccountsDetails(Integer accountId) {
		
		var account= new Account();
		var accountResponseDTO=new AccountResponseDTO();
		
		Optional<Account> optionalAccount=accountRepository.findById(accountId);
		
		if(optionalAccount.isPresent()) {
			account=optionalAccount.get();
		}
		
		BeanUtils.copyProperties(account, accountResponseDTO);
		return accountResponseDTO;
	}
	@Override
	public AccountResponse findAccountByAccountNumber(long accountNumber) {
		
		
		return accountRepository.findAccountByAccountNumber(accountNumber);
	}
	
	
}
