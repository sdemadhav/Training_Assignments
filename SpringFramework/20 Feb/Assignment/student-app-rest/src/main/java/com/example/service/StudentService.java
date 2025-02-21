package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Student;
import com.example.repos.p1.StudentRepository;
import com.example.repos.p2.StudentRepository2;

import java.util.List;



@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository1;
    @Autowired
    private StudentRepository2 studentRepository2;

    public List<Student> getAllStudents() {
        return studentRepository1.findAll();
    }

    public void  saveStudent(Student student) {
        studentRepository1.save(student);
        studentRepository2.save(student);
    }

    public Student getStudentById(int rollNo) {
        return studentRepository1.findById(rollNo).orElse(null);
    }

    public void deleteStudent(int rollNo) {
        studentRepository1.deleteById(rollNo);
    }
}
