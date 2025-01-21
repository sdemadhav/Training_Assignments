import java.lang.reflect.*;
import java.util.Scanner;

class Calculator extends Object 
{
	public int add(int a, int b)
	{
		return a+b;
	}
	public int sub(int a, int b)
	{
		return a-b;
	}
	public int mul(int a, int b)
	{
		return a*b;
	}
	public int div(int a, int b)
	{
		return a/b;
	}
}
 
public class CalculatorAssignment
{
    public static void main(String[] args) {
        System.out.println("Enter the Class Name: ");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
	Class c2 = null;
	Object obj = null;
	try
	{
		 c2 = Class.forName(className);
	}
	catch(ClassNotFoundException e){
		System.out.println("Exception from inputting class name !");
	}
        
	try
	{
		obj = c2.newInstance();
	}
	catch(InstantiationException e){
		System.out.println("Exception from initializing the instance of the class !");
	}
	catch( IllegalAccessException e){
		System.out.println("Exception from initializing the instance of the class !");
	}
        
        System.out.println(obj);
		//Class c2 = Class.forName(className);

		Method[] methods = c2.getDeclaredMethods();

		for(Method method : methods)
		{
			if(method.getDeclaringClass().equals(c2))
			{
				System.out.println(method.getName());
			}
		}
		System.out.println("Enter the operation name: ");
		String operation = scanner.nextLine();
		System.out.println("Enter the paramter 1: ");
		int param1 = scanner.nextInt();
		System.out.println("Enter the paramter 2: ");
		int param2 = scanner.nextInt();

		for (int i = 0; i < methods.length; i++) {
			if(methods[i].getName().equals(operation))
			{
				try
				{
				System.out.println("Your Result for the "+methods[i].getName()+"Operation is :" +methods[i].invoke(obj, param1, param2));
				}
				catch( IllegalAccessException | InvocationTargetException e){
				System.out.println("Exception from accessing the class mathods via invoke method!");
				}
			}
			else
			{
				MethodDoesntExistException e = new MethodDoesntExistException();
				e.displayMsg();
				throw new MethodDoesntExistException("Inappropriate Method name");
				
			}
		}
	}
}

class MethodDoesntExistException extends RuntimeException
{
	MethodDoesntExistException()
	{
		super();
	}
	MethodDoesntExistException(String msg)
	{
		super(msg);
	}
	
	public void displayMsg()
	{
		System.out.println("Please check the method name. No such method is either available or accessible from the entered class name");
	}
}
