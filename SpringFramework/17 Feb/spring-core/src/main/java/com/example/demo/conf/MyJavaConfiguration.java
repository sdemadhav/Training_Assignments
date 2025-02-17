package com.example.demo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Address;
import com.example.demo.models.Employee;
import com.example.demo.models.Student;

@Configuration
public class MyJavaConfiguration {

	@Bean
	//@Lazy
	public Employee getEmployee()
	{
		return new Employee("Raju",25,30000,"Tester",getAddress());
	}
	
	@Bean("std") //we can name also 
	public Student getStudent()
	{
		return new Student("Madhav",4,14);
	}
	
	@Bean("addr")
	public Address getAddress()
	{
		return new Address("Maharashtra","Mumbai",400054);
	}
	
	@Bean("emp_s")
	public Employee getEmployee2()
	{
		Employee e = new Employee();
		e.setName("Mohan Pyaare");
		e.setAge(23);
		e.setSalary(23000);
		e.setDesignation("Programmer");
		e.setAddress(getAddress());
		return e;
	}
	
	@Bean("addr_s")
	public Address getAddress2()
	{
		Address a1 = new Address();
		a1.setState("Karnataka");
		a1.setCity("Bengaluru");
		a1.setPin(345678);
		
		return a1;
	}
}
