package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Transactions;
import com.example.demo.services.TransactionService;

@RestController
public class TransactionsController {
	
	@Autowired
	TransactionService ts ;
	
	@PostMapping("/transactions")
	public String addTransaction(@RequestBody Transactions transactions){
		return ts.addTransaction(transactions);
		
	}
	
	@GetMapping("/transactions/{id}")
	public Optional<Transactions> getTransactionById(@PathVariable("id") int id) {
		return ts.getTransactionById(id);
	}
	
	@GetMapping("/transactions/all")
	public List<Transactions> getAllTransactions() {
		return ts.getAllTransactions();
	}


}
