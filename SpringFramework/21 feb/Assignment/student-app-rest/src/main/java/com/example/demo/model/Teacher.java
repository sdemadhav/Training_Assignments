package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Teacher")
public class Teacher {

	private String name;
	@Id
	private int standard;
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
	@Override
	public String toString() {
		return "Teacher [name=" + name + ", standard=" + standard + "]";
	}
	
	
}
