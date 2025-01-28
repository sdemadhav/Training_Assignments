package p1;

public class A
{
	public int x=5;
	private int y=10;
	protected int z = 20;
	int q = 30;

	public static void main(String[] args)
	{
		System.out.println("Hello ! I am coming form package p1");
	}
}

class B extends A 
{
	public void display()
	{
		System.out.println("Display method in A");
	}
}

class C
{

	public void display()
	{
		System.out.println("Display method from C");
	}

}

---------------------------------------------------------------
package p2;
import package p1.A;

public class D
{
	
	public void display()
	{
		A a1 = new A();
		System.out.println("a1.x);
		//System.out.println("a1.y);    //Private var not acc anywhere outside original class
		//System.out.println("a1.z);
		//System.out.println("a1.q);


	}
}

public class E extends A
{
	public void display()
	{
		System.out.println("x);
		//System.out.println("y);
		System.out.println("z);
		//System.out.println("q);
		
	}
}

public class F extends E
{
	public void display()
	{
		System.out.println("x);
		//System.out.println("y);
		System.out.println("z);
		//System.out.println("q);
	}
}

---------------------------------------------------------------



