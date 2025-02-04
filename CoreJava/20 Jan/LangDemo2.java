import java.lang.reflect.*;
import java.util.Scanner;

class A {
    int x;

    A(int x) {
        this.x = x;
    }

    @Override
    protected void finalize() {
        System.out.println("Object with x value: " + x + " is getting removed....");
    }
}

class Employee {
    public void display() {
        System.out.println("Display being called here");
    }
}

class Clerk {
    @Override
    public String toString() {
        return "Hello from CLERK";
    }
}

class Programmer {
    @Override
    public String toString() {
        return "Hello from PROGRAMMER";
    }
}

class Manager {
    @Override
    public String toString() {
        return "Hello from MANAGER";
    }
}

class CEO {
    @Override
    public String toString() {
        return "Hello from CEO";
    }
}

public class LangDemo2 {
    public static void main(String[] args) throws Exception {
        A a1 = new A(10);
        A a2 = new A(20);
        A a3 = new A(30);

        a2.finalize();
        System.out.println(a1.x);
        System.out.println(a2.x);
        System.out.println(a3.x);

        a2 = null;
        a1 = null;
        a3 = null;

        // Suggest garbage collection
        System.gc();

        // Creating a class at runtime by taking class type from user
        System.out.println("Enter the Class Name: ");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
        Class c2 = Class.forName(className);
        Object obj = c2.newInstance();
        System.out.println(obj);

        // To know the methods in the class
        Method[] methods = c2.getMethods();
        Field[] fields = c2.getFields();
        Constructor[] constructors = c2.getConstructors();

        System.out.println("class " + c2.getName() + "()");
        System.out.println("{");
        for (Method method : methods) {
            System.out.println(method.getName() + "()");
        } 
        System.out.println("}");
    }
}
