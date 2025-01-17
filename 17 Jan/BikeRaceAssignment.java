import java.util.*;
import java.util.Scanner;
class Biker implements Runnable
{
	private int startTime ;
	private int endTime;
	private int speed;
	private String name;
	private String[] names;
	public Biker(String[] names , int raceDistance)
	{
		this.names = names;
		Random rand = new Random();
		this.name = names[rand.nextInt(names.length)];
		this.speed = rand.nextInt(100, 200);
		this.startTime = 0;
		this.endTime = startTime + raceDistance / speed;
	}
	public void run()
	{
		System.out.println(this.name + " has started the race");
		System.out.println();
	}
}

public class BikeRaceAssignment
{
	public static void main(String[] args)
	{
		ThreadGroup bikerGroup = new ThreadGroup("BikerGroup");
		final int raceDistance  = 1000;
		String[] names = {"Madhav", "sanat", "karan" ,"Jonathan","Atul","Aditya","Sumit","Pushpender","Jatin","Jasprit","Dhoni","Virat","Rohit","Rahul","Rishabh","Shreyas","Shikhar","Hardik","Ravindra","Ravichandran"};
		
		System.out.println("----------Welcome To Blazing Bikers Game ----------");
		System.out.println("Enter the number of Bikers in the game:- ");
		final int bikers = new Scanner(System.in).nextInt();
		Thread[] bikerThread = new Thread[bikers];
		
		for(int i=0; i<bikers; i++)
		{
			Biker biker = new Biker(names, raceDistance);
			bikerThread[i] = new Thread(bikerGroup, biker);
			bikerThread[i].start();
		}


	}

}

