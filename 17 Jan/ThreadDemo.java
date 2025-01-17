import java.Thread.*;
class A extends Thread
{
	public void run()
	{
		for(int i=1;i<=20; i++)
		{
			System.out.println(Thread.currentThread().getName()+" : "+i);
			try
			{
				if(i%2==0)
				{
					Thread.sleep(3000);
				}
			}		
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
}
class B extends Thread
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName()+" : "+i);
		for(int i=1;i<=20; i++)
		{
		try
		{
			if(i%2==0)
			{	
				Thread.sleep(2000);
				
			}
		}		
		catch(Exception e)
		{
			System.out.println(e);
		}
		}

		}
	}
}
class C extends Thread
{
	public void run()
	{
		System.out.println(Thread.currentThread().getName()+" : "+i);
		for(int i=1;i<=20; i++)
		{
		try
		{
			if(i%2==0)
			{
				Thread.sleep(1000);
				
			}
		}		
		catch(Exception e)
		{
			System.out.println(e);
		}
		}

		}
	}
}

public class ThreadDemo
{
	public static void main(String args[])
	{
		
		Thread tg= new ThreadGroup("Demo");
		
		Thread a1 = new Thread(tg, new A());
		Thread b1 = new Thread(tg, new B());
		Thread c1 = new Thread(tg, new C());

		
/*
		A a1 = new A();
		A b1 = new B()
		A c1 = new C()	
*/
			
		a1.setName("Rita");
		b1.setName("Geeta");
		c1.setName("Sita");

		a1.start();
		b1.start();
		c1.start();

//		b1.setPriority(8);		

		
		for(int i=0; i<=20 ;i++)
		{
			System.out.println("*******Main :"+i+" *******");
			

			if(i==5)
				th.suspend()
			if(i==8)
				tg.resume();
			if(i==13)
				tg.stop();

			if(i==12)
				System.out.println("a1 is alive: "+a1.isAlive());
				System.out.println("b1 is alive: "+b1.isAlive());
				System.out.println("c1 is alive: "+c1.isAlive());

			
			Thread.sleep(300);
		}
		a1.join();
		c1.join();
		System.out.println("-----Main Exit-----");
	}
}