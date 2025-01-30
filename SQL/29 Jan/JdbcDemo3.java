//Callable Statements in JDBC is used to run procedures that are defined in the Database sql file .
//We can just call those procedures from our java code by making  a simple callableStatement object and running it remotely.
import java.sql.*;
import java.io.*;

public class JdbcDemo3 {
    public static void main(String[] args) {
        try {
           	            
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
		
		PreparedStatement pstmt = con.prepareStatement("");
		
		CallableStatement cstmt = con.prepareCall("CALL dummy_record()");
		
		//We can use same placeholders in callableStatement as well if we wanted just like prepared statement
		//cstmt.setString(1,name)	;
		//cstmt.setInt(2,age);
	
		cstmt.close();           
            	con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
