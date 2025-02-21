package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.StudentController;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student createDemoStudent() {
        Student student = new Student();
        student.setName("Madhav Jha");
        student.setRollNo(9543);
        student.setPercentage(99.99);
        student.setStandard(4);
        student.setSchoolName("CRCE");
        return student;
    }

    @Test
    public void testGetAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(createDemoStudent());
        Mockito.when(studentService.getStudentList()).thenReturn(students);

        mockMvc.perform(get("/students/"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentByRollNo() throws Exception {
        Optional<Student> student = Optional.of(createDemoStudent());
        Mockito.when(studentService.getStudentByunivRegNo(9543)).thenReturn(student);

        mockMvc.perform(get("/students/9543"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetStudentsBySchoolName() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(createDemoStudent());
        Mockito.when(studentService.getStudentsBySchoolName("CRCE")).thenReturn(students);

        mockMvc.perform(get("/students/school").param("schoolName", "CRCE"))
               .andExpect(status().isOk());
    }

    @Test
    public void testGetPassedOrFailedStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(createDemoStudent());
        Mockito.when(studentService.getPassedOrFailedStudents(true)).thenReturn(students);

        mockMvc.perform(get("/students/result").param("pass", "true"))
               .andExpect(status().isOk());
    }

    @Test
    public void testAddStudent() throws Exception {
        Student demoStudent = createDemoStudent();
        Mockito.when(studentService.addStudent(demoStudent)).thenReturn("new student added successfully");

        mockMvc.perform(post("/students/save")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(demoStudent)))
               .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student demoStudent = createDemoStudent();
        Mockito.when(studentService.updateStudent(demoStudent)).thenReturn("new student added successfully");

        mockMvc.perform(put("/students/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(demoStudent)))
               .andExpect(status().isOk());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Mockito.when(studentService.deleteStudentById(9543)).thenReturn("Student with id 9543 deleted successfully !");

        mockMvc.perform(put("/students/delete").param("rollNo", "9543"))
               .andExpect(status().isOk());
    }
}
