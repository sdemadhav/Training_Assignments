import java.sql.*;
import java.io.*;

public class JdbcDemo2 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into EMPLOYEE values(?,?)");
            
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.execute();
            
            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//Commands to run
//javac -cp .;pg_driver\postgresql-42.7.5.jar JdbcDemo2.java
//java -cp .;pg_driver\postgresql-42.7.5.jar JdbcDemo2

//Database username : postgres, password: tiger
//Database name: postgres