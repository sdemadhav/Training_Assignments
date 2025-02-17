import java.util.function.Predicate;
import java.util.*;
public class PredicateDemo
{
	public static void main(String[] args)
	{
		int arr[] = {11,22,33,44,55,66,77,88,99};

		//Traditional Method
		/* 
		evenNumbers(arr);
		oddNumbers(arr);
		*/
		
		//Lets use predicate to check conditions like lambda expressions
		
		//Checks if even number
		Predicate<Integer> p1 = (x)-> x%2==0;

		//Checks if odd number
		Predicate<Integer> p2 = (x)-> x%2==1;

		//Checks if number is greater than 50
		Predicate<Integer> p3 = (x)-> x>50;



		//prints even numbers
		System.out.println("Printing Even Numbers:-");
		process(arr,p1);
		
		//prints odd number
		System.out.println("Printing odd Numbers:-");
		process(arr,p2);
		
		//Prints odd Numbers by using .negate() on p1 predicate
		System.out.println("Printing Odd Numbers using Negate on P1 Predicate:-");
		process(arr,p1.negate());
		
		//Prints numbers greater than 50
		System.out.println("Printing Numbers greater than 50:-");
		process(arr,p3);

		//Prints numbers less than 50
		System.out.println("Printing Numbers less than 50:-");
		process(arr,p3.negate());
		
		//Prints numbers greater than 50 and odd
		System.out.println("Printing Numbers greater than 50 AND odd-");
		process(arr,p3.and(p1.negate()));

		//Prints numbers lesser than 50 and odd
		System.out.println("Printing Numbers lesser than 50 AND odd-");
		process(arr,p3.negate().and(p1.negate()));
	
		//Prints numbers lesser than 50 OR odd
		System.out.println("Printing Numbers lesser than 50 OR odd-");
		process(arr,p3.negate().or(p1.negate()));
		
		
	}

	public static void process(int[] arr, Predicate<Integer> p)
	{
		for(int x: arr)
		{	
			if(p.test(x))
			{
				System.out.println(x);
			}
			
		}
		System.out.println("-----------------------------");
	}
	
	public static void evenNumbers(int arr[])
	{
		for(int i = 0; i<arr.length; i++)
		{	
			if(arr[i]%2==0)
			System.out.println(arr[i]);
		}
		System.out.println("-----------------------------");
	}
	public static void oddNumbers(int arr[])
	{
		for(int i = 0; i<arr.length; i++)
		{	
			if(arr[i]%2==1)
			System.out.println(arr[i]);
		}
		System.out.println("-----------------------------");

	}
}