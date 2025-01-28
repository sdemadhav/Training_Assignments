import java.io.*;
import java.util.regex.*;

public class IODemo
{
	public static void main(String[] args)
	{
	try{ //or use try-with resources if we dont want to deal with closing resources
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the file name: ");
		String fname = br.readLine();
		br.close();
		File f = new File(fname);
		if(f.exists())
		{
			System.out.println("File Found, please tell us what do you want to perform !");
			BufferedReader br2 = new BufferedReader(new FileReader(fname));
			String line = null;
			while((line = br2.readLine()) != null )
			{
				System.out.println(line);
			}
			br2.close();
			
		}
		else
		{
			System.out.println("File Does Not Exist , we're sorry :( ");
			
		}
	}catch(IOException e)
	{
		System.out.println("Exception in IODemo.");
	}
	}
	
}