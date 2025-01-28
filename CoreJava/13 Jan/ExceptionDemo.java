import java.io.*;
import java.sql.SQLException;

class A {
    public void abc() throws IOException, SQLException, UserException { 
        int a = 50;
        for (int i = 1; i < 20; i++) {
            System.out.println(i);
            int res = a / (a - i);

            if (i == 150) {
                throw new NullPointerException();
            }
            if (i == 120) {
                throw new IOException();
            }
            if (i == 100) {
                throw new SQLException();
            }
            if (i == 60) {
                return;
            }
            if (i == 60) {
                throw new UserException(); 
            }
        }
    }

    public void xyz() throws IOException, SQLException, UserException { 
	try
	{
		abc();
	}
	catch(NullPointerException e)
	{
		System.out.println("**Null Pointer Exception being handled in xyz() method even though xyz() throws NullPointerException**")
	}
        
    }

    public void atoz() throws IOException, SQLException, UserException {        
	xyz();
    }
}

public class ExceptionDemo {
    public static void main(String args[]) {

        try {
            A a1 = new A();
            a1.atoz();

        } catch (IOException | SQLException e) {
            System.out.println("IO/SQL Exception....");
        } catch (NullPointerException e) {
            System.out.println("NullPointer Exception....");
        } catch (UserException e) {
            System.out.println("Custom Exception....");
            System.out.println("Reason: " + e.getMessage());
	    e.display();
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Parent Exception....");
        }
	finally
        {
        System.out.println("THANK YOU!");
        }
        System.out.println("Program Continues....");
	
       
    }
}

// Making a checked Exception by simply extending the Exception class 
// and making the 2 compulsory constructors and mapping them to the super class
class UserException extends Exception {
    public UserException() { // Just to mention the exception, but does not provide any reason
        super();
    }

    public UserException(String msg) { // To throw the message of the exception: what the exception is really about??
        super(msg);
    }

    // We may add our extra methods for the custom exception class and making the 2 compulsory constructors and mapping them to the super class

    public void display() {
        System.out.println("This is a demo display method");
    }
}

// Making a runtime Exception by extending RuntimeException class
class RuntimeUserException extends RuntimeException {
    public RuntimeUserException() {
        super();
    }

    public RuntimeUserException(String msg) {
        super(msg);
    }

    // We may add our extra methods for the custom exception class
    public void display() {
        System.out.println("This is a demo display method");
    }
}
