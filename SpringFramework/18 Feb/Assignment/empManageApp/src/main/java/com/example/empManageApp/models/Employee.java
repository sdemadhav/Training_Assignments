package com.example.empManageApp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class Employee {
	
	private int id;
    private String name;
    private String designation;
    private String department;
    private int salary;
    private int age;

    @Autowired
    private Address address;

    private static final List<Employee> empList = new ArrayList<>();
    private static int empCount = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public Employee(String designation, int salary) {
        this.designation = designation;
        this.salary = salary;
        collectEmployeeDetails();
        addEmployee(this);
        System.out.println("---------------- Employee Added -----------------");
    }

    private void addEmployee(Employee employee) {
        empList.add(employee);
        empCount++;
    }

    private void collectEmployeeDetails() {
    	System.out.print("Enter Employee ID: ");
        this.id = scanner.nextInt();
        
        System.out.print("Enter " + designation + " name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter " + designation + " department: ");
        this.department = scanner.nextLine();

        System.out.print("Enter age: ");
        this.age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        collectAddressDetails();
    }

    private void collectAddressDetails() {
        if (address == null) {
            address = new Address();
        }
        
        System.out.print("Enter city name: ");
        address.setCity(scanner.nextLine());

        System.out.print("Enter state name: ");
        address.setState(scanner.nextLine());

        System.out.print("Enter pincode: ");
        address.setPincode(scanner.nextInt());
        scanner.nextLine(); 
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public int getSalary() { return salary; }
    public void setSalary(int salary) { this.salary = salary; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Address getAddress() { return address; }
    
    public static List<Employee> getEmpList() {
    	return empList; 
    	}
    public static void setEmpList(List<Employee> empList) {
    	Employee.empList.clear(); Employee.empList.addAll(empList); 
    	}

    @Override
    public String toString() {
        return "Employee [name=" + name + ", designation=" + designation + ", department=" + department + ", salary="
                + salary + ", age=" + age + ", address=" + address + "]";
    }
}
