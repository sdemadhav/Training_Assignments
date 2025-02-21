package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Teacher;

public interface TeacherDao extends JpaRepository<Teacher, Integer>{

}
