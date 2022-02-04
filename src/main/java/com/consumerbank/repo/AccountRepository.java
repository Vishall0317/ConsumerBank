package com.consumerbank.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.consumerbank.dto.AccountResponse;
import com.consumerbank.entity.Account;

	@Repository
	public interface AccountRepository extends CrudRepository<Account, Integer>{

		AccountResponse findAccountByAccountNumber(long accountNumber);
		
}
