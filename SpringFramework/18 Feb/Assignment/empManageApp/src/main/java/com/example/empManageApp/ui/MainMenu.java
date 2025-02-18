package com.example.empManageApp.ui;

import com.example.empManageApp.models.Employee;
import com.example.empManageApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MainMenu {

    @Autowired
    private EmployeeService employeeService;

    public void storeEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    public void displayEmployee(String criteria) {
        employeeService.displayEmployees(criteria);
    }

    public void raiseEmployeeSalary(int empId) {
        boolean success = employeeService.appraiseEmployee(empId);
        if (success) {
            System.out.println("Salary updated successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void deleteEmployee(int empId) {
        boolean success = employeeService.removeEmployee(empId);
        if (success) {
            System.out.println("Employee removed successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public void searchEmployee(String criteria, String value) {
        List<Employee> employees = employeeService.searchEmployee(criteria, value);
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
        } else {
            employees.forEach(System.out::println);
        }
    }

    public void searchEmployeeBasedOnId(int empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        if (employee != null) {
            System.out.println(employee);
        } else {
            System.out.println("Employee not found!");
        }
    }
}
