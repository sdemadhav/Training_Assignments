package com.example.demo.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Student") //now everywhere the class will be referred by "Student"
public class Student implements Person{

	
	@Value("${rno}")
	private int rollNo;
	@Value("${sname}") //Default value
	private String name;
	@Value("${rstd}") //Default value
	private int standard;
	
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", standard=" + standard + "]";
	}
	
	
}
