package com.example.demo.models.lappy;

import org.springframework.stereotype.Component;

@Component("mac")
//@Priority(value = 3) -> this would mean we are giving a priority of 3 to this while trying to inject dependency
public class MacBook implements Laptop {

	@Override
	public String toString() {
		return "You are being given a MacBook";
	}

}

