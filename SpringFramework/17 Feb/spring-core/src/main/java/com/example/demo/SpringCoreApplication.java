package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
//@ComponentScan(basePackages = {"com.example.demo.models","com.example.demo"}) //If packages to be scanned are less better add this way than adding the whole umbrella for scanning everytime
//If we don't put this then the other package components wont be scanned. As models is another package we must add the super package
public class SpringCoreApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SpringCoreApplication.class, args);
		
		Object obj1 = ctx.getBean("std"); //calling from MyJavaConfiguration class 
		Object obj2 = ctx.getBean("getEmployee");//calling from MyJavaConfiguration class 
		Object obj3 = ctx.getBean("addr_s");
		Object obj4 = ctx.getBean("emp_s");
		
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj3);
		System.out.println(obj4);
	}

}
