package com.example.demo.models.lappy;

import org.springframework.stereotype.Component;

@Component
public class DellLaptop implements Laptop {

	@Override
	public String toString() {
		return "You are being given a Dell Laptop";
	}

}
