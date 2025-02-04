/*
1. FunctionalInterface means an interface whose only 1 method is compulsory to be implemented in the class implementing it .
So we can only write 1 abstract method in a functional interface which is NOT a method of the Object class.
We can have multiple methods from the Object class in the Functional Interface but they aren't Compulsory to be implemented by the implementing class.


*/

@FunctionalInterface
interface I
{
	public void abc();
	public boolean equals(Object obj);
	public int hashCode();
}
class A implements I
{
	public void abc()
	{
		System.out.println("From A class abc() method");
	}
}
class B
{
	public void demo()
	{
		System.out.println("Hi Everybody");
	}
	
}
public class FunctionalInterfaceDemo
{
	public static void main(String[] args)
	{
		I i1 = new A();
		i1.abc();
		
		I i2 = new I()
		  	   {
			   	System.out.println("Hey from Anonymous class abc() method");
			   }
		i2.abc();
		
		B b1 = new B()
			{
				System.out.println("hey from anonymous class of B");
			}
		b1.demo();
	
		I i3 = ()-> System.out.println("Hey from Lambda Expression");
		i3.abc();
	}
}