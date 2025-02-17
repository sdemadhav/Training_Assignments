package com.example.demo;

import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.models.Person;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		BeanFactory factory = SpringApplication.run(DemoApplication.class, args);
		
		System.out.println("Welcome to my first springboot project");
		System.out.println("Enter the class name");
		//Type emp if u want employee object, else Student if u want Student object
		String obj = new Scanner(System.in).next();
		Person p1 = (Person)factory.getBean(obj);
		System.out.println(p1);
		
	}

}
