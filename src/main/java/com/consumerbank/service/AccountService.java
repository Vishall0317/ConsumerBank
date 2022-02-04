package com.consumerbank.service;

import java.util.List;

import com.consumerbank.dto.AccountRequestDTO;
import com.consumerbank.dto.AccountResponse;
import com.consumerbank.dto.AccountResponseDTO;


public interface AccountService {

	boolean saveAccountData(AccountRequestDTO accountRequestDTO);

	List<AccountResponseDTO> getAccountsDetails();

	AccountResponseDTO getAccountsDetails(Integer accountId);

	AccountResponse findAccountByAccountNumber(long accountNumber);

}
