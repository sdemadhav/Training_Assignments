import java.util.io.*;
/*
final class Student implements Serializable
{
	private final int rollNo;
	private final String name;
	private final int standard;
	
	public Student(int rn , String n , int s)
	{
		rollNo = rn;
		name = n;
		standard = s;
	}
	public int getRollNo
	{
		return rollNo;
	}
	public String getName
	{
		return name;
	}
	public String getStandard()
	{
		return standard;
	}
	public String toString()
	{
		return "Student["Roll no: "+rollNO+", Name: "+name+", Standard: "+standard+" ]";
	}
	
	public int hashcode()
	{
		return rollNo;	
	}
	public boolean equals(Object obj)
	{
		Student s1 = (Student)obj;
		if(this.rollNo == s1.rollNo)&&(this.name.equals(s1.name)) && (this.standard == s1.standard)
			return true;
		else{ false ;}
			

	}
	
}
*/
class Transaction
{
	int fromAccNo;
	int toAccNo;
	String ifsc;
	int amount;
}

// record class does exactly the same as above but with a  single keyword 

//Default way to call record class: this one uses canonical constructors
// record Student(int rollNo, String name , int standard)

//Custom record class : Allows custom methods and user defined constructors
record Student()
{
	Static String schoolName= "DPS";
	
	Student()
	{
		this(22,"Raj",3); //default student object incase no argument is passed for object.
	}
	Student(int rollNo, String name , int standard)
	{
		if(rollNo<1)
		{ throw new InvalidRollNoException();}
		this.rollNo = rollNo;
		this.name = name ;
		this.standard = standard;

	}
	public void display()
	{
		System.out.println()"SchoolName :"+schoolName;
	}
	
	public boolean equals(Object obj)
	{	
		if(this.rollNo == ((Student)obj).rollNo)
		{
			return true;
		}
		return false;
	}
		
}

public class Immutable
{
	public static void main(String args[])
	{
		Student s1 = new Student(11,"Rinku",5);
		Student s2 = new Student(11,"Rinku",5);
		
		System.out.println(s1);
		System.out.println(s2);
	
		System.out.println(s1.hashcode());
		System.out.println(s2.hashcode());

		
	}
}