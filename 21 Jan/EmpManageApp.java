import java.util.*;
import java.util.regex.*;

abstract class Emp {
    int eid;
    String name;
    private int age;
    float salary;
    String designation;

    static int countEmp = 0;

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        // Check if ID already exists
        this.eid = EmployeeValidation.readID();
        this.name = EmployeeValidation.readName();
        this.age = EmployeeValidation.readAge(21, 60);
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

    public void raiseSalary() {
        salary += 2000;
    }
}

class Programmer extends Emp {
    Programmer() {
        super(30000, "Programmer");
    }

    public void raiseSalary() {
        salary += 5000;
    }
}

class Manager extends Emp {
    Manager() {
        super(100000, "Manager");
    }

    public void raiseSalary() {
        salary += 15000;
    }
}

public class EmpManageApp {
    static HashMap<Integer, Emp> emp = new HashMap();

    public static void main(String[] args) throws InputMismatchException {

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
                        System.out.println("1. Create Clerk");
                        System.out.println("2. Create Programmer");
                        System.out.println("3. Create Manager");
                        System.out.println("4. Back");
                        System.out.println("--------------------------------------------");

                        ch2 = Menu.readChoice(4);

                        switch (ch2) {
                            case 1:
                                Clerk c = new Clerk();
                                emp.put(c.eid, c);
                                break;
                            case 2:
                                Programmer p = new Programmer();
                                emp.put(p.eid, p);
                                break;
                            case 3:
                                Manager m = new Manager();
                                emp.put(m.eid, m);
                                break;
                        }
                    } while (ch2 != 4);
                    break;

                case 2:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Display");
                    } else {
                        for (Emp e : emp.values()) {
                            e.display();
                        }
                    }
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
                    System.out.print("Enter the record ID to be deleted: ");
                    int id = sc.nextInt();
                    if (emp.containsKey(id)) {
                        emp.get(id).display();
                        System.out.print("Do you really want to delete the record? (Y/N): ");
                        sc.nextLine();
                        String res = sc.nextLine();
                        if (res.equalsIgnoreCase("Y")) {
                            emp.remove(id);
                            Emp.countEmp--;
                            System.out.println("Record deleted.");
                        } else {
                            System.out.println("Record not deleted.");
                        }
                    } else {
                        System.out.println("Record not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter the record ID to be searched: ");
                    int searchId = sc.nextInt();
                    Iterator<Map.Entry<Integer, Emp>> iterator = emp.entrySet().iterator();
                    boolean found = false;
                    while (iterator.hasNext()) {
                        Map.Entry<Integer, Emp> entry = iterator.next();
                        if (entry.getKey() == searchId) {
                            entry.getValue().display();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Record not found.");
                    }
                    break;
            }
        } while (ch1 != 6);

        System.out.println("Total Employees Present in the Company: " + Emp.countEmp);
    }
}

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
                System.out.println("Some recommendations for existing IDs are as follows: " 
        
                + (EmpManageApp.emp.keySet().stream().max(Integer::compare).get() + 1) + ", "
                + (EmpManageApp.emp.keySet().stream().min(Integer::compare).get() + 2 + ",")
                + (EmpManageApp.emp.keySet().stream().max(Integer::compare).get() + 3));
                
            }
        }
    }
}
