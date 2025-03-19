package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepo cr ;
	
	public String createCustomer(Customer customer) {
		cr.save(customer);
		return "Customer created successfully !";
	}
	
	public List<Customer> getAllCustomer()
	{
		return cr.findAll();
	}
	
	public Optional<Customer> getCustomerById(int id) {
		if(cr.findById(id) != null) {
			return cr.findById(id);
		}
		return Optional.empty();
	}
	
		

}
