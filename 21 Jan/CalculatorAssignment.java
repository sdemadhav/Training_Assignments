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
    public static void main(String[] args) throws ClassNotFoundException,InstantiationException,IllegalAccessException, InvocationTargetException {
        System.out.println("Enter the Class Name: ");
        Scanner scanner = new Scanner(System.in);
        String className = scanner.nextLine();
        Class c2 = Class.forName(className);
        Object obj = c2.newInstance();
        System.out.println(obj);

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
				System.out.println("Your Result for the "+methods[i].getName()+"Operation is :" +methods[i].invoke(obj, param1, param2));
			}
		}
	}
}
