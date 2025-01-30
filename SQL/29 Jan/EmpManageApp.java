import java.util.*;
import java.util.regex.*;

import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.sql.*;
import java.io.*;

// New class for Displaying Employee Options in one place rather than 
class DisplayEmployeeOptions {

    ResultSet resultSet = null;

    public static void displayEmployees() throws Exception {
        int employeeCount = 0;
        DisplayEmployeeOptions obj = new DisplayEmployeeOptions();
    
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
             Statement stmt = con.createStatement()) {
             
            obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App");
            while (obj.resultSet.next()) {
                employeeCount++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        if (employeeCount == 0) {
            System.out.println("No Employee Records Found");
            return;
        }
    
        System.out.println("Enter choice to sort by: ");
        System.out.println("1. ID Wise");
        System.out.println("2. Name Wise");
        System.out.println("3. Designation Wise");
        System.out.println("4. Age Wise");
        System.out.println("5. Salary Wise");
        System.out.println("6. Department Wise");
        System.out.println("7. Back");
    
        int ch = Menu.readChoice(6);
    
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
             Statement stmt = con.createStatement()) {
             
            switch (ch) {
                case 1:
                    obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by eid asc");
                    break;
                case 2:
                    obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_name");
                    break;
                case 3:
                    obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_designation");
                    break;
                case 4:
                    obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_age");
                    break;
                case 5:
                    System.out.println("What order do you want to display in?");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int choice = Menu.readChoice(2);
                    if (choice == 1) {
                        obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_salary");
                    } else {
                        obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_salary desc");
                    }
                    break;
                case 6:
                    obj.resultSet = stmt.executeQuery("Select * from EMP_Manage_App order by e_department");
                    break;
                default:
                    System.out.println("Invalid Choice, please select from the given options");
                    return;
            }
    
            System.out.println("Employee Details: ");
            while (obj.resultSet.next()) {
                System.out.println("Employee ID: " + obj.resultSet.getInt(1));
                System.out.println("Name: " + obj.resultSet.getString(2));
                System.out.println("Age: " + obj.resultSet.getInt(3));
                System.out.println("Salary: " + obj.resultSet.getInt(4));
                System.out.println("Designation: " + obj.resultSet.getString(5));
                System.out.println("Department: " + obj.resultSet.getString(6));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        if (ch != 7) {
            System.out.println("Total Employees: " + employeeCount);
        }
    }
}
    


// SearchEmployeeOptions class for searching employee by various fields
class SearchEmployeeOptions {
    public static void searchEmployee() {
        

        System.out.println("Search by? ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Designation");
        int choice = Menu.readChoice(3);

        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
        Statement stmt = con.createStatement()) 
        {
            String query = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    int id = Integer.parseInt(br.readLine());
                    query = "Select * from EMP_Manage_App where eid = " + id;
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    String name = br.readLine();
                    query = "Select * from EMP_Manage_App where e_name = '" + name + "'";
                    break;
                case 3:
                    System.out.println("Enter Designation: ");
                    String designation = br.readLine();
                    query = "Select * from EMP_Manage_App where e_designation = '" + designation + "'";
                    break;
            }
            ResultSet resultSet = stmt.executeQuery(query);

            System.out.println("Employee Details: ");
            while (resultSet.next()) {
                System.out.println("Employee ID: " + resultSet.getInt(1));
                System.out.println("Name: " + resultSet.getString(2));
                System.out.println("Age: " + resultSet.getInt(3));
                System.out.println("Salary: " + resultSet.getInt(4));
                System.out.println("Designation: " + resultSet.getString(5));
                System.out.println("Department: " + resultSet.getString(6));
                System.out.println();
            }
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    
// Abstract Employee class
abstract class Emp {
    public int eid;
    public String name;
    public int age;
    public float salary;
    public String designation;

    static int countEmp = 0;

    Emp(float salary, String designation) throws Exception {
        Scanner sc = new Scanner(System.in);
        this.eid = EmployeeValidation.readID();
        this.name = EmployeeValidation.readName();
        this.age = EmployeeValidation.readAge(21, 60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
    }

    Emp(int eid, String name, int age, float salary, String designation) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;
    }


    public final void display() {

        System.out.println("Employee ID: " + eid);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + designation);
        System.out.println();
    }

    public abstract void raiseSalary();
}

class Clerk extends Emp {
    Clerk() throws Exception {
        super(20000, "Clerk");
    }
    Clerk(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 2000;
    }
}

class Programmer extends Emp{
    Programmer() throws Exception{
        super(30000, "Programmer");
    }

    Programmer(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 5000;
    }
}

class Manager extends Emp{
    Manager() throws Exception {
        super(100000, "Manager");
    }
    Manager(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 15000;
    }
}

class CEO extends Emp {
     private static boolean ceoCreated = false;

    CEO() throws Exception{
        super(500000, "CEO");
    }

    private CEO(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
        CEO.ceoCreated = true;
        
    }

    public static CEO getCeoInstance(int id, String name, int age, float salary, String designation) {
        if(isCeoCreated()){
            return null;
        }
        return new CEO(id, name, age, salary, designation);
    }

    @Override
    public void raiseSalary() {
        salary += 10000;
    }

    public static boolean isCeoCreated() {
        return ceoCreated;
    }

    public static void setCeoCreated(boolean ceoCreated) {
        CEO.ceoCreated = ceoCreated;
    }
}

// Comparator classes for sorting based on different properties of Employee class


class Database {
    public static void saveData() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMP_Manage_App(e_name, e_age, e_salary, e_designation, e_department) values(?,?,?,?,?)");
            
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Salary: ");
            int salary = Integer.parseInt(br.readLine());
            System.out.println("Enter Designation: ");
            String designation = br.readLine();
            System.out.println("Enter Department: ");
            String department = br.readLine();

            
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setInt(4, salary);
            pstmt.setString(5, designation);
            pstmt.setString(6, department);
            pstmt.execute();
            
            br.close();
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void saveCEO() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMP_Manage_App(e_name, e_age, e_salary, e_designation, e_department) values(?, ?, 50000, 'CEO', ?)");
            
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Department: ");
            String department = br.readLine();
    
            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.execute();
            
            br.close();
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public static void saveClerk() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMP_Manage_App(e_name, e_age, e_salary, e_designation, e_department) values(?, ?, 10000, 'CLERK', ?)");
            
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Department: ");
            String department = br.readLine();

            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.execute();
            
            br.close();
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void saveManager() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMP_Manage_App(e_name, e_age, e_salary, e_designation, e_department) values(?,?,30000,'Manager',?)");
            
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Department: ");
            String department = br.readLine();

            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.execute();
            
            br.close();
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void saveProgrammer(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMP_Manage_App(e_name, e_age, e_salary, e_designation, e_department) values(?,?,50000,'Programmer',?)");

            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Department: ");
            String department = br.readLine();

            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.execute();
            
            br.close();
            pstmt.close();
            con.close();    
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class EmpManageApp {
    static HashMap<Integer, Emp> emp = new HashMap<>();

    public static void main(String[] args) throws Exception {
        
        int ch1 = 0, ch2 = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Appraisal");
            System.out.println("4. Delete a Record");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");

            ch1 = Menu.readChoice(6);

            switch (ch1) {
                case 1:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. Create CEO");
                        System.out.println("2. Create Clerk");
                        System.out.println("3. Create Programmer");
                        System.out.println("4. Create Manager");
                        System.out.println("5. Others");
                        System.out.println("6. Back");
                        System.out.println("--------------------------------------------");

                        ch2 = Menu.readChoice(6);

                        switch (ch2) {
                            case 1:
                                Database.saveCEO();
                                break;
                            case 2:
                                Database.saveClerk();
                                break;
                            case 3:
                                Database.saveProgrammer();
                                break;
                            case 4:
                                Database.saveManager();
                                break;
                            case 5:
                                Database.saveData();
                                break;
                        }
                        
                    } while (ch2 != 6);
                    break;

                case 2:
                    
                    DisplayEmployeeOptions.displayEmployees();
                    break;

                case 3:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    } else {
                        for (Emp e : emp.values()) {
                            e.raiseSalary();
                        }
                    }
                    break;

                case 4:
                    //Add delete Employee Logic

                    break;
                case 5:
                    SearchEmployeeOptions.searchEmployee();
                    break;
            }
        } while (ch1 != 6);
        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}

// Custom Exception Classes
class AgeCustomException extends RuntimeException {
    public AgeCustomException() {
        super();
    }

    public AgeCustomException(String msg) {
        super(msg);
    }
}

class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException() {
        super();
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }
}

class InvalidNameFormatException extends RuntimeException {
    public InvalidNameFormatException() {
        super();
    }

