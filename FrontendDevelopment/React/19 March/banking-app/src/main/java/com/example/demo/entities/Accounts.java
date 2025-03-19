package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Accounts {
	
	@Id
	@GeneratedValue
	private int accountId;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Customer customerId;
	private int balance;
	
	public int getAccountId() {
		return accountId;
	}
	
	public Customer getCustomerId() {
		return customerId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
		

	
}
