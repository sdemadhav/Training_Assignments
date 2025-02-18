package com.example.NetBanking.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

import com.example.NetBanking.Database.Exceptions.UserNotFoundException;

@Repository
public class DatabaseHandler {

    private Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tiger");
    }

    public boolean verifyUserDetails(String user, String password) throws UserNotFoundException, SQLException {
    	String query = "SELECT cpass FROM Customer WHERE cname = ?"; 
        
        try (Connection con = getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
             
            stmt.setString(1, user); 
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new UserNotFoundException("User does not exist, please sign up first!");
                }
                
                return password.equals(rs.getString("cpass"));
            }
        }
    }


    public Pair<Boolean, String> handleSignUp(int customerId, String user, String password, String cpassword) throws SQLException {
        if (!password.equals(cpassword)) {
            return Pair.of(false, "Passwords do not match. Please re-enter correctly.");
        }

        String checkQuery = "SELECT cid FROM Customer WHERE cid = ?";
        String insertQuery = "INSERT INTO Customer (cid, cname, cpass) VALUES (?, ?, ?)";


        try (Connection con = getDatabaseConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery);
             PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {

            checkStmt.setInt(1, customerId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) { // If an entry already exists, rs.next() will not be false hence means custId exists
                    return Pair.of(false, "Customer ID already exists. Try a different one.");
                }
            }

            insertStmt.setInt(1, customerId);
            insertStmt.setString(2, user);
            insertStmt.setString(3, password);
            int rowsInserted = insertStmt.executeUpdate();

            return rowsInserted > 0 ? Pair.of(true, user) : Pair.of(false, "Sign-up failed due to a system error.");
        }
    }

}


