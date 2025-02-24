package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    private int standard;
    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students;


    public int getStandard() {
		return standard;
	}


	public void setStandard(int standard) {
		this.standard = standard;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Student> getStudents() {
		return students;
	}


	public void setStudents(List<Student> students) {
		this.students = students;
	}


	@Override
    public String toString() {
        return "Teacher [name=" + name + ", standard=" + standard + "]";
    }
}