    public InvalidNameFormatException(String msg) {
        super(msg);
    }

    public void display() {
        String rules = " The following rules must be followed:\n\t1. Name must have 2 words.\n\t2. Both first and last names must be entered.\n\t3. Both first and last names must start with a capital letter.\n";
        System.out.println(rules);
    }
}

// Menu class
class Menu {
    private static int maxChoice;

    public static int readChoice(int max) {
        maxChoice = max;
        int choice=0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Choice: ");
            choice = scanner.nextInt();
            try {
                    if (choice < 1 || choice > maxChoice) throw new InvalidChoiceException();
                    return choice;
                    
            } catch (InputMismatchException e) {
                System.out.println("Please enter number only");
            } catch (InvalidChoiceException e) {
                System.out.println("Please enter from 1 to " + max);
            }
        }
    }
}

// Employee Validation class
class EmployeeValidation {
    static int readAge(int min, int max) {
        int age;
        System.out.println("Enter your age: ");
        while (true) {
            try {
                age = new Scanner(System.in).nextInt();
                if (age < min || age > max) throw new AgeCustomException("Age must be between " + min + " and " + max);
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Please enter number only");
            } catch (AgeCustomException e) {
                System.out.println("Reason: " + e.getMessage());
            }
        }
    }

    static String readName() {
        while (true) {
            System.out.println("Enter your name: ");
            try {
                String name = new Scanner(System.in).nextLine();
                String format = "^[A-Z][a-z]*\\s[A-Z][a-z]*$";
                Pattern p1 = Pattern.compile(format);
                Matcher m1 = p1.matcher(name);
                if (!m1.matches()) throw new InvalidNameFormatException("Name format rules have not been followed.");
                return name;
            } catch (InvalidNameFormatException e) {
                e.display();
            }
        }
    }

    static int readID() {
        Scanner sc = new Scanner(System.in);
        int id;
        while (true) {
            System.out.println("Enter an emp ID: ");
            id = sc.nextInt();
            if (!EmpManageApp.emp.containsKey(id)) {
                return id;
            } else {
                System.out.println("ID already exists. Please enter a unique ID.");
            }
        }
    }
}

