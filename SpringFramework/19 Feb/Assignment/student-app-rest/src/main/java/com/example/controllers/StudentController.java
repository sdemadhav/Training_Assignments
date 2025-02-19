package com.example.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.model.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	StudentService ss = new StudentService();
	
	@GetMapping(value ="/")
	public List<Student> getAllStudents()
	{
		return ss.getStudentList();
	}
	
	@GetMapping("/{rollNo}")
	public String getStudentByRollNo(@PathVariable int rollNo) {
	    List<Student> temp = ss.getStudentList();
	    
	    Student student = temp.stream()
	                          .filter(s ->s.getRollNo() == rollNo)
	                          .findFirst()
	                          .orElse(null);
	    
	    if (student != null) {
	        return "Student found: " + student.getName() + ", Roll No: " + student.getRollNo();
	    } else {
	        return "Student with Roll No: " + rollNo + " not found.";
	    }
	}
	
	@GetMapping("/school")
	public String getStudentsBySchoolName(@RequestParam String schoolName) {
		    
	    List<Student> students = ss.getStudentList().stream()
	                                 .filter(s -> s.getSchoolName().equals(schoolName))
	                                 .collect(Collectors.toList());
	    
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
	    List<Student> students = ss.getStudentList().stream()
	                               .filter(s -> pass ? s.getPercentage() >= 40 : s.getPercentage() < 40)
	                               .collect(Collectors.toList());
	    
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
	    List<Student> students = ss.getStudentList().stream()
	                               .filter(s -> s.getStandard() == standard)
	                               .collect(Collectors.toList());
	    
	    int count = students.size();
	    
	    return "There are " + count + " students in the " + standard + "th standard.";
	}
	
	@GetMapping("/strength")
	public String getTotalStrengthForSchool(@RequestParam String schoolName) {
	    List<Student> students = ss.getStudentList().stream()
	                               .filter(s -> s.getSchoolName().equals(schoolName))
	                               .collect(Collectors.toList());
	    
	    int count = students.size();
	    
	    return "The total strength for school " + schoolName + " is " + count + " students.";
	}
	
	@GetMapping("/toppers")
	public String getTopStudents() {
	    List<Student> students = ss.getStudentList().stream()
	                               .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
	                               .limit(5)
	                               .collect(Collectors.toList());
	    
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
	    List<Student> students = ss.getStudentList().stream()
	                               .filter(s -> s.getStandard() == standard)
	                               .sorted(Comparator.comparingDouble(Student::getPercentage).reversed())
	                               .collect(Collectors.toList());
	    
	    if (!students.isEmpty()) {
	        Student topper = students.get(0);
	        return "Topper of " + standard + "th standard:\n" +
	               "Name: " + topper.getName() + ", Roll No: " + topper.getRollNo() +
	               ", Percentage: " + topper.getPercentage();
	    } else {
	        return "No students found in " + standard + "th standard.";
	    }
	}
	
}
