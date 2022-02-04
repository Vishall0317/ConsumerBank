package com.consumerbank.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.consumerbank.dto.TransactionResponse;
import com.consumerbank.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	List<Transaction> findByTransactiondateBetween(Date fromDate, Date toDate);

	List<Transaction> findByTransactiondateBefore(Date date);
	
	@Query(value="select *from transactions where :date between date_format(curdate(), '%Y-%m-01') and curdate()", nativeQuery = true)
	List<TransactionResponse> findByTransactionsByMonth(Date date);

}