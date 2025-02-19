package com.example.model;

public class Student {
	
	private int rollNo;
	private String name;
	private int standard;
	private String schoolName;
	private double percentage;
	
	public Student(){}
	public Student(int rollNo, String name, int standard, String schoolName, double percentage) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.standard = standard;
		this.schoolName = schoolName;
		this.percentage = percentage;
	}
	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
		
	

}
