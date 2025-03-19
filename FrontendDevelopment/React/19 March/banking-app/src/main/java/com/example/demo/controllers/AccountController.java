package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Accounts;
import com.example.demo.services.AccountsService;

@RestController
public class AccountController {
	
	@Autowired
	AccountsService as ;
	
	@PostMapping("/accounts")
	public String createAccount(@RequestBody Accounts account){
		return as.createAccount(account);
		
	}
	
	@GetMapping("/accounts/all")
	public List<Accounts> getAllAccounts() {
		return as.getAllAccounts();
	}

	@GetMapping("/accounts/{id}")
	public Optional<Accounts> getAccountsById(@PathVariable("id") int id) {
		return as.getAccountById(id);
	}

}
