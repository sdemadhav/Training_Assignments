package emp.assignment;

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

  Emp(float salary, String designation) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter an emp ID");
    eid = sc.nextInt();
    sc.nextLine(); // consume the newline character

    // Check if ID already exists
    for (int i = 0; i < EmpManageApp.emp.length; i++) {
      if (EmpManageApp.emp[i] != null && EmpManageApp.emp[i].eid == eid) {
        System.out.println("ID already exists. Please enter a unique ID.");
        return;
      }
    }

    EmployeeValidation.readName(name);    
    EmployeeValidation.readAge(age, 21, 60);
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
  static Emp[] emp = new Emp[100];

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
              System.out.println("1. Create Clerk");
              System.out.println("2. Create Programmer");
              System.out.println("3. Create Manager");
              System.out.println("4. Back");
              System.out.println("--------------------------------------------");

              ch2 = Menu.readChoice(4);

              switch (ch2) {
                case 1:
                  emp[Emp.countEmp] = new Clerk();
                  break;
                case 2:
                  emp[Emp.countEmp] = new Programmer();
                  break;
                case 3:
                  emp[Emp.countEmp] = new Manager();
                  break;
              }
            } while (ch2 != 4);
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
              sc.nextLine(); // consume the newline
              String res = sc.nextLine();
              if (res.equalsIgnoreCase("Y")) {
                for (int i = foundId; i < Emp.countEmp - 1; i++) {
                  emp[i] = emp[i + 1];
                }
                emp[Emp.countEmp - 1] = null; // clear last element
                Emp.countEmp--;
                System.out.println("Record deleted.");
              } else {
                System.out.println("Record not deleted.");
              }
            } else {
              System.out.println("Record not found.");
            }
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

    		// Age validation with custom exception
   
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




//Develop code in order to make it usable in more than one use case
//for instance our readAge maybe used for reading age of employees as well as for students and teachers.