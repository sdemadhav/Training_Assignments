/*
1.Till v7 we could only declare a method inside an interface and by default it was abstract.

2.from version 8 , we can use static and default keywords only in our method declaration in Interface

3.from version 9 onwards we can also decalre a method as private , so that internally methods can communicate within but not accessible outside.
This way we dont have to modify exisiting method bodies again and again ,rather we can have new private methods be called in the existing bodies.
*/

interface I
{
	public void abc(); //abstract by default. Upto V7 only this much we could do with a method in interfaces

	/*by default all methods are 'abstract' in an interface class so we can't provide body. So in order to provide body to a method in interfcae we can 	use default before that method name*/

	public default void xyz()
	{
		System.out.println("Default Keyword used in xyz()");
	}

	public static void atoz() 
	{
		System.out.println("static method atoz() being called");
	}
	
}
class A implements I
{
	public void abc()
	{
		System.out.println("abc() method implemented in class A being called.")	
	}
}

interface X
{
	public default void hello()
	{
		System.out.println("hello() method being invoked from X interface");
	}
}

interface Y
{
	public default void hello()
	{
		System.out.println("hello() method being invoked from Y interface");
	}
}
class B implements X,Y
{
	public void hello()
	{
		System.out.println("hello() method in class B invoked.");
		X.super.hello()
		Y.super.hello();
	}
}

public interface LatestInterface
{
	public static void main(String[] args)
	{
		System.out.println("Hello Everybody");
		A obj = new A();
		obj.abc();
		obj.xyz();
	}
}