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
	@JoinColumn(name = "customer_id", nullable = false) 
	private Customer customer;

	private int balance;
	
	public int getAccountId() {
		return accountId;
	}

	public Customer getCustomer() { // Rename getter to match new field name
		return customer;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setCustomer(Customer customer) { // Rename setter to match new field name
		this.customer = customer;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
