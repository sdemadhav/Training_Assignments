package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Accounts;
import com.example.demo.entities.Transactions;
import com.example.demo.repositories.AccountsRepo;
import com.example.demo.repositories.TransactionsRepo;

@Service
public class TransactionService {
	
	@Autowired
	TransactionsRepo tr;
	
	@Autowired
	AccountsRepo ar;
	
	public String addTransaction(Transactions transaction) {

	    Optional<Accounts> senderAccountsOpt = ar.findById(transaction.getSenderAccountId());
	    Optional<Accounts> receiverAccountsOpt = ar.findById(transaction.getReceiverAccountId());

	    if (senderAccountsOpt.isEmpty() || receiverAccountsOpt.isEmpty()) {
	        return "Invalid sender or receiver Accounts.";
	    }

	    Accounts senderAccounts = senderAccountsOpt.get();
	    Accounts receiverAccounts = receiverAccountsOpt.get();

	    if (senderAccounts.getBalance() < transaction.getAmount()) {
	        return "Insufficient funds.";
	    }

	    senderAccounts.setBalance(senderAccounts.getBalance() - transaction.getAmount());
	    receiverAccounts.setBalance(receiverAccounts.getBalance() + transaction.getAmount());

	    ar.save(senderAccounts);
	    ar.save(receiverAccounts);

	    tr.save(transaction);

	    return "Transaction created successfully!";
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
