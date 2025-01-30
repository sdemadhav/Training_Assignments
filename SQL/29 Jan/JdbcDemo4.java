import java.sql.*;
import java.io.*;

public class JdbcDemo4 {
    public static void main(String[] args) {
        try {
           	            
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");		
		Statement stmt = con.createStatement();	
		stmt.addBatch("insert into EMPLOYEE values('Aman',22)");
		stmt.addBatch("insert into EMPLOYEE values('Amanda',25)");
		stmt.addBatch("insert into EMPLOYEE values('Amanjeet',32)");
		stmt.addBatch("insert into EMPLOYEE values('Amanveer',26)");
		stmt.addBatch("insert into EMPLOYEE values('Amana',67)");		stmt.addBatch("insert into EMPLOYEE values('Amanaaa',34)");
		
		System.out.println("We shall here wait for 10 seconds to execute the whole batch , then print");
		Thread.sleep(10000);
		
		stmt.executeBatch();
	
		stmt.close();           
            	con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
