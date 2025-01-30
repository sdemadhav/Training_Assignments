import java.sql.*;

public class ResultSetDemo
{
	public static void main(String[] args)
	{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");		
		
		//Default statement object that is by default forward only , so we cant use methods like absolute or previous to reverse the order of 			//resultset
		Statement stmt = con.createStatement();
		//Read Only Means no Update operations allowed
		Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		//This statement allows us to change the direction of scroll as well as allow updates on the resultSet
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITVE, ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = stmt.executeQuery("select * from Employee");
		
		while(rs.next())
		{
			System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();
	
		}
		while(rs.previous())
		{
			System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();
	
		}
		rs.absolute(3); //Skips to the abosolute position defined. This skips to 3rd row as indexing is 1-based.
		rs.relative(3); //Skips to the relative position defined. This skips to 3 rows ahead.
		System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();

		rs.relative(-3); //Skips to the relative position defined. This skips to 3 rows behind.
		System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();	

		rs.last(); //skips to last row , no matter wherever the cursor is 
		System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();

		rs.first(); //skips to last row , no matter wherever the cursor is 
		System.out.println(rs.getString(1));
			System.out.println(rs.getInt(2));
			System.out.println();


		/*some other methods: 
					afterLast()   			-> takes the cursor to just next row after last row
					beforeFirst() 			-> takes the cursor to just one row before first row
					isAfterlast() 			->returns true if cursor is beyond the last row
					isFirst()     			-> returns true if cursor is before the first row
					row()         			-> returns the row number of the current row where cursor is
					getRowId( int columns idx)  	-> returns the value of the given column in the current row
		*/


		
		//Always close connections and resources
		rs.close();
		stmt.close();
		con.close();
	}
}