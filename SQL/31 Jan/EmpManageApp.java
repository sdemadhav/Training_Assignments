import java.util.*;
import java.sql.*;
import javax.sql.rowset.*;

class Menu {
    public static int readChoice(int max) {
        int choice = -1;
        while (choice < 1 || choice > max) {
            try {
                choice = EmpManageApp.sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice, please try again.");
                EmpManageApp.sc.nextLine(); // clear the buffer
            }
        }
        return choice;
    }
}

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
            obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App");
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

        int ch = Menu.readChoice(7);

        try {
            obj.initializeRowSet();
            switch (ch) {
                case 1: obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY eid ASC"); break;
                case 2: obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_name"); break;
                case 3: obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_designation"); break;
                case 4: obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_age"); break;
                case 5:
                    System.out.println("What order do you want to display in?");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int choice = Menu.readChoice(2);
                    if (choice == 1) {
                        obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_salary");
                    } else {
                        obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_salary DESC");
                    }
                    break;
                case 6: obj.rowSet.setCommand("SELECT * FROM EMP_Manage_App ORDER BY e_department"); break;
                case 7: return;
                default: System.out.println("Invalid Choice"); return;
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

class CreateEmployee {
    public static void createEmployee() throws Exception{
        try (JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet()) {
            // Set connection properties
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");
    
            // Collect input data from the user
            System.out.println("Enter Employee Name: ");
            String name = EmpManageApp.sc.next();
            System.out.println("Enter Employee Age: ");
            int age = EmpManageApp.sc.nextInt();
            System.out.println("Enter Employee Salary: ");
            float salary = EmpManageApp.sc.nextFloat();
            System.out.println("Enter Employee Designation: ");
            String designation = EmpManageApp.sc.next();
            System.out.println("Enter Employee Department: ");
            String department = EmpManageApp.sc.next();
    
            // Define the SQL query for insertion
            String sql = "INSERT INTO emp_manage_app (e_name, e_age, e_salary, e_designation, e_department) VALUES (?, ?, ?, ?, ?)";
    
            // Set the SQL command for the JdbcRowSet
            rs.setCommand(sql);
    
            // Set parameters for the SQL query
            rs.setString(1, name);           
            rs.setInt(2, age);               
            rs.setFloat(3, salary);          
            rs.setString(4, designation);   
            rs.setString(5, department);     
            rs.execute();  
    
            System.out.println("Employee Created Successfully!");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

class RaiseSalary {
    public static void raiseSalary() {
        System.out.println("Enter Employee ID to raise salary: ");
        int id = EmpManageApp.sc.nextInt();
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
                float raiseAmount = EmpManageApp.sc.nextFloat();
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

class DeleteEmployee {
    public static void deleteEmployee() {
        try (JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet()) {
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");

            System.out.println("Enter Employee ID to delete: ");
            int id = EmpManageApp.sc.nextInt();
            rs.setCommand("SELECT * FROM emp_manage_app WHERE eid = ?");
            rs.setInt(1, id);
            rs.execute();

            if (rs.next()) {
                System.out.println("Employee To Be Deleted: ");
                System.out.println("Employee ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getFloat(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));

                if (rs.getString(5).equals("CEO")) {
                    System.out.println("Cannot delete CEO, You shall be reported to the authorities.");
                    return;
                }

                rs.close();

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
        }
    }
}

class SearchEmployeeOptions {
    public static void searchEmployee() {
        try (JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet()) {
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");

            System.out.println("Search by?");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Designation");
            int choice = Menu.readChoice(3);

            String query = "";
            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    int id = EmpManageApp.sc.nextInt();
                    query = "SELECT * FROM emp_manage_app WHERE eid = ?";
                    rs.setCommand(query);
                    rs.setInt(1, id);
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    String name = EmpManageApp.sc.next();
                    query = "SELECT * FROM emp_manage_app WHERE e_name = ?";
                    rs.setCommand(query);
                    rs.setString(1, name);
                    break;
                case 3:
                    System.out.println("Enter Designation: ");
                    String designation = EmpManageApp.sc.next();
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

abstract class Emp {
    public int eid;
    public String name;
    public int age;
    public float salary;
    public String designation;

    static int countEmp = 0;

    Emp(float salary, String designation) {
        this.salary = salary;
        this.designation = designation;
        countEmp++;
    }

    Emp(String name, int age, float salary, String designation) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.designation = designation;
        countEmp++;
    }

    abstract void display();
}

public class EmpManageApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("Employee Management Application");
                System.out.println("1. Create Employee");
                System.out.println("2. Display Employees");
                System.out.println("3. Search Employee");
                System.out.println("4. Raise Salary");
                System.out.println("5. Delete Employee");
                System.out.println("6. Exit");
                int choice = Menu.readChoice(6);

                switch (choice) {
                    case 1: CreateEmployee.createEmployee(); break;
                    case 2: DisplayEmployeeOptions.displayEmployees(); break;
                    case 3: SearchEmployeeOptions.searchEmployee(); break;
                    case 4: RaiseSalary.raiseSalary(); break;
                    case 5: DeleteEmployee.deleteEmployee(); break;
                    case 6: System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid Choice"); break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
