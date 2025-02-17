import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class A
{
	public void print1to10()
	{
		for(int i=1; i<=10; i++)
		{
			System.out.println("Range : "+i);
		}
	}
}
class B
{

	public void evenTill50()
	{
		for(int i=0; i<=50; i+=2)
		{
			System.out.println("Even : "+i);
		}
	}
}
class C
{
	public void fibonacciFrom1to1000()
	{
        int a=0,b=1,c=0;
        while(c<=1000)
        {
            System.out.println(c);
            a=b;
            b=c;
            c=a+b;
        }
	}
}
class Multithreading
{
	public static void main(String args[])
	{
        A a = new A();

        //Method 1
        Thread threadB = new Thread( new Runnable() {
            
                public void run(){
                    new B().evenTill50();
                }
        }
        );

        //Method 2: Recommended
        Thread threadC= new Thread(new C()::fibonacciFrom1to1000);

        //Method 3:ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(() -> a.print1to10());
        threadB.start();
        threadC.start();

        executor.shutdown();
        

	}
}