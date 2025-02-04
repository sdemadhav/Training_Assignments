import java.util.*;
import java.util.regex.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.*;

public class EmpManageApp_DB {
    
}
abstract class Emp{
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
