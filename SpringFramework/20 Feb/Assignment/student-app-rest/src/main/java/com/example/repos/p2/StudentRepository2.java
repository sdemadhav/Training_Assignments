package com.example.repos.p2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.Student;

@Repository
public interface StudentRepository2 extends JpaRepository<Student, Integer> {
}
