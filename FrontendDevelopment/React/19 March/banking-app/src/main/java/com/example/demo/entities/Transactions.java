package com.example.demo.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Transactions {

	@Id
	@GeneratedValue
	private int transactionId;
	
//	@ManyToOne	
//	@JoinColumn
	private int senderAccountId;
	
//	@ManyToOne
//	@JoinColumn
	private int receiverAccountId;
	
	private int amount;
	
	@Column(name = "created_datetime", nullable = false, updatable = false)
    private LocalDateTime createdDatetime;
	
	
	 @PrePersist
	    public void prePersist() {
	        LocalDateTime now = LocalDateTime.now();
	        this.createdDatetime = now;
	    }


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public int getSenderAccountId() {
		return senderAccountId;
	}


	public void setSenderAccountId(int senderAccountId) {
		this.senderAccountId = senderAccountId;
	}


	public int getReceiverAccountId() {
		return receiverAccountId;
	}


	public void setReceiverAccountId(int receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}


	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	 
	
}
