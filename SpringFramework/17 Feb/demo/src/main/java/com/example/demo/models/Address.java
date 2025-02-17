package com.example.demo.models;

import org.springframework.stereotype.Component;

@Component
public class Address {
	
	private int pin;
	private String city;
	private String streetName;
	@Override
	public String toString() {
		return "Address [pin=" + pin + ", city=" + city + ", streetName=" + streetName + "]";
	}
	
}
