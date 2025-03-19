package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Customer;
import com.example.demo.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService cs ;
	
	@PostMapping("/customer")
	public String createCustomer(@RequestBody Customer customer){
		return cs.createCustomer(customer);
		
	}
	
	@GetMapping("/customer/{id}")
	public Optional<Customer> getCustomerById(@PathVariable("id") int id) {
		return cs.getCustomerById(id);
	}
	
	@GetMapping("/customer/all")
	public List<Customer> getAllCustomers() {
		return cs.getAllCustomer();
	}

}
