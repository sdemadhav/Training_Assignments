//Early Instantiation Singleton class
final class Principal
{
	static private final Principal p1 = new Principal();

	private Principal()
	{
		System.out.println("Creating a object in via Early Initialization");
	}
	public static Principal getPrincipal()
	{
		return p1;
	}
}

/*
//Lazy Initialization Singleton class
final class Principal
{
	//why this is not final? since we are just declaring here and initializing in the getPrincipal object 
	static private Principal p1 = null;

	private Principal()
	{
		System.out.println("Creating a object in via Lazy Initialization");
	}
	public static Principal getPrincipal()
	{
		//If its the first time , create an object else return p1 itself .
		if(p1==null)
		{p1 = getPrincipal();}
		return p1;
	}
}

*/

public class Singleton 
{
	public static void main(String[] args)
	{	
		Principal p1= Principal.getPrincipal();
		Principal p2 = Principal.getPrincipal();
		Principal p3 = Principal.getPrincipal();
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

	}
}