import java.sql.*;
import java.io.*;

public class TransactionDemo {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
	    //Set Auto-Commit off to enable transaction control, else regardless of what you set commit or rollback , the records will be commited
	    con.setAutoCommit(false);
            PreparedStatement pstmt = con.prepareStatement("insert into EMPLOYEE values(?,?)");
	    
	    for(int i=0; i<10 ;i++)
	    {
            	System.out.println("Enter Name: ");
            	String name = br.readLine();
            	System.out.println("Enter Age: ");
            	int age = Integer.parseInt(br.readLine());
            	
		
            	pstmt.setString(1, name);
            	pstmt.setInt(2, age);
		System.out.println(pstmt);
            	pstmt.execute();
		if(i==5)
			{
				con.commit();
			}
		if(i==9)
			{
				//con.rollback();
			}
            }
	    
            //System.out.println(pstmt);
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
