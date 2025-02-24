package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repo.StudentDao;

@Service
public class StudentService {

    @Autowired
    private StudentDao dao;

    @Autowired
    private TeacherService ts;

    public List<Student> getStudentList() {
        return dao.findAll();
    }

    public Optional<Student> getStudentByunivRegNo(int univRegNo) throws StudentNotFoundException {
        Optional<Student> student = dao.findById(univRegNo);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student with roll number " + univRegNo + " not found");
        }
        return student;
    }

    public List<Student> getStudentsBySchoolName(String schoolName) {
        return dao.findAll().stream().filter(s -> s.getSchoolName().equals(schoolName)).collect(Collectors.toList());
    }

    public List<Student> getPassedOrFailedStudents(boolean pass) {
        return dao.findAll().stream().filter(s -> pass ? s.getPercentage() >= 40 : s.getPercentage() < 40)
                .collect(Collectors.toList());
    }

    public long getCountOfStudentsInStandard(int standard) {
        return dao.findAll().stream().filter(s -> s.getStandard() == standard).count();
    }

    public long getTotalStrengthForSchool(String schoolName) {
        return dao.findAll().stream().filter(s -> s.getSchoolName().equals(schoolName)).count();
    }

    public List<Student> getTopStudents() {
        return dao.findAll().stream().sorted(Comparator.comparingDouble(Student::getPercentage).reversed()).limit(5)
                .collect(Collectors.toList());
    }

    public Optional<Student> getTopperOfStandard(int standard) {
        return dao.findAll().stream().filter(s -> s.getStandard() == standard)
                .sorted(Comparator.comparingDouble(Student::getPercentage).reversed()).findFirst();
    }

    public String addStudent(Student s) {
        if (dao.existsById(s.getUnivRegNo())) {
            return "Student ID already exists, please choose another ID";
        } else {
            dao.save(s);
            return "New student added successfully";
        }
    }

    public String updateStudent(Student s) {
        if (dao.existsById(s.getUnivRegNo())) {
            dao.save(s);
            return "student updated successfully";
        }
        return "Student ID does not exist, check ID properly";
    }

    public String deleteStudentById(int univRegNo) {
        if (dao.existsById(univRegNo)) {
            dao.deleteById(univRegNo);
            return "Student with id " + univRegNo + " deleted successfully !";
        }
        return "Student ID does not exist, check ID properly";
    }

    public String getClassTeacher(int reg_no) throws StudentNotFoundException {
        Optional<Student> studentOpt = dao.findById(reg_no);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            Teacher teacher = student.getTeacher();
            if (teacher != null) {
                return "Teacher name is :"+teacher.getName();
            } else {
                return "No teacher found for standard " + student.getStandard();
            }
        } else {
            throw new StudentNotFoundException("Student with roll number " + reg_no + " not found.");
        }
    }
}
