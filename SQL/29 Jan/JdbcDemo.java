import java.sql.*;
public class JdbcDemo
{
	public static void main(String[] args)
	{
		try
		{
			
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
			Statement stmt = con.createStatement();
			 
			//Optional Statement after java 8 onwards, before that must use
			Class.forName("org.postgresql.Driver");
			
			//stmt.executeUpdate("insert into EMPLOYEE values('Madhav Jha',18)");
					

			//Fetch Infomation from Database to the Application	
			/*
			ResultSet rs = stmt.executeQuery("Select * from EMPLOYEE");
			while(rs.next())
			{
				System.out.println("Name: "+rs.getString(1));
				System.out.println("Age "+ rs.getString(2));
				System.out.println();
			}
			
			rs.close();
			*/
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
}