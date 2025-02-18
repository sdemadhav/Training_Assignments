package com.example.empManageApp.service;

import com.example.empManageApp.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
        System.out.println("Employee added successfully: " + employee);
    }

    public void displayEmployees(String sortBy) {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }

        List<Employee> sortedList = switch (sortBy.toLowerCase()) {
            case "designation" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getDesignation))
                    .collect(Collectors.toList());
            case "id" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getId)) 
                    .collect(Collectors.toList());
            case "name" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getName))
                    .collect(Collectors.toList());
            case "age" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getAge))
                    .collect(Collectors.toList());
            case "salary" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getSalary))
                    .collect(Collectors.toList());
            case "department" -> employeeList.stream()
                    .sorted(Comparator.comparing(Employee::getDepartment))
                    .collect(Collectors.toList());
            default -> employeeList;
        };

        sortedList.forEach(System.out::println);
    }

    public void raiseEmployeeSalary(int empId) {
        Optional<Employee> employee = employeeList.stream()
                .filter(e -> e.getId() == empId)
                .findFirst();

        employee.ifPresentOrElse(
            e -> {
                e.setSalary(e.getSalary() + (e.getSalary() * 10 / 100));
                System.out.println("Salary updated for: " + e);
            },
            () -> System.out.println("Employee not found!")
        );
    }

    public void removeEmployee(int empId) {
        boolean removed = employeeList.removeIf(e -> e.getId() == empId);
        if (removed) {
            System.out.println("Employee removed successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

}
