package com.consumerbank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.dto.CustomerResponse;
import com.consumerbank.entity.Customer;

//here Integer is class and it is primary key for customer in database.

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query(value = "select c from Customer c")
	List<CustomerResponse> findByCustomerName(String name);
	
	List<Customer> findByCustomerNameContaining(String name);

	
}
