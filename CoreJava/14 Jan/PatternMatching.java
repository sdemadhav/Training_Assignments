import java.util.*;
import java.util.regex;

import java 
public class PatternMatching
{
	public static void main(String args[])
	{
		
		String s1 = "Wissen Technology";
		String s2 = "Tech"

		Pattern p1 = Pattern.compile(s2);
		Matcher m1 = p1.matcher(s1);

		System.out.println(" ' "+s1+" ' contains '"+s2+"' : "+m1.find());
		System.out.println("----------------------------------")
		
		String email = "abc@xyz.com";
		Pattern p2 = Pattern.compile("[a-zA-Z0-9.]+@[a-zA-Z]")
		

	}
}








