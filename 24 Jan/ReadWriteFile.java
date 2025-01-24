class Employee
{
	private String name;
	private int age;
	private int salary;	
	private String designation;
	
	public Employee()
	{
		readDetails();
	}

	public void readDetails()
	{
		try
		{
			BufferedReader br = new BuferedReader(new InputStreamReader(System.in));
			System.out.println("Enter name: ");
			name= br.readLine();
			age = Integer.parseInt(br.readLine());
			System.out.println("Enter salary: ");
			salary = Integer.parseInt(br.readLine());
			System.out.println("Enter designation: ");
			designation= br.readLine();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void writeToFile()
	{
		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream("employees.csv",true));
			pw.println(name+","+","+age+","+salary+","designation);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public static void display() 
	{
		try
		{
			BuffferedReader fr = new BufferedReader(new Filereader("employees.csv"));
		}catch(Exception e)
		{
			System.out.println(e);
		}
		String line = null;
		while((line=fr.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(file,",");
		}
	}

}

public class ReadWriteFile
{
	public static void main() throws Exception
	{
		int ch=0;
		do
		{
			System.out.println("-------------------------");
			System.out.println("1.Create");
			System.out.println("2.Display");
			System.out.println("3.Exit");
			System.out.println("-------------------------");
			System.out.println("Enter choice:-");	
			ch = Integer.parseInt(br.readLine());
			switch(ch)
			{
				case 1 -> new Employee().writeToFile();
				case 2 -> Employee.display();
			}
		}while(ch!=3)	
	}
}