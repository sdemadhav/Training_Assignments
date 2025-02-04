//Demo for Random Access File class
import java.io.*;
public class RAFDemo
{
	public static void main(String[] args)
	{
		try{
			RandomAccessFile raf = new RandomAccessFile("abc.txt", "rws"); //the second argument specifies for what purpose or mode we are 											       //accessing the file. In this case we're doing it for read only
			//System.out.println(raf.readLine()); //Simple reading 1 Line
			//System.out.println(raf.readLine()); //Simple reading 1 Line
			//raf.writeBytes("Writing in the file blah blah"); //Replaces the file with this content
			raf.seek(2); //goes to 10th Byte Position 
			//raf.seek(raf.length()); //jumps to end of file
			raf.writeBytes("8");
			raf.seek(0);
			System.out.println(raf.readLine());
		}
		catch(Exception e)
		{
			System.out.println("Oops ! Exception caught in main method ....");
			System.out.println(e);
		}
	}
}