package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> studentList = new ArrayList<Student>();

    public StudentService() {
        studentList.add(new Student(1, "Aarav", 10, "DPS School", 92.5));
        studentList.add(new Student(2, "Ananya", 9, "St. Xavier's", 89.3));
        studentList.add(new Student(3, "Vihaan", 11, "Modern School", 85.4));
        studentList.add(new Student(4, "Diya", 10, "National Public School", 91.8));
        studentList.add(new Student(5, "Arjun", 12, "Kendriya Vidyalaya", 88.9));
        studentList.add(new Student(6, "Ishaan", 8, "DAV School", 87.0));
        studentList.add(new Student(7, "Mira", 9, "Ryan International", 90.2));
        studentList.add(new Student(8, "Kabir", 11, "The Bishop's School", 89.5));
        studentList.add(new Student(9, "Saanvi", 12, "Navy Children School", 93.4));
        studentList.add(new Student(10, "Rohan", 10, "Army Public School", 85.1));
        studentList.add(new Student(11, "Aditi", 9, "Tagore International", 88.2));
        studentList.add(new Student(12, "Dev", 11, "Holy Angels", 91.0));
        studentList.add(new Student(13, "Naina", 12, "Sacred Heart School", 89.7));
        studentList.add(new Student(14, "Rudra", 10, "Cambridge School", 92.1));
        studentList.add(new Student(15, "Tara", 11, "Loreto Convent", 87.8));
        studentList.add(new Student(16, "Atharv", 8, "St. Joseph's School", 90.9));
        studentList.add(new Student(17, "Leela", 9, "Bishop Cotton School", 93.0));
        studentList.add(new Student(18, "Reyansh", 12, "Delhi Public School", 88.4));
        studentList.add(new Student(19, "Vanya", 10, "Jubilee Hills Public School", 91.3));
        studentList.add(new Student(20, "Aditya", 11, "Carmel Convent", 90.1));
        studentList.add(new Student(21, "Kaira", 12, "Springdales School", 87.5));
        studentList.add(new Student(22, "Neil", 10, "Greenwood High", 88.7));
        studentList.add(new Student(23, "Riya", 9, "Baldwin Boys' High School", 89.6));
        studentList.add(new Student(24, "Om", 11, "St. Mary's School", 85.3));
        studentList.add(new Student(25, "Pia", 12, "Frank Anthony Public School", 92.2));
        studentList.add(new Student(26, "Yash", 10, "Sarala Birla Academy", 91.9));
        studentList.add(new Student(27, "Sneha", 11, "Loyola School", 89.0));
        studentList.add(new Student(28, "Aryan", 8, "Good Shepherd International School", 88.1));
        studentList.add(new Student(29, "Kiara", 9, "The Valley School", 90.6));
        studentList.add(new Student(30, "Vivaan", 12, "Sishya School", 93.5));
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
