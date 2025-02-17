package com.example.demo.models;

public class Student {

	private int std;
	private String name;
	private int age;
	
	public Student(String name, int std, int age)
	{
		this.name = name;
		this.std = std;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Student [std=" + std + ", name=" + name + ", age=" + age + "]";
	}
	
}
