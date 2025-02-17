interface I
{
	void abc();
}

class A
{
	public A()
	{
		System.out.println("From constructor A()");
	}
	public static void classMethod()
	{
		System.out.println("From static classMethod() in class A");
	}
	public void instanceMethod()
	{
		System.out.println("From instance method in class A");
	}
}

class B
{
	public static void demo()
	{
		System.out.println("From class B , demo() is invoked");
	}
}

class C
{
	public C()
	{
	}
	
	public String toString
	{
		System.out.println("Hello from ToString method in class C");
		returns "";
	}
}

interface J
{
	Object getObject();
	
}

public class MethodReference
{
	public static void main(String[] args)
	{
		//since instance method is not static , we must create an object of A and then give a method reference

		I i1 = A::classMethod;
		i1.abc();
		
		//since instance method is not static , we must create an object of A and then give a method reference
		I i2 = new A()::instanceMethod;
		i2.abc();
		
		I i3 = A::new;
		i3.abc();

		I i4 = B::demo;
		i4.abc();

		I j1 = C::new;
		Object obj = j1.getObject();
		System.out.println(obj);

		J j2 = Thread::new;
		System.out.println(j2.getObject());

			
	}
}