package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.models.lappy.Laptop;

@Scope("prototype") //makes possible creation of multiple objects of the same class AND is by default Lazy Instantiation. Default is singleton which is by default EARLY instantiation.
@Component("emp")
@Lazy //Lazy initialization ensures there's no object created in the spring context even before we call getBean. Default is early
public class Employee implements Person{
	
	@Autowired
	private Address address;
	
	@Autowired
	@Qualifier("mac") 
	private Laptop laptop;
	
	Employee(){
		System.out.println("Employee Object Created");
	}
	
	@Value("Tarun")
	private String name;
	@Value("32")
	private int age;
	@Value("45000")
	private int salary;
	@Value("Senior Developer")
	private String designation;

	@Override
	public String toString() {
		return "Employee [address=" + address + ", laptop=" + laptop + ", name=" + name + ", age=" + age + ", salary="
				+ salary + ", designation=" + designation + "]";
	}
	
	
}
//
//package com.example.demo.models;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.models.lappy.Laptop;
//
//@Component("emp")
////@Lazy
//@Scope("prototype")
//public class Employee implements Person {
//	@Value("Rajesh")
//	private String name;
//	@Value("25")
//	private int age;
//	@Value("25000")
//	private int salary;
//	@Value("Intern")
//	private String designation;
//	@Autowired
//	private Address address;
//	@Autowired
//	@Qualifier("mac")
//	private Laptop laptop;
//	
//	public Employee() {
//		System.out.println("Employee Object Created");
//	}
//	
//	@Override
//	public String toString() {
//		System.out.println("Employee [name: " + name + ", age: " + age + ", Salary: " + salary + ", Designation: " + designation + " ]");
//		System.out.println(address);
//		System.out.println(laptop);
//		return "";
//	}
//}
