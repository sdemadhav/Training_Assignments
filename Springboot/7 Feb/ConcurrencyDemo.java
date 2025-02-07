import java.util.concurrent.*;
class Shared
{
	static int count = 0;
}
class IncThread extends Thread
{
	String name;
	Semaphore sem;
	IncThread(String name,Semaphore sem)
	{
		this.name = name;
		new Thread(this).start();
		this.sem = sem;
	}
	public void run()
	{
		try
		{
		
			sem.acquire();
			System.out.println(name+" has got the permission");
			for(int i=1; i<=5; i++)
			{
				System.out.println(name+" : "+ ++Shared.count);
				
			}
			System.out.println(name+" has released the permission");
			Thread.sleep(1000);
			sem.release();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
class DecThread extends Thread
{
	String name;
	Semaphore sem;
	DecThread(String name,Semaphore sem)
	{
		this.name = name;
		new Thread(this).start();
		this.sem = sem;
	}

	public void run()
	{
		try
		{
		
			sem.acquire();
			System.out.println(name+" has got the permission");
			for(int i=1; i<=5; i++)
			{
				System.out.println(name+" : "+ --Shared.count);
				
			}
			System.out.println(name+" has released the permission");
			System.out.println("--------------------");
			Thread.sleep(1000);
			sem.release();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

public class ConcurrencyDemo
{
	public static void main(String[] args)
	{
		Semaphore sem = new Semaphore(2); //allow only 1 thread to enter the synchronized block at a time
		
		
		new IncThread("Rajesh",sem);
		new DecThread("Vipul",sem);
		new IncThread("Ganesh",sem);
		new DecThread("Varad",sem);
		new IncThread("Suresh",sem);
		new DecThread("Bhupender",sem);
		new IncThread("Rameshangal",sem);
		new DecThread("Kukender",sem);
		new IncThread("Surender",sem);
		new DecThread("Subedar",sem);
		
		
		
	}
}
