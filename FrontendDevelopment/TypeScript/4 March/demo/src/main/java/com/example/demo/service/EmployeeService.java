package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(emp -> {
            emp.setName(updatedEmployee.getName());
            emp.setRole(updatedEmployee.getRole());
            emp.setSalary(updatedEmployee.getSalary());
            return employeeRepository.save(emp);
        });
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Employee> searchEmployees(String search, String searchBy) {
        switch (searchBy.toLowerCase()) {
            case "name":
                return employeeRepository.findByNameContainingIgnoreCase(search);
            case "role":
                return employeeRepository.findByRoleContainingIgnoreCase(search);
            case "id":
                return employeeRepository.findById(Long.parseLong(search)).map(List::of).orElse(List.of());
            default:
                return List.of();
        }
    }
}
