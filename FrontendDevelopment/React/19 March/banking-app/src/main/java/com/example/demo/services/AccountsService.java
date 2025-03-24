package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Accounts;
import com.example.demo.entities.Customer;
import com.example.demo.repositories.AccountsRepo;
import com.example.demo.repositories.CustomerRepo;

@Service
public class AccountsService {
	@Autowired
	AccountsRepo ar;
	
	@Autowired
	CustomerRepo cr;
	
	public List<Accounts> getAllAccounts() {
		return ar.findAll();
	}
	
	public Optional<Accounts> getAccountById(int id){
		if(ar.findById(id)!=null) {
			return ar.findById(id);
		}
		return Optional.empty();
	}
	
	public String createAccount(Accounts acc) {
		ar.save(acc);
		return "Created Account Successfully !";
	}

	
	public List<Accounts> getAccountsByCustomerId(int customerId) {
        Optional<Customer> customer = cr.findById(customerId);
        if (customer.isPresent()) {
            return ar.findByCustomer(customer.get());
        } else {
            throw new RuntimeException("Customer not found!");
        }
    }

}
