package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {
    
    @Id
    private int univRegNo;
    private int rollNo;
    private String name;
    private int standard;
    private String schoolName;
    private double percentage;

    public int getUnivRegNo() {
		return univRegNo;
	}


	public void setUnivRegNo(int univRegNo) {
		this.univRegNo = univRegNo;
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


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	@ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "standard", insertable = false, updatable = false)
    private Teacher teacher;


    @Override
    public String toString() {
        return "Student [univRegNo=" + univRegNo + ", rollNo=" + rollNo + ", name=" + name + ", standard=" + standard
                + ", schoolName=" + schoolName + ", percentage=" + percentage + "]";
    }
}
