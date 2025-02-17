import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;


// New class for Displaying Employee Options in one place rather than 
interface connection
{
	public MongoDatabase makeConnection();
}

class DbConnection implements connection
{
		
		public MongoDatabase makeConnection()
		{
			MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
			MongoDatabase database = mongoClient.getDatabase("demodb");
			return database;
		}
	    
		
	public MongoCollection<Document> getCollection()
	{
		MongoDatabase database = makeConnection();
		MongoCollection<Document> collection= database.getCollection("empManageApp");
		return collection ;
	}
}
class DisplayEmployeeOptions {
	DbConnection db = new DbConnection();
	MongoCollection<Document> collection = db.getCollection();
	static int employeeCount = 0;

    public static void displayEmployees() throws Exception {
        
        DisplayEmployeeOptions obj = new DisplayEmployeeOptions();
        
        try {
            
        	FindIterable<Document> i = obj.collection.find();
        	for(Document d : i)
    		{
        		DisplayEmployeeOptions.employeeCount++;
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        if (DisplayEmployeeOptions.employeeCount == 0) {
            System.out.println("No Employee Records Found");
            return;
        }
    
        System.out.println("Enter choice to sort by: ");
        System.out.println("1. ID Wise");
        System.out.println("2. Name Wise");
        System.out.println("3. Designation Wise");
        System.out.println("4. Age Wise");
        System.out.println("5. Salary Wise");
        System.out.println("6. Department Wise");
        System.out.println("7. Back");
    
        int ch = Menu.readChoice(6,EmpManageApp.br);
    
        try {
        	Bson sort;
        	FindIterable<Document> i ;
            switch (ch) {
                case 1:
                	sort = Sorts.ascending("eid");
                	i = obj.collection.find().sort(sort);            		
                
                    break;
                case 2:
                	sort = Sorts.ascending("name");
                	i = obj.collection.find().sort(sort);  
                    break;
                case 3:
                	sort = Sorts.ascending("designation");
                	i = obj.collection.find().sort(sort);
                    break;
                case 4:
                	sort = Sorts.ascending("designation");
                	i = obj.collection.find().sort(sort);
                    break;
                case 5:
                    System.out.println("What order do you want to display in?");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int choice = Menu.readChoice(2,EmpManageApp.br);
                    if (choice == 1) {
                    	sort = Sorts.ascending("salary");
                    	i = obj.collection.find().sort(sort);
                    } else {
                    	sort = Sorts.descending("designation");
                    	i = obj.collection.find().sort(sort);
                    }
                    break;
                case 6:
                	sort = Sorts.ascending("department");
                	i = obj.collection.find().sort(sort);
                    break;
                default:
                    System.out.println("Invalid Choice, please select from the given options");
                    return;
            }
    
            System.out.println("Employee Details: ");
            for(Document d : i){
            	System.out.println("Employee ID: " + d.getInteger("eid"));
                System.out.println("Name: " + d.getString("name"));
                System.out.println("Age: " + d.getInteger("age"));
                System.out.println("Salary: " + d.getInteger("salary"));
                System.out.println("Designation: " + d.getString("designation"));
                System.out.println("Department: " + d.getString("department"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        if (ch != 7) {
            System.out.println("Total Employees: " + DisplayEmployeeOptions.employeeCount);
        }
    }
}

class RaiseSalary {
    DbConnection db = new DbConnection();

    public static void raiseSalary() {
        RaiseSalary obj = new RaiseSalary();
        try {
            System.out.println("Enter ID: ");
            int id = Integer.parseInt(EmpManageApp.br.readLine());
            Bson filter = Filters.eq("eid", id);
            Document i = obj.db.getCollection().find(filter).first();

            if (i == null) { 
                System.out.println("No such record exists");
                return;
            } else {
                System.out.println("Enter increment amount: "); 
                int increment = Integer.parseInt(EmpManageApp.br.readLine()); 
                obj.db.getCollection().updateOne(filter, Updates.set("salary", i.getInteger("salary") + increment));
                System.out.println("Salary updated successfully.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// SearchEmployeeOptions class for searching employee by various fields
class SearchEmployeeOptions {
    DbConnection db = new DbConnection();
    public static void searchEmployee() throws Exception {
        SearchEmployeeOptions obj = new SearchEmployeeOptions();
        try  {
            System.out.println("Search by? ");
            System.out.println("1. ID");
            System.out.println("2. Name");
            System.out.println("3. Designation");
            int choice = Menu.readChoice(3, EmpManageApp.br);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FindIterable<Document> i = null;
            Bson filter;
            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    int id = Integer.parseInt(br.readLine());
                    filter = Filters.eq("eid", id);
                    i = obj.db.getCollection().find(filter);
                    break;
                case 2:
                    System.out.println("Enter Name: ");
                    String name = br.readLine();
                    filter = Filters.eq("name", name);
                    i = obj.db.getCollection().find(filter);
                    break;
                case 3:
                    System.out.println("Enter Designation: ");
                    String designation = br.readLine();
                    filter = Filters.eq("designation", designation);
                    i = obj.db.getCollection().find(filter);
                    break;
            }

            System.out.println("Employee Details: ");
            for (Document d : i) {
                System.out.println("Employee ID: " + d.getInteger("eid"));
                System.out.println("Name: " + d.getString("name"));
                System.out.println("Age: " + d.getInteger("age"));
                System.out.println("Salary: " + d.getInteger("salary"));
                System.out.println("Designation: " + d.getString("designation"));
                System.out.println("Department: " + d.getString("department"));
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Database {
	private MongoCollection<Document> collection = new DbConnection().getCollection();
	
    public static void saveData() {
    	Database db = new Database();
    	
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.println("Enter Employee id: ");
            int eid = Integer.parseInt(br.readLine());
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            System.out.println("Enter Salary: ");
            int salary = Integer.parseInt(br.readLine());
            System.out.println("Enter Designation: ");
            String designation = br.readLine();
            System.out.println("Enter Department: ");
            String department = br.readLine();
            
            db.collection.insertOne(new Document().append("eid",eid).append("name",name).append("age",age).append("salary", salary).append("designation",designation).append("department", department));
//            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void saveCEO() {
    	Database db = new Database();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.println("Enter Employee id: ");
            int eid = Integer.parseInt(br.readLine());
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            int salary = 50000;
            String designation = "CEO";
            System.out.println("Enter Department: ");
            String department = br.readLine();
            
            db.collection.insertOne(new Document().append("eid",eid).append("name",name).append("age",age).append("salary", salary).append("designation",designation).append("department", department));
//            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    public static void saveClerk() {
    	Database db = new Database();
    	
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.println("Enter Employee id: ");
            int eid = Integer.parseInt(br.readLine());
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            int salary = 30000;
            String designation = "Clerk";
            System.out.println("Enter Department: ");
            String department = br.readLine();
            
            db.collection.insertOne(new Document().append("eid",eid).append("name",name).append("age",age).append("salary", salary).append("designation",designation).append("department", department));
//            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void saveManager() {
    	Database db = new Database();
    	
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.println("Enter Employee id: ");
            int eid = Integer.parseInt(br.readLine());
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            int salary = 30000;
            String designation = "Manager";
            System.out.println("Enter Department: ");
            String department = br.readLine();
            
            db.collection.insertOne(new Document().append("eid",eid).append("name",name).append("age",age).append("salary", salary).append("designation",designation).append("department", department));
//            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void saveProgrammer(){
    	Database db = new Database();
    	
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.println("Enter Employee id: ");
            int eid = Integer.parseInt(br.readLine());
            System.out.println("Enter Name: ");
            String name = br.readLine();
            System.out.println("Enter Age: ");
            int age = Integer.parseInt(br.readLine());
            int salary = 30000;
            String designation = "Programmer";
            System.out.println("Enter Department: ");
            String department = br.readLine();
            
            db.collection.insertOne(new Document().append("eid",eid).append("name",name).append("age",age).append("salary", salary).append("designation",designation).append("department", department));
            
//            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

	public static void deleteEmployee() {
		Database db = new Database();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the ID of the employee to be deleted: ");
		try {
			int id = Integer.parseInt(br.readLine());
			Bson filter2 = Filters.eq("eid",id);
			Document d = db.collection.find(filter2).first();
			if(d.isEmpty())
			{
				System.out.println("No such record exists");
				return;
			}
			else 
			{
				db.collection.deleteOne(filter2);
			}
			
		}
			
		catch(IOException e) {
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		
	}
}

public class EmpManageApp {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
    public static void main(String[] args) throws Exception {
    	
        int ch1 = 0, ch2 = 0;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Create Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Appraisal");
            System.out.println("4. Delete a Record");
            System.out.println("5. Search Employee");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------");

            ch1 = Menu.readChoice(6,br);

            switch (ch1) {
                case 1:
                    do {
                        System.out.println("---------------------------------------------");
                        System.out.println("1. Create CEO");
                        System.out.println("2. Create Clerk");
                        System.out.println("3. Create Programmer");
                        System.out.println("4. Create Manager");
                        System.out.println("5. Others");
                        System.out.println("6. Back");
                        System.out.println("--------------------------------------------");

                        ch2 = Menu.readChoice(6,br);

                        switch (ch2) {
                            case 1:
                                Database.saveCEO();
                                break;
                            case 2:
                                Database.saveClerk();
                                break;
                            case 3:
                                Database.saveProgrammer();
                                break;
                            case 4:
                                Database.saveManager();
                                break;
                            case 5:
                                Database.saveData();
                                break;
                        }
                        
                    } while (ch2 != 6);
                    break;

                case 2:
                    
                    DisplayEmployeeOptions.displayEmployees();
                    break;

                case 3:
                    RaiseSalary.raiseSalary();
                    break;

                case 4:
                    Database.deleteEmployee();
                    break;
                case 5:
                    SearchEmployeeOptions.searchEmployee();
                    break;
            }
        } while (ch1 != 6);
        System.out.println("Total Employees Present in the Company: " + DisplayEmployeeOptions.employeeCount);
    }
}

// Custom Exception Classes
class AgeCustomException extends RuntimeException {
    public AgeCustomException() {
        super();
    }

    public AgeCustomException(String msg) {
        super(msg);
    }
}

class InvalidChoiceException extends RuntimeException {
    public InvalidChoiceException() {
        super();
    }

    public InvalidChoiceException(String msg) {
        super(msg);
    }
}

class InvalidNameFormatException extends RuntimeException {
    public InvalidNameFormatException() {
        super();
    }

    public InvalidNameFormatException(String msg) {
        super(msg);
    }

    public void display() {
        String rules = " The following rules must be followed:\n\t1. Name must have 2 words.\n\t2. Both first and last names must be entered.\n\t3. Both first and last names must start with a capital letter.\n";
        System.out.println(rules);
    }
}

// Menu class
class Menu {

    public static int readChoice(int maxChoice, BufferedReader br) {
        while (true) {
            System.out.println("Enter your choice:");
            try {
                int choice = Integer.parseInt(br.readLine());
                if (choice < 1 || choice > maxChoice) {
                    throw new InvalidChoiceException();
                }
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number only.");
            } catch (InvalidChoiceException e) {
                System.out.println("Reason: " + e.getMessage());
            }
            catch(IOException e)
            {
            	System.out.println("Reason: " + e.getMessage());
            }
        }
    }
}

