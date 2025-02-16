import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.rowset.*;

abstract class Emp {
    protected String name;
    protected int age;
    protected float salary;
    protected int eid;
    protected String designation;
    protected String department;

    Emp(float salary, String designation) {
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = salary;
        this.designation = designation;
        this.department = DepartmentInput.readDepartment();
    }

    Emp(String designation) {
        this.name = NameInput.readName();
        this.age = AgeInput.readAge(20, 60);
        this.salary = SalaryInput.readSalary();
        this.designation = designation;
        this.department = DepartmentInput.readDepartment();
    }
}

final class Clerk extends Emp {
    Clerk() {
        super("Clerk");
    }

    private static Clerk clerk = null;

    public static Clerk getClerk() {
        clerk = new Clerk();
        return clerk;
    }
}

final class Programmer extends Emp {
    Programmer() {
        super("Programmer");
    }

    private static Programmer programmer = null;

    public static Programmer getProgrammer() {
        programmer = new Programmer();
        return programmer;
    }

}

final class Manager extends Emp {
    Manager() {
        super("Manager");
    }

    private static Manager manager = null;

    public static Manager getManager() {
        manager = new Manager();
        return manager;
    }
}

final class OtherDesignation extends Emp {
    OtherDesignation(String designation) {
        super(designation);
    }

    private static OtherDesignation otherDesignation = null;

    public static OtherDesignation getOtherDesignation() {
        otherDesignation = new OtherDesignation(DesignationInput.readDesignation());
        return otherDesignation;
    }
}


final class JdbcConnection {
    private static JdbcRowSet rs = null;

    private JdbcConnection() {
    }

    public static JdbcRowSet getJdbcConnection() throws SQLException {
        if (rs == null) {
            rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(System.getenv("DATABASE_URL"));
            rs.setUsername(System.getenv("DATABASE_USERNAME"));
            rs.setPassword(System.getenv("DATABASE_PASSWORD"));
        }
        return rs;
    }

    public static void closeJdbcConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }
}

final class BufferConn {
    private static BufferedReader br = null;
 
    private BufferConn() {
    }

    public static BufferedReader getBufferConnection() {
        if (br == null) {
        	br = new BufferedReader(new InputStreamReader(System.in));
        }
        return br;
    }

    public static void closeBufferConnection(){
        try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

class EmpFactory {
    public static Emp createEmp(String type) {
        switch (type) {
            case "Clerk":
                return Clerk.getClerk();
            case "Programmer":
                return Programmer.getProgrammer();
            case "Manager":
                return Manager.getManager();
            case "Others":
                return OtherDesignation.getOtherDesignation();
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}

interface EmpDAO {
    abstract void storeEmployee(Emp e);

    abstract void displayEmployee(String type);

    abstract void raiseEmployeeSalary(int eid);

    abstract void deleteEmployee(int eid);

    abstract void searchEmployeeBasedOnId(int eid);

    abstract void searchEmployee(String type, String value);
}

class MainMenu implements EmpDAO {
    @Override
    public void storeEmployee(Emp emp) {
        try {
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            rs.setCommand("SELECT * FROM employee WHERE 1=0");
            rs.execute();
            rs.moveToInsertRow();
            rs.updateString("name", emp.name);
            rs.updateInt("age", emp.age);
            rs.updateFloat("salary", emp.salary);
            rs.updateString("designation", emp.designation);
            rs.updateString("department", emp.department);
            rs.insertRow();
            rs.moveToCurrentRow();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void displayEmployee(String type) {
        try {
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            switch (type) {
                case "Designation": 
                    rs.setCommand("select * from employee order by Designation");
                    break;
                case "ID": 
                    rs.setCommand("select * from employee order by EID");
                    break;
                case "Name": 
                    rs.setCommand("select * from employee order by Name");
                    break;
                case "Age": 
                    rs.setCommand("select * from employee order by Age");
                    break;
                case "Salary": 
                    rs.setCommand("select * from employee order by Salary");
                    break;
                case "Department": 
                    rs.setCommand("select * from employee order by Department");
                    break;
                default: 
                    throw new IllegalArgumentException("Unknown employee type");
            }
            rs.execute();
            while (rs.next()) {
                System.out.println("------------------------------------------------");
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getInt(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));
                System.out.println("------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void raiseEmployeeSalary(int eid) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            rs.setCommand("SELECT * FROM employee WHERE eid = ?");
            rs.setInt(1, eid);
            rs.execute();

            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                Display.displayEmployeeFunction(rs);
                System.out.print("Do you really want to raise salary of the above record (Y/N)? ");
                String confirm = br.readLine();
                System.out.println("Enter the amount:");
                int amount = Integer.parseInt(br.readLine());

                if (confirm.equalsIgnoreCase("Y")) {
                    rs.updateFloat("salary", rs.getFloat("salary") + amount);
                    rs.updateRow();

                    System.out.println("Employee with eid: " + eid + " salary raised successfully");
                }
            }
            br.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteEmployee(int eid) {
        try {
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            rs.setCommand("select * from employee where eid = ?");
            rs.setInt(1, eid);
            rs.execute();
            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                rs.deleteRow();
                System.out.println("Employee Deleted Successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void searchEmployeeBasedOnId(int searchEid) {
        try {
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            rs.setCommand("select * from employee where eid = ?");
            rs.setInt(1, searchEid);
            rs.execute();
            if (!rs.next()) {
                System.out.println("No Employee Present with this eid");
            } else {
                Display.displayEmployeeFunction(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void searchEmployee(String type, String value) {
        try {
            JdbcRowSet rs = JdbcConnection.getJdbcConnection();
            rs.setCommand("select * from employee where " + type + " = ?");
            rs.setString(1, value);
            rs.execute();
            if (!rs.next()) {
                System.out.println("No Employee Present with this " + type);
            } else {
                Display.displayEmployeeFunction(rs);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

class Display {
    public static void displayEmployeeFunction(JdbcRowSet rs) {
        try {
            do {
                System.out.println("------------------------------------------------");
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("Salary: " + rs.getInt(4));
                System.out.println("Designation: " + rs.getString(5));
                System.out.println("Department: " + rs.getString(6));
                System.out.println("------------------------------------------------");
            } while (rs.next());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class EmpManageApp {
    public static void main(String[] args) throws SQLException {
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
            } catch (IOException e) {
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
        JdbcConnection.closeJdbcConnection();
        // DBConnection.closeConnection();
    }
}

class Menu {
    public static int readChoice(int maxChoice) throws IOException, NumberFormatException{
        BufferedReader br = BufferConn.getBufferConnection();
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = Integer.parseInt(br.readLine());;
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                e.displayMessage(maxChoice);
                return 0;
            }
        }
    }
}

class AgeInput {
    public static int readAge(int minAge, int maxAge) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your age:");
            try {
                int age = Integer.parseInt(reader.readLine());
                if (age < minAge || age > maxAge) {
                    throw new InvalidAgeException();
                }
                return age;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidAgeException e) {
                e.displayMessage(minAge, maxAge);
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class IdInput {
    public static int readId() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your id:");
            try {
                int id = Integer.parseInt(reader.readLine());
                return id;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class NameInput {
    public static String readName() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your name:");
            try {
                String name = reader.readLine();
                if (!name.matches("^[A-Z][a-z]*\\s[A-Z][a-z]*$")) {
                    throw new InvalidNameException();
                }
                return name;
            } catch (InvalidNameException e) {
                e.displayMessage();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class DesignationInput {
    public static String readDesignation() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your Designation:");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class SalaryInput {
    public static int readSalary() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your salary:");
            try {
                return Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number only.");
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class DepartmentInput {
    public static String readDepartment() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your Department:");
            try {
                return reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading input: " + e.getMessage());
            }
        }
    }
}

class InvalidChoiceException extends RuntimeException {
    public void displayMessage(int maxChoice) {
        System.out.println("Please enter a choice between 1 and " + maxChoice);
    }
}

class InvalidAgeException extends RuntimeException {
    public void displayMessage(int minAge, int maxAge) {
        System.out.println("Please enter an age between " + minAge + " and " + maxAge);
    }
}

class InvalidNameException extends RuntimeException {
    public void displayMessage() {
        System.out.println("Invalid name. Name must start with a capital letter and contain two words.");
    }
}

class InvalidIDException extends RuntimeException {
    InvalidIDException(String msg) {
        super(msg);
    }

    public void displayMessage() {
        System.out.println("Please enter a number only");
    }
}