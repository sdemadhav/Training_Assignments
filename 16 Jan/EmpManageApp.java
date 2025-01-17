import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.*;

abstract class Emp {
    int eid;
    String name;
    private int age;
    float salary;
    String designation;

    static int countEmp = 0;
    static final int MAX_EMPLOYEES = 100;

    Emp(float salary, String designation) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an emp ID");
        eid = sc.nextInt();
        sc.nextLine();

        // Check if ID already exists
        for (int i = 0; i < EmpManageApp.emp.length; i++) {
            if (EmpManageApp.emp[i] != null && EmpManageApp.emp[i].eid == eid) {
                System.out.println("ID already exists. Please enter a unique ID.");
                return;
            }
        }

        this.name = EmployeeValidation.readName(null);  
        this.age = EmployeeValidation.readAge(0, 21, 60);
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

final class CEO extends Emp {
    private static boolean ceoCreated = false;
    static CEO ceo = null;

    private CEO() {
        super(500000, "CEO");
    }

    public static boolean isCreated() {
        return ceoCreated;
    }

    public static CEO getCeoInstance() {
        if (!ceoCreated) {
            ceoCreated = true;
            return ceo = new CEO();
        }
        return ceo;
    }

    public void raiseSalary() {
        salary += 10000;
    }
}

// Factory class to handle employee creation
class EmployeeFactory {
    public static Emp createEmployee(String type) {
        if (Emp.countEmp >= Emp.MAX_EMPLOYEES) {
            throw new IllegalStateException("Maximum employee limit reached. Cannot create more employees.");
        }

        switch (type) {
            case "Clerk":
                return new Clerk();
            case "Programmer":
                return new Programmer();
            case "Manager":
                return new Manager();
            case "CEO":
                if (CEO.isCreated()) {
                    throw new IllegalStateException("CEO already exists. Only one CEO is allowed.");
                }
                return CEO.getCeoInstance();
            default:
                throw new IllegalArgumentException("Invalid employee type: " + type);
        }
    }
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
    static Emp[] emp = new Emp[Emp.MAX_EMPLOYEES];

    public static void main(String[] args) throws InputMismatchException {
        int ch1 = 0, ch2 = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Raise Salary");
            System.out.println("4. Delete a Record");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------");

            ch1 = Menu.readChoice(5);

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

                        try {
                            if (ch2 != 1 && !(CEO.isCreated())) {
                                System.out.println("No new employees allowed until CEO is created");
                                continue;
                            }
                            if (ch2 == 1 && (CEO.isCreated())) {
                                System.out.println("CEO already exists");
                                continue;
                            }

                            switch (ch2) {
                                case 1:
                                    emp[Emp.countEmp] = EmployeeFactory.createEmployee("CEO");
                                    break;
                                case 2:
                                    emp[Emp.countEmp] = EmployeeFactory.createEmployee("Clerk");
                                    break;
                                case 3:
                                    emp[Emp.countEmp] = EmployeeFactory.createEmployee("Programmer");
                                    break;
                                case 4:
                                    emp[Emp.countEmp] = EmployeeFactory.createEmployee("Manager");
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } while (ch2 != 5);
                    break;

                case 2:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Display");
                    }
                    for (int i = 0; i < Emp.countEmp; i++) {
                        emp[i].display();
                    }
                    break;

                case 3:
                    if (Emp.countEmp == 0) {
                        System.out.println("No Employee Present to Raise Salary");
                    }
                    for (int i = 0; i < Emp.countEmp; i++) {
                        emp[i].raiseSalary();
                    }
                    break;

                case 4:
                    System.out.print("Enter the record ID to be deleted: ");
                    int id = sc.nextInt();
                    int foundId = -1;
                    boolean found = false;

                    for (int i = 0; i < Emp.countEmp; i++) {
                        if (emp[i].eid == id) {
                            emp[i].display();
                            foundId = i;
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        System.out.print("Do you really want to delete the record? (Y/N): ");
                        sc.nextLine();
                        String res = sc.nextLine();
                        if (res.equalsIgnoreCase("Y")) {
                            if(emp[foundId].designation == "CEO")
                            {
                                System.out.println("Deleting the CEO is not allowed.You shall be contacted by the admin for the misconduct.");
                                break;
                            }
                            for (int i = foundId; i < Emp.countEmp - 1; i++) {
                                emp[i] = emp[i + 1];
                            }
                            emp[Emp.countEmp - 1] = null;
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
                    System.out.println("Exiting...");
                    break;
            }
        } while (ch1 != 5);

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
public void display()
{
String rules = " The following rules must be followed:\t1.Name must have 2 words.\n\t2.Both first and last names must be entered\n\t3.Both first and last names must start with a capital letter\n";
System.out.println(rules);
}


}
class Menu
{
private static int maxChoice;
public static int readChoice(int max)
{
maxChoice=max;
while(true)
{
System.out.println("Enter Choice:- ");
try
{
int choice = new Scanner(System.in).nextInt();
if(choice<1 || choice>maxChoice)
throw new InvalidChoiceException();
return choice;
}
catch(InputMismatchException e)
{
System.out.println("Please enter number only");
}
catch(InvalidChoiceException e)
{
System.out.println("Please enter from 1 to "+max);
}

}
}
}

class EmployeeValidation
{
static int readAge(int age, int min, int max)
{
System.out.println("Enter your age: ");
   
    do {
      try {
        age = new Scanner(System.in).nextInt();
        if (age < min || age > max)
          throw new AgeCustomException("Age must be between "+min+" and "+max);
          break;
       
      }
catch(InputMismatchException e)
{
System.out.println("Please enter number only");
}
catch (AgeCustomException e) {
        System.out.println("Reason: " + e.getMessage());
        System.out.println("Please enter a valid age from "+min+"-"+max);
      }
    } while (age < min || age > max);

return age;
}

static String readName(String name)
{
while(true)
{
System.out.println("Enter your name: ");
try
{
name = new Scanner(System.in).nextLine();
String format = "^[A-Z][a-z]*\\s[A-Z][a-z]*$";

Pattern p1 = Pattern.compile(format);
Matcher m1 = p1.matcher(name);


if(!m1.matches()) throw new InvalidNameFormatException("Name format rules have not been followed.");
break;

}
catch(InvalidNameFormatException e)
{
e.display();
}
}

return name;
}
}
/*
interface deleteEmployee
{
	private boolean generalEmployee = false;
	public void checkDesignation(int foundId);
	public void displayRecord();
	public void handleDelete();
	public void displayResult();
}

class void CeoEmployeeDeletion implements deleteEmployee
{
	private boolean generalEmployee = false;
	
	@override 
	public void checkDesignation(int foundId)
	{
		if(emp[foundId].designation=="CEO")
		{
			System.out.println("CEO record found");	
			generalEmployee = true;		
		}
		else{
			return;
		}
	}


}
*/