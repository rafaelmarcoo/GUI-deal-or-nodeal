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

/*
    This class has a WRITE DB method which stores and writes the errors the user triggers
    throughout their gameplay
*/
public class DBErrorLog implements IDBErrorLog
{
    @Override
    public void dbErrorLog(String firstName, String lastName, String action)
    {
        // Current time
        LocalDateTime time = LocalDateTime.now();
        Timestamp timeStamp = Timestamp.valueOf(time);

        String dbURL = "jdbc:derby:dealornodealDB;create=true";

        try
        {
            // Make connection
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            // If the table does not exist then create it
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, firstName + "_" + lastName + "_ERRORLOG", null);
            if(!rs.next())
            {
                stmt.executeUpdate("CREATE TABLE " + firstName + "_" + lastName + "_ERRORLOG"
                    + " (TIMESTAMP TIMESTAMP, "
                    + "ACTION VARCHAR(256))");
            }
            
            // Insert and update the table with the errors
            String insertQuery = "INSERT INTO " + firstName + "_" + lastName + "_ERRORLOG"
                    + " (TIMESTAMP, ACTION) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setTimestamp(1, timeStamp);
            pstmt.setString(2, action);
            pstmt.executeUpdate();
            
//            // Test
//            rs = stmt.executeQuery("SELECT * FROM " + firstName + "_" + lastName + "_ERRORLOG");
//            while(rs.next())
//            {
//                System.out.println("Timestamp: " + rs.getTimestamp("TIMESTAMP") + "\n"
//                        + "Action: " + rs.getString("ACTION") + "\n");  
//            }
//            System.out.println("");
            conn.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
    
//    public static void main(String[] args) 
//    {
//        dbErrorLog("RAFAEL", "MARCO", "INVALID INPUT!");
//    }
}
