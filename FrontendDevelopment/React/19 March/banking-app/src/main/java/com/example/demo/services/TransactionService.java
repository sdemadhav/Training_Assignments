package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Transactions;
import com.example.demo.repositories.TransactionsRepo;

@Service
public class TransactionService {
	
	@Autowired
	TransactionsRepo tr;
	
	public String addTransaction(Transactions transaction) {
		tr.save(transaction);
		return "Transaction created successfully !";
	}
	
	public List<Transactions> getAllTransactions()
	{
		return tr.findAll();
	}
	
	public Optional<Transactions> getTransactionById(int id) {
		if(tr.findById(id) != null) {
			return tr.findById(id);
		}
		return Optional.empty();
	}	

}
