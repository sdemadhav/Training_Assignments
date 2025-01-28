class A implements runnable
{
	//add synchroniuzed if you need entire method to be synchronized
	public void run()
	{
		System.out.println(Thread.currentThread().getName()+" is waiting for the permission......");
		//synchronizes the code of block only which is inside the synchronized block

		synchronized(this)
		{
			System.out.println(Thread.currentThread().getName()+" has got the permission.....")
			for(int i=1; i<20; i++)
			{	
				try
				{
					Thread.sleep(3000);
				}
				catch(Exception e)
				{
					System.out.println(e);
				}	
			}
		}
	}
}

public  class SynchDemo
{
	public static void main(String[] args)
	{
		A a1 = new A();
		//Since we are using Runnable interface and not extending the Thread class then we must create threads like this
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a1);
		Thread t3 = new Thread(a1);
		
		t1.setName("Aakash");
		t2.setName("Subhash");
		t3.setName("Prakash");
		
		t1.start();
		t2.start();
		t3.start();

	}
}