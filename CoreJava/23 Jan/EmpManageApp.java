import java.util.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// New class for Displaying Employee Options in one place rather than 
class DisplayEmployeeOptions {

    static List<Emp> list = new ArrayList<>(EmpManageApp.emp.values());

    public static void displayEmployees() {
        CSVReader.fillHashMapFromCSV();
        if (Emp.countEmp == 0) {
            System.out.println("No Employee Present to Display");
            return;
        }

        System.out.println("Enter choice to sort by: ");
        System.out.println("1. ID Wise");
        System.out.println("2. Name Wise");
        System.out.println("3. Designation Wise");
        System.out.println("4. Age Wise");
        System.out.println("5. Salary Wise");
        System.out.println("6. Back");

        int ch = Menu.readChoice(6);

        switch (ch) {
            case 1:
                sortByID(list);  
                break;
            case 2:
                sortByName(list);  
                break;
            case 3:
                Collections.sort(list, new DesignationWiseDisplay());
                break;
            case 4:
                sortByAge(list);
                break;
            case 5:
                sortBySalary(list);  
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }

        if (ch != 6) {
            for (Emp e : list) {
                e.display();
            }
        }
    }

    private static void sortByID(List<Emp> list) {
        System.out.println("What order do you want to display in?");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int choice = Menu.readChoice(2);
        if (choice == 1) {
            Collections.sort(list, new IDWiseDisplay());
        } else {
            Collections.sort(list, new IDWiseDisplayDesc());
        }
    }

    private static void sortByName(List<Emp> list) {
        System.out.println("What order do you want to display in?");
        System.out.println("1. A-Z");
        System.out.println("2. Z-A");
        int choice = Menu.readChoice(2);
        if (choice == 1) {
            Collections.sort(list, new NameWiseDisplay());
        } else {
            Collections.sort(list, new NameWiseDisplayDesc());
        }
    }

    private static void sortByAge(List<Emp> list) {
        System.out.println("What order do you want to display in?");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int choice = Menu.readChoice(2);
        if (choice == 1) {
            Collections.sort(list, new AgeWiseDisplay());
        } else {
            Collections.sort(list, new AgeWiseDisplayDesc());
        }
    }

    private static void sortBySalary(List<Emp> list) {
        System.out.println("What order do you want to display in?");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int choice = Menu.readChoice(2);
        if (choice == 1) {
            Collections.sort(list, new SalaryWiseDisplay());
        } else {
            Collections.sort(list, new SalaryWiseDisplayDesc());
        }
    }

    public static List<Emp> getList() {
        return list;  // Return the static list
    }
}

// SearchEmployeeOptions class for searching employee by various fields
class SearchEmployeeOptions {
    public static void searchEmployee() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Search by? ");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Designation");
        int choice = Menu.readChoice(3);

        switch (choice) {
            case 1:
                searchByID();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchByDesignation();
                break;
            default:
                System.out.println("Invalid Choice");
        }
    }

    private static void searchByID() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Employee ID to search: ");
        int searchId = sc.nextInt();

        Emp employee = EmpManageApp.emp.get(searchId);

        if (employee != null) {
            employee.display();
        } else {
            System.out.println("Record not found.");
        }
    }

    private static void searchByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Employee Name to search: ");
        String searchName = sc.nextLine();

        boolean found = false;
        for (Emp employee : EmpManageApp.emp.values()) {
            if (employee.name.equalsIgnoreCase(searchName)) {
                employee.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Record not found.");
        }
    }

    private static void searchByDesignation() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Employee Designation to search: ");
        String searchDesignation = sc.nextLine();

        boolean found = false;
        for (Emp employee : EmpManageApp.emp.values()) {
            if (employee.designation.equalsIgnoreCase(searchDesignation)) {
                employee.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Record not found.");
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

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        this.eid = EmployeeValidation.readID();
        this.name = EmployeeValidation.readName();
        this.age = EmployeeValidation.readAge(21, 60);
        this.salary = salary;
        this.designation = designation;
        countEmp += 1;

        WriteToCSV.writeToCSV(this);
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
    Clerk() {
        super(20000, "Clerk");
    }
    Clerk(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 2000;
    }
}

class Programmer extends Emp {
    Programmer() {
        super(30000, "Programmer");
    }

    Programmer(int id, String name, int age, float salary, String designation) {
        super(id, name, age, salary, designation);
    }

    public void raiseSalary() {
        salary += 5000;
    }
}

class Manager extends Emp {
    Manager() {
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

    CEO() {
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
class IDWiseDisplay implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Integer.compare(e1.eid, e2.eid);
    }
}

class NameWiseDisplay implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e1.name.compareTo(e2.name);
    }
}

class DesignationWiseDisplay implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e1.designation.compareTo(e2.designation);
    }
}

class AgeWiseDisplay implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e1.age - e2.age;
    }
}

class SalaryWiseDisplay implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Float.compare(e1.salary, e2.salary);
    }
}

class IDWiseDisplayDesc implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Integer.compare(e2.eid, e1.eid);
    }
}

class NameWiseDisplayDesc implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e2.name.compareTo(e1.name);
    }
}

class AgeWiseDisplayDesc implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return e2.age - e1.age;
    }
}

class SalaryWiseDisplayDesc implements Comparator<Emp> {
    public int compare(Emp e1, Emp e2) {
        return Float.compare(e2.salary, e1.salary);
    }
}

class CSVReader {

    public static void fillHashMapFromCSV() {
        String csvFile = "employees.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    float salary = Float.parseFloat(data[3]);
                    String designation = data[4];
                    
                        if(designation.equalsIgnoreCase("CEO")) {
                            CEO ceo = CEO.getCeoInstance(id, name, age, salary, designation);
                            if(ceo==null){
                                continue;
                            }
                            else{
                                EmpManageApp.emp.put(id, ceo);
                            }                            
                        } else if (designation.equalsIgnoreCase("Clerk")) {
                            Clerk clerk = new Clerk(id, name, age, salary, designation);
                            EmpManageApp.emp.put(id, clerk);
                        } else if (designation.equalsIgnoreCase("programmer")) {
                            Programmer programmer = new Programmer(id, name, age, salary, designation);
                            EmpManageApp.emp.put(id, programmer);
                        } else if (designation.equalsIgnoreCase("manager")) {
                            Manager manager = new Manager(id, name, age, salary, designation);
                            EmpManageApp.emp.put(id, manager);
                        } else {
                            System.out.println("Pehle record bhar");
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WriteToCSV
{
    //Used to add an employee record in the employees.csv whenever someone calls the Emp abstract class
    public static void writeToCSV(Emp emp) {
        try {
            FileWriter writer = new FileWriter("employees.csv", true);
            writer.append(emp.eid + "," + emp.name + "," + emp.age + "," + emp.salary + "," + emp.designation + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Used to delete an employee record from the entered employee ID
    public static void deleteFromCSV(HashMap<Integer, Emp> emp) {    
        try {
            FileWriter writer = new FileWriter("employees.csv");
            for (Emp e : emp.values()) {
                writer.write(e.eid + "," + e.name + "," + e.age + "," + e.salary + "," + e.designation + "\n");
            } 
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}

}



// Main Application class
public class EmpManageApp {
    static HashMap<Integer, Emp> emp = new HashMap<>();

    public static void main(String[] args) throws InputMismatchException {
        CSVReader.fillHashMapFromCSV();
        int ch1 = 0, ch2 = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
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
                        System.out.println("5. Back");
                        System.out.println("--------------------------------------------");

                        ch2 = Menu.readChoice(5);

                        switch (ch2) {
                            case 1:
                                if (CEO.isCeoCreated()) {
                                    System.out.println("CEO already exists.");
                                } else {
                                    CEO ceo = new CEO();
                                    emp.put(ceo.eid, ceo);
                                    CEO.setCeoCreated(true);
                                }
                                break;
                            case 2:
                                if (!CEO.isCeoCreated()) {
                                    System.out.println("Cannot create Clerk. CEO must be created first.");
                                    continue;
                                }
                                Clerk c = new Clerk();
                                emp.put(c.eid, c);
                                break;
                            case 3:
                                if (!CEO.isCeoCreated()) {
                                    System.out.println("Cannot create Programmer. CEO must be created first.");
                                    continue;
                                }
                                Programmer p = new Programmer();
                                emp.put(p.eid, p);
                                break;
                            case 4:
                                if (!CEO.isCeoCreated()) {
                                    System.out.println("Cannot create Manager. CEO must be created first.");
                                    continue;
                                }
                                Manager m = new Manager();
                                emp.put(m.eid, m);
                                break;
                        }
                    } while (ch2 != 5);
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
                        WriteToCSV.deleteFromCSV(emp);

                    }
                    break;

                case 4:
                    System.out.print("Enter the record ID to be deleted: ");
                    int id = sc.nextInt();
                    if (emp.containsKey(id)) {
                        emp.get(id).display();
                        String designation = emp.get(id).designation;
                        System.out.println("Designation: " + designation);
                        System.out.print("Do you really want to delete the record? (Y/N): ");
                        sc.nextLine();
                        String res = sc.nextLine();
                        if(designation.equalsIgnoreCase("CEO")) {
                            System.out.println("Record not deleted. Deleting CEO is not allowed.");
                            break;
                        }
                        else{
                            
                            if (res.equalsIgnoreCase("Y")) {
                                emp.remove(id);
                                WriteToCSV.deleteFromCSV(emp);
                                Emp.countEmp--;
                                System.out.println("Record deleted.");
                            } else {
                                System.out.println("Record not deleted.");
                            }
                        }
                        }
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
        while (true) {
            System.out.println("Enter Choice: ");
            try {
                int choice = new Scanner(System.in).nextInt();
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

