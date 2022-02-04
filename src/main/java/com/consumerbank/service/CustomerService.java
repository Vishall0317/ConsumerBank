package com.consumerbank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.dto.CustomerResponse;
import com.consumerbank.dto.CustomerResponseDTO;
import com.consumerbank.entity.Customer;

@Service
public interface CustomerService {

	boolean saveCustomerData(CustomerRequestDTO customerRequestDTO);

	List<CustomerResponseDTO> getCustomersDetails();
	
	CustomerResponseDTO getCustomersDetails(Integer customerId);
	
	List<CustomerResponse> getCustomersDetailsByName(String name);

	List<CustomerResponseDTO> getContainingCustomersDetails(String name);

	boolean deleteCustomer(Integer customerId);


}
