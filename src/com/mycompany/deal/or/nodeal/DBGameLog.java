/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.sql.*;
import java.time.LocalDateTime;

/**
 *
 * @author rafae
 */
public class DBGameLog
{
//    @Override
    public static void dbGameLog(String firstName, String lastName, String action)
    {
        LocalDateTime time = LocalDateTime.now();
        Timestamp timeStamp = Timestamp.valueOf(time);
        
        String dbURL = "jdbc:derby:dealornodealDB;create=true";
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, firstName + "_" + lastName + "_GAMELOG", null);
            if(!rs.next())
            {
                stmt.executeUpdate("CREATE TABLE " + firstName + "_" + lastName + "_GAMELOG" + "("
                    + "TIMESTAMP TIMESTAMP, "
                    + "ACTION VARCHAR(256))");
            }
            
            String insertQuery = "INSERT INTO " + firstName + "_" + lastName + "_GAMELOG"
                    + " (TIMESTAMP, ACTION) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setTimestamp(1, timeStamp);
            pstmt.setString(2, action);
            pstmt.executeUpdate();
            
            // Test
            rs = stmt.executeQuery("SELECT * FROM " + firstName + "_" + lastName + "_GAMELOG");
            while(rs.next())
            {
                System.out.println("Timestamp: " + rs.getTimestamp("TIMESTAMP") + "\n"
                        + "Action: " + rs.getString("ACTION") + "\n");  
            }
            System.out.println("");
            conn.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
        dbGameLog("RAFAEL", "MARCO", "INITIALISED FIRST NAME");
    }
}
