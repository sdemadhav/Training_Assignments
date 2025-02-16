import java.util.*;
import java.util.regex.*;

import javax.xml.crypto.Data;

import java.sql.*;
import java.io.*;
import javax.sql.rowset.*;


// New class for Displaying Employee Options in one place rather than 
class DisplayEmployeeOptions {

private JdbcRowSet rowSet;

public void initializeRowSet() throws SQLException {
    rowSet = RowSetProvider.newFactory().createJdbcRowSet();
    rowSet.setUrl("jdbc:postgresql://localhost:5432/postgres");
    rowSet.setUsername("postgres");
    rowSet.setPassword("tiger");
    }


    public static void displayEmployees() throws Exception {
        int employeeCount = 0;
        DisplayEmployeeOptions obj = new DisplayEmployeeOptions();
        
        try {
            obj.initializeRowSet();
            obj.rowSet.setCommand("Select * from EMP_Manage_App");
            obj.rowSet.execute();
            while (obj.rowSet.next()) {
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
    
        int ch = Menu.readChoice(6,EmpManageApp.sc);
    
        try {
            obj.initializeRowSet();
            switch (ch) {
                case 1:
                    obj.rowSet.setCommand("Select * from EMP_Manage_App order by eid asc");
                    break;
                case 2:
                    obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_name");
                    break;
                case 3:
                    obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_designation");
                    break;
                case 4:
                    obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_age");
                    break;
                case 5:
                    System.out.println("What order do you want to display in?");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int choice = Menu.readChoice(2,EmpManageApp.sc);
                    if (choice == 1) {
                        obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_salary");
                    } else {
                        obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_salary desc");
                    }
                    break;
                case 6:
                    obj.rowSet.setCommand("Select * from EMP_Manage_App order by e_department");
                    break;
                default:
                    System.out.println("Invalid Choice, please select from the given options");
                    return;
            }
    
            obj.rowSet.execute();
    
            System.out.println("Employee Details: ");
            while (obj.rowSet.next()) {
                System.out.println("Employee ID: " + obj.rowSet.getInt(1));
                System.out.println("Name: " + obj.rowSet.getString(2));
                System.out.println("Age: " + obj.rowSet.getInt(3));
                System.out.println("Salary: " + obj.rowSet.getInt(4));
                System.out.println("Designation: " + obj.rowSet.getString(5));
                System.out.println("Department: " + obj.rowSet.getString(6));
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

class RaiseSalary
{
    public static void raiseSalary()
{
    System.out.println("Enter Employee ID to raise salary: ");
    int id = new Scanner(System.in).nextInt();
    try (JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet()) {
        rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
        rs.setUsername("postgres");
        rs.setPassword("tiger");
        rs.setCommand("SELECT * FROM emp_manage_app WHERE eid = ?");
        rs.setInt(1, id);
        rs.execute();
        if (rs.next()) {
            float currentSalary = rs.getFloat("e_salary");
            System.out.println("Current Salary: " + currentSalary);
            System.out.println("Enter raise amount: ");
            float raiseAmount = new Scanner(System.in).nextFloat();
            float newSalary = currentSalary + raiseAmount;

            rs.setCommand("UPDATE emp_manage_app SET e_salary = ? WHERE eid = ?");
            rs.setFloat(1, newSalary);
            rs.setInt(2, id);
            rs.execute();
            
            rs.setCommand("SELECT e_salary FROM emp_manage_app WHERE eid = ?");
            rs.setInt(1, id);
            rs.execute();
            if (rs.next()) {
                System.out.println("New Salary: " + rs.getFloat("e_salary"));
                System.out.println("Salary updated successfully.");
            } else {
                System.out.println("Error verifying the update.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

class DeleteEmployee
{
    public static void deleteEmployee() 
    {
        try(JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet())
        {
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");

            System.out.println("Enter Employee ID to delete: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int id = Integer.parseInt(br.readLine());
            rs.setCommand("SELECT * FROM emp_manage_app WHERE eid = ?");
            rs.setInt(1, id);
            rs.execute();  // Execute the query to move the cursor to the first row

            if (rs.next()) {
                System.out.println("Employee To Be Deleted: ");
                System.out.println("Employee ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getFloat(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));
                System.out.println();

                if (rs.getString(5).equals("CEO")) {
                    System.out.println("Cannot delete CEO, You shall be reported to the authorities for disciplinary action.");
                    return;
                }

                // Closing the current result set to prepare for the DELETE command
                rs.close();

                // we are creating a new RowSet object as rs is closed so all resources for rs are removed
                try (JdbcRowSet deleteRs = RowSetProvider.newFactory().createJdbcRowSet()) {
                    deleteRs.setUrl("jdbc:postgresql://localhost:5432/postgres");
                    deleteRs.setUsername("postgres");
                    deleteRs.setPassword("tiger");
                    deleteRs.setCommand("DELETE FROM emp_manage_app WHERE eid = ?");
                    deleteRs.setInt(1, id);
                    deleteRs.execute();
                    System.out.println("Employee Deleted Successfully.");
                }

            } else {
                System.out.println("Employee not found.");
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



// SearchEmployeeOptions class for searching employee by various fields
class SearchEmployeeOptions {
    public static void searchEmployee() throws Exception {
        try (JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet()) {
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
    
            System.out.println("Search by? ");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Designation");
            int choice = Menu.readChoice(3,EmpManageApp.sc);
    
            String query = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    int id = Integer.parseInt(br.readLine());
                    query = "SELECT * FROM emp_manage_app WHERE eid = ?";
                    rs.setCommand(query);
                    rs.setInt(1, id);
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    String name = br.readLine();
                    query = "SELECT * FROM emp_manage_app WHERE e_name = ?";
                    rs.setCommand(query);
                    rs.setString(1, name);
                    break;
                case 3:
                    System.out.println("Enter Designation: ");
                    String designation = br.readLine();
                    query = "SELECT * FROM emp_manage_app WHERE e_designation = ?";
                    rs.setCommand(query);
                    rs.setString(1, designation);
                    break;
            }
            
            rs.execute();
            
            System.out.println("Employee Details: ");
            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getFloat(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));
                System.out.println();
            }
        } catch (SQLException e) {
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

public class EmpManageAppv2 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        int ch1 = 0, ch2 = 0;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Appraisal");
            System.out.println("4. Delete a Record");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");

            ch1 = Menu.readChoice(6,EmpManageApp.sc);

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

                        ch2 = Menu.readChoice(6 ,EmpManageApp.sc);

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
                    RaiseSalary.raiseSalary();
                    break;

                case 4:
                    DeleteEmployee.deleteEmployee();
                    break;
                case 5:
                    SearchEmployeeOptions.searchEmployee();
                    break;
            }
        } while (ch1 != 6);
        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
         EmpManageApp.sc.close();
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
    public static int readChoice(int maxChoice, Scanner sc) {
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = sc.nextInt();
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                System.out.println("Reason: " + e.getMessage());
            }
        }
    }
}

