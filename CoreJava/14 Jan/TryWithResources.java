import java.util.*;


class A implements AutoCloseable
{
	A()
	{
		System.out.println("***Allocating the required resources to object of class A***");
	}
	public void process()
	{
		System.out.println("Performing the required processing on A object");	
	}
	public void close()
	{
		System.out.println("***Releasing resources for A class***");	
	}
}

class B implements AutoCloseable
{
	B()
	{
		System.out.println("***Allocating the required resources to object of class B***");
	}
	public void process()
	{
		System.out.println("Performing the required processing on B object");	
	}
	public void close()
	{
		System.out.println("***Releasing resources for B class***");	
	}
}


public class TryWithResources
{
	public static void main(String args[])
	{
		//Allocate resources in the order you want them to start 
		//resources will be closed in the reverse order always : first B then A will be closed
		try(A a1 = new A() ; B b1= new B();)
		{
			a1.process();
			//below is an example of some exception being thrown for some condition
			if(true)
				return; // JVM stops reading ahead and only closes the allocated resources to B
			b1.process();
		}
		System.out.println("----------------CODE UNREACHABLE----------------");
		System.out.println("Program Continues with other operations after resource oriented programming is done...");
	}
}