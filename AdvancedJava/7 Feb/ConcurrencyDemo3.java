import java.util.concurrent.*;

public class ConcurrencyDemo3
{
	public static void main(String[] args)
	{
		CompletableFuture<> cf1 = CompletableFuture.supplyAsync(()->{
			for(int i=0; i<=10; i++)
			{
				try
				{
					Thread.sleep(1000);//delaying on purpose to run he TimeOut Exceptionally method condition.If commented works within 																		     //time
				}
				System.out.println("Using supplyAsync for String type: "+i);
				return "Completed the String argument execution";
				
			}
		}).orTimeout(2,TimeUnit.SECONDS,exceptionally(s->));
		
		CompletableFuture.runAsync(()->{
			for(int i=0; i<=10; i++)
			{
				System.out.println("Using runAsync: "+i);
			}
		});

		CompletableFuture<> cf2 = CompletableFuture.supplyAsync(()->{
			double res = 0;
			for(int i=0; i<=10; i++)
			{
				System.out.println("Using supplyAsync for String type: "+i);
				return "Completed the String argument execution";
				
				
			}
			for(int j=0;j<=100;j++)
			{
				res += j;
			}
			return res;
		});

		//System.out.println("Result: "+cf1.get());
		
		//CompletableFuture<void> f = CompletableFuture.allOf(cf1,cf2);
		
		cf2.thenApplyAsync((x)->{
			System.out.println("Summing till 100: "+x);
			return x;
		});
		
		System.out.println("Result from CF1: "+cf1.get(""));
		System.out.println("Main Exit: ");
		Thread.sleep(1000);		
		
	}
}