package com.consumerbank.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;

import com.consumerbank.dto.CustomerRequestDTO;
import com.consumerbank.dto.CustomerResponse;
import com.consumerbank.dto.CustomerResponseDTO;
import com.consumerbank.entity.Customer;
import com.consumerbank.repo.CustomerRepository;
import com.consumerbank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public boolean saveCustomerData(CustomerRequestDTO customerRequestDTO) {
	
		var customer=new Customer();
		
		BeanUtils.copyProperties(customerRequestDTO, customer);
		
		 var savedCustomer=customerRepository.save(customer);
		 if(ObjectUtils.isEmpty(savedCustomer)) {
			 return false;
		 }else {
			 return true;
		 }
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<CustomerResponseDTO> getCustomersDetails() {
		
		List<CustomerResponseDTO> customerResponseList=new ArrayList<>();
		var iterator=customerRepository.findAll().iterator();
		
		while(iterator.hasNext()) {
			var responseDTO=new CustomerResponseDTO();
			BeanUtils.copyProperties(iterator.next(), responseDTO);
			customerResponseList.add(responseDTO);
		}
		return customerResponseList;
	}

	@Override
	public CustomerResponseDTO getCustomersDetails(Integer customerId) {
	
		var customer=new Customer();
		var customerResponseDTO=new CustomerResponseDTO();
		
		Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
		
		if(optionalCustomer.isPresent()) {
			customer=optionalCustomer.get();
		}
		
		BeanUtils.copyProperties(customer, customerResponseDTO);
		return customerResponseDTO;
	}
	
	@Override
	public List<CustomerResponse> getCustomersDetailsByName(String name) {
		
		return customerRepository.findByCustomerName(name);
	}

	@Override
	public List<CustomerResponseDTO> getContainingCustomersDetails(String name) {
		List<CustomerResponseDTO> customerResponseList=new ArrayList<>();
		List<Customer> customerList=customerRepository.findByCustomerNameContaining(name);
		
		for(Customer customer : customerList){
			var responseDTO=new CustomerResponseDTO();
			BeanUtils.copyProperties(customer, responseDTO);
			customerResponseList.add(responseDTO);
		}
		return customerResponseList;
	}

	@Override
	public boolean deleteCustomer(Integer customerId) {
		
		customerRepository.deleteById(customerId);
		return true;
	}
}
