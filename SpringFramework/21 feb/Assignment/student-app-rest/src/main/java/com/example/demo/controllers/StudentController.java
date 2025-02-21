package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService ss;

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return ss.getStudentList();
    }

    @GetMapping("/{rollNo}")
    public Optional<Student> getStudentByRollNo(@PathVariable int rollNo) throws StudentNotFoundException {
        return ss.getStudentByunivRegNo(rollNo);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFound(StudentNotFoundException ex) {
        return ex.getMessage();
    }

    @GetMapping("/school")
    public String getStudentsBySchoolName(@RequestParam String schoolName) {
        List<Student> students = ss.getStudentsBySchoolName(schoolName);
        if (!students.isEmpty()) {
            StringBuilder response = new StringBuilder("Students from " + schoolName + ":\n");
            for (Student student : students) {
                response.append("Name: ").append(student.getName()).append(", Roll No: ").append(student.getRollNo()).append("\n");
            }
            return response.toString();
        } else {
            return "No students found from " + schoolName;
        }
    }

    @GetMapping("/result")
    public String getPassedOrFailedStudents(@RequestParam boolean pass) {
        List<Student> students = ss.getPassedOrFailedStudents(pass);
        if (!students.isEmpty()) {
            StringBuilder response = new StringBuilder("Students who have " + (pass ? "passed" : "failed") + ":\n");
            for (Student student : students) {
                response.append("Name: ").append(student.getName())
                        .append(", Roll No: ").append(student.getRollNo())
                        .append(", Percentage: ").append(student.getPercentage())
                        .append("\n");
            }
            return response.toString();
        } else {
            return "No students found who have " + (pass ? "passed" : "failed") + ".";
        }
    }

    @GetMapping("/{standard}/count")
    public String getCountOfStudentsInStandard(@PathVariable int standard) {
        long count = ss.getCountOfStudentsInStandard(standard);
        return "There are " + count + " students in the " + standard + "th standard.";
    }

    @GetMapping("/strength")
    public String getTotalStrengthForSchool(@RequestParam String schoolName) {
        long count = ss.getTotalStrengthForSchool(schoolName);
        return "The total strength for school " + schoolName + " is " + count + " students.";
    }

    @GetMapping("/toppers")
    public String getTopStudents() {
        List<Student> students = ss.getTopStudents();
        if (!students.isEmpty()) {
            StringBuilder response = new StringBuilder("Top 5 students by percentage:\n");
            for (Student student : students) {
                response.append("Name: ").append(student.getName())
                        .append(", Roll No: ").append(student.getRollNo())
                        .append(", Percentage: ").append(student.getPercentage())
                        .append("\n");
            }
            return response.toString();
        } else {
            return "No students found.";
        }
    }

    @GetMapping("/topper/{standard}")
    public String getTopperOfStandard(@PathVariable int standard) {
        Optional<Student> topper = ss.getTopperOfStandard(standard);
        if (topper.isPresent()) {
            return "Topper of " + standard + "th standard:\n" +
                   "Name: " + topper.get().getName() + ", Roll No: " + topper.get().getRollNo() +
                   ", Percentage: " + topper.get().getPercentage();
        } else {
            return "No students found in " + standard + "th standard.";
        }
    }
    
    @PostMapping("/save")
    public String insertStudent(@RequestBody Student s) {
        return ss.addStudent(s);
    }
    
    @PutMapping("/update")
    public String updateStudent(@RequestBody Student s)
    {
    	return ss.updateStudent(s);
    }
    
    @PutMapping("/delete")
    public String deleteStudent(@RequestParam int rollNo)
    {
    	return ss.deleteStudentById(rollNo);
    }
    
    @GetMapping("/class_teacher")
    public String getClassTeacher(@RequestParam int reg_no) {
    	return ss.getClassTeacher(reg_no);
    }
}
