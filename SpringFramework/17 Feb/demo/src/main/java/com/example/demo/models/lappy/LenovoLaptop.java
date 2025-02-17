package com.example.demo.models.lappy;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary //This will be chosen as the default implementation of laptop interface until specifically asked by Qualifier in the Employee class.
@Component
public class LenovoLaptop implements Laptop {

	@Override
	public String toString() {
		return "You are being given a LenovoLaptop";
	}

}
