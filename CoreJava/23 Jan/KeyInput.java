import java.io.*;
import java.io.IOException; //in IO Class always handle exceptions
public class KeyInput
{
	public static void main(String[] args)
	{
		try
		{
		//System in --> takes input in ascii
		//InputStreamReader --> converts ascii to byteStream
		//BufferedReader --> reads the input stream
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please enter your name: ");
		String name = br.readLine();
		System.out.println("Your name is: "+name);
		
		System.out.print("Please enter your age: ");
		int age = Integer.parseInt(br.readLine());
		System.out.println("Your age after 10 years: "+(age+10));
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}