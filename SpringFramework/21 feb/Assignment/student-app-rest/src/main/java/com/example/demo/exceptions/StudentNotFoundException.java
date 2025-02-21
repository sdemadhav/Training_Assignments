package com.example.demo.exceptions;

public class StudentNotFoundException extends Exception {

	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String msg) {
		super(msg);
	}

}
