package com.example.empManageApp;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.example.empManageApp.ui.MainMenu;
import com.example.empManageApp.ui.Menu;

@SpringBootApplication
@ComponentScan(basePackages =  {"com.example.empManageApp"})
public class EmpManage {

	public static void main(String[] args) {
		ApplicationContext ctx=  SpringApplication.run(EmpManage.class, args);

		        int ch1 = 0, ch2 = 0, ch3 = 0, ch4 = 0;
		        MainMenu employeeOperations = new MainMenu();
		        do {
		            System.out.println("-------------------------------------");
		            System.out.println("1. Create Employee");
		            System.out.println("2. Display");
		            System.out.println("3. Appraisal");
		            System.out.println("4. Remove");
		            System.out.println("5. Search");
		            System.out.println("6. Exit");
		            System.out.println("-------------------------------------");
		            try {
		                ch1 = Menu.readChoice(6);
		            } catch (Exception e) {
		                System.out.println("Error reading choice: " + e.getMessage());
		            }
		            switch (ch1) {
		                case 1:
		                    do {
		                        System.out.println("---------------------------------------------");
		                        System.out.println("1. Create Clerk");
		                        System.out.println("2. Create Programmer");
		                        System.out.println("3. Create Manager");
		                        System.out.println("4. Others");
		                        System.out.println("5. Back");
		                        System.out.println("---------------------------------------------");
		                        try {
		                            ch2 = Menu.readChoice(5);
		                        } catch (IOException e) {
		                            System.out.println("Error reading choice: " + e.getMessage());
		                        }
		                        switch (ch2) {
		                            case 1:
		                                employeeOperations.storeEmployee(EmpFactory.createEmp("Clerk"));
		                                break;
		                            case 2:
		                                employeeOperations.storeEmployee(EmpFactory.createEmp("Programmer"));
		                                break;
		                            case 3:
		                                employeeOperations.storeEmployee(EmpFactory.createEmp("Manager"));
		                                break;
		                            case 4:
		                                employeeOperations.storeEmployee(EmpFactory.createEmp("Others"));
		                                break;
		                            case 5:
		                                ch2 = 5;
		                                break;
		                        }
		                    } while (ch2 != 5);
		                    break;

		                case 2:
		                    do {
		                        System.out.println("---------------------------------------------");
		                        System.out.println("Sorted Based On the");
		                        System.out.println("1. Designation");
		                        System.out.println("2. ID");
		                        System.out.println("3. Name");
		                        System.out.println("4. Age");
		                        System.out.println("5. Salary");
		                        System.out.println("6. Department");
		                        System.out.println("7. Exit");
		                        System.out.println("---------------------------------------------");
		                        try {
		                            ch3 = Menu.readChoice(7);
		                        } catch (IOException e) {
		                            System.out.println("Error reading choice: " + e.getMessage());
		                        }
		                        switch (ch3) {
		                            case 1 :
		                            employeeOperations.displayEmployee("Designation");
		                            break;
		                            case 2 :
		                            employeeOperations.displayEmployee("ID");
		                            break;
		                            case 3 :
		                            employeeOperations.displayEmployee("Name");
		                            break;
		                            case 4 :
		                            employeeOperations.displayEmployee("Age");
		                            break;
		                            case 5 :
		                            employeeOperations.displayEmployee("Salary");
		                            break;
		                            case 6 :
		                            employeeOperations.displayEmployee("Department");
		                            break;
		                            case 7 :
		                            ch3 = 7;
		                        }
		                    } while (ch3 != 7);

		                    break;

		                case 3:
		                    int eid = IdInput.readId();
		                    employeeOperations.raiseEmployeeSalary(eid);
		                    break;

		                case 4:
		                    System.out.println("Enter the Employee ID to delete:");
		                    int empIdToRemove = IdInput.readId();
		                    employeeOperations.deleteEmployee(empIdToRemove);
		                    break;
		                case 5:
		                    do {
		                        System.out.println("---------------------------------------------");
		                        System.out.println("Search Based On the");
		                        System.out.println("1. Designation");
		                        System.out.println("2. ID");
		                        System.out.println("3. Name");
		                        System.out.println("4. Department");
		                        System.out.println("5. Exit");
		                        System.out.println("---------------------------------------------");
		                        try {
		                            ch4 = Menu.readChoice(5);
		                        } catch (IOException e) {
		                            System.out.println("Error reading choice: " + e.getMessage());
		                        }
		                        switch (ch4) {
		                            case 1:
		                                String designation = DesignationInput.readDesignation();
		                                employeeOperations.searchEmployee("Designation", designation);
		                                break;
		                            case 2:
		                                int searchEid = IdInput.readId();
		                                employeeOperations.searchEmployeeBasedOnId(searchEid);
		                                break;
		                            case 3:
		                                String Name = NameInput.readName();
		                                employeeOperations.searchEmployee("Name", Name);
		                                break;
		                            case 4:
		                                String Department = DepartmentInput.readDepartment();
		                                employeeOperations.searchEmployee("Department", Department);
		                                break;
		                            case 5:
		                                ch4 = 5;
		                                break;
		                        }
		                    } while (ch4 != 5);
		                    break;

		                case 6:
		                    System.out.println("Exiting...");
		                    break;

		                default:
		                    System.out.println("Invalid choice. Try again.");
		            }
		        } while (ch1 != 6);

		    
}
		
