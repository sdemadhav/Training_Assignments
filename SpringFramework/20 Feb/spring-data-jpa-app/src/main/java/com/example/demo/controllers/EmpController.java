package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;

@RestController
public class EmpController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> getEmployeeById(@RequestParam int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/employees")
    public String insertEmployee(@RequestBody Employee e) {
        return employeeService.insertEmployee(e);
    }

    @RequestMapping(path = "/update/{id}", method = { RequestMethod.PUT, RequestMethod.PATCH })
    public String updateEmployee(@RequestBody Employee e, @PathVariable int id) {
        return employeeService.updateEmployee(e, id);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/custom")
    public List<Employee> getCustomQuery(String desig) {
        return employeeService.getCustomQuery(desig);
    }

    @GetMapping("/employees/below")
    public List<Employee> getEmployeeBelow(int age) {
        return employeeService.getEmployeeBelow(age);
    }

    @GetMapping("/employees/above")
    public List<Employee> getEmployeeAbove(int age) {
        return employeeService.getEmployeeAbove(age);
    }

    @GetMapping("/employees/role")
    public List<Employee> getEmployeeByDesig(String desig) {
        return employeeService.getEmployeeByDesig(desig);
    }
}
