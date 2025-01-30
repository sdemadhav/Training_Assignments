import java.sql.*;
import java.io.*;
public class ReadFromTable
{
	public static void main(String[] args)
	{
	try
	{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter Table name: ");
		String table = br.readLine();

		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("select * from "+table);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columns = rsmd.getColumnCount();
		
		while(rs.next())
		{
			for(int i=1 ; i<=columns ; i++)
			{
				System.out.println(rsmd.getColumnName(i)+" : "+ rs.getString(i));
			}
			System.out.println();

		}
		rs.close();
		stmt.close();
		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	}
}