import java.util.function.*;
import java.util.*;

class A implements Consumer<String>
{
	public void accept(String str)
	{
		System.out.println("Good name is : "+str);
	}
}
class B implements Consumer<String>
{
	public void accept(String str)
	{
		System.out.println("Written to csv file through class B: "+str);
	}
}
class C 
{
	public void writeToFile(String str)
	{
		System.out.println("Written to csv file: "+str);
	}
}

public class ForEachMethod
{
	public static void main(String[] args)
	{
		List<String> employees = Arrays.asList("Suman","Sujata","Supriya","Sunita");
		
		//Method 1: use Consumer interface and just create A() object in forEach argument. This is because by default it searches for accept() 			//	    method and runs it
		employees.forEach(new A());
		employees.forEach(new B());
		
		//Method 2: use the method reference way to just reefer to the method of some other class and run that method for each object in list.
		employees.forEach(C::writeToFile);
		
		//Method 3: use simple lambda expression
		employees.forEach((arg)->System.out.println(arg));
	
		//Method 4: use the println method of System class to just print every object in list
		employees.forEach(System.out::println);

		
	}
}