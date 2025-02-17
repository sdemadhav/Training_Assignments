import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

class Database{
    public static void savePatient(){
        try {
            
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","tiger");
            PreparedStatement pstmt = con.prepareStatement("insert into Patient(p_name, p_age, p_city, p_disease) values(?,?,?,?)");
            
            System.out.println("Enter Name: ");
            String name = HealthCare.br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt( HealthCare.br.readLine());
            System.out.println("Enter City: ");
            int salary =  HealthCare.br.readLine();
            System.out.println("Enter Disease: ");
            String designation =  HealthCare.br.readLine();

            
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, city);
            pstmt.setString(4, disease);
            pstmt.execute();

            pstmt.close();
            con.close();    
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public static void saveDoctor(){
       
    }
    public static void deletePatient(){
        
        try(JdbcRowSet rs = RowSetProvider.newFactory().createJdbcRowSet())
        {
            rs.setUrl("jdbc:postgresql://localhost:5432/postgres");
            rs.setUsername("postgres");
            rs.setPassword("tiger");

            System.out.println("Enter patient ID to delete: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int id = Integer.parseInt(br.readLine());
            rs.setCommand("SELECT * FROM Patient WHERE eid = ?");
            rs.setInt(1, id);
            rs.execute();  // Execute the query to move the cursor to the first row

            if (rs.next()) {
                System.out.println("Patient To Be Deleted: ");
                System.out.println("Patient ID: " + rs.getInt(1));
                System.out.println("Name: " + rs.getString(2));
                System.out.println("Age: " + rs.getInt(3));
                System.out.println("City: " + rs.getString(4));
                System.out.println("Disease: " + rs.getString(5));
            } else {
                System.out.println("Patient not found.");
            }

                // Closing the current result set to prepare for the DELETE command
                rs.close();

                // we are creating a new RowSet object as rs is closed so all resources for rs are removed
                try (JdbcRowSet deleteRs = RowSetProvider.newFactory().createJdbcRowSet()) {
                    deleteRs.setUrl("jdbc:postgresql://localhost:5432/postgres");
                    deleteRs.setUsername("postgres");
                    deleteRs.setPassword("tiger");
                    deleteRs.setCommand("DELETE FROM Patient WHERE eid = ?");
                    deleteRs.setInt(1, id);
                    deleteRs.execute();
                    System.out.println("Patient Deleted Successfully.");
                }

            } 
                
            catch (SQLException e) {
                System.out.println(e);
            }
    
    }

        
}


class Patient{
    int p_id;
    String name;
    int age;
    String city;
    String disease;
    int d_id;
}
class Doctor
{
    int d_id;
    String name;
    int age;
    String specialization;
    int experience;
}


public class HealthCare
{
    static BufferedReader br = new Bufferedreader(new InputStreamReader(System.in));
    public static void main(String[] args)
    {
        int ch1 = 0, ch2 = 0;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create User");
            System.out.println("2. Display Users");
            System.out.println("3. Appraisal");
            System.out.println("4. Delete a Record");
            System.out.println("5. Search user");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");

            ch1 = Menu.readChoice(6,br);

            switch (ch1) {
                case 1:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. Create Patient");
                        System.out.println("2. Create Doctor");
                        System.out.println("3. Back");
                        System.out.println("--------------------------------------------");

                        ch2 = Menu.readChoice(3,br);

                        switch (ch2) {
                            case 1:
                                Database.savePatient();
                                break;
                            case 2:
                                Database.saveDoctor();
                                break;
                        }
                        
                    } while (ch2 != 6);
                    break;

                case 2:
                    
                    DisplayUserOptions.displayUsers();
                    break;

                case 3:
                    RaiseSalary.raiseSalary();
                    break;

                case 4:
                    Database.deleteUser();
                    break;
                case 5:
                    SearchUserOptions.searchUser();
                    break;
            }
        } while (ch1 != 6);
        System.out.println("Total Employees Present in the Company: " + DisplayUserOptions.userCount);
    }
}