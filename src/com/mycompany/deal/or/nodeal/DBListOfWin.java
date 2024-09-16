/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rafae
 */
import java.sql.*;
import java.time.LocalDate;

public class DBListOfWin 
{
    public static void dbListWin(String firstName, String lastName, double winnings)
    {
        String dbURL = "jdbc:derby:dealornodealDB;create=true";
        LocalDate date = LocalDate.now();
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "LISTOFWINNERS", null);
            if(!rs.next())
            {
                stmt.executeUpdate("CREATE TABLE ListOfWinners ("
                        + "DATE_PLAYED DATE, "
                        + "FIRST_NAME VARCHAR(50), "
                        + "LAST_NAME VARCHAR(50), "
                        + "AMOUNT_WON DOUBLE)");
            }
            
            String insertQuery = "INSERT INTO ListOfWinners (DATE_PLAYED, FIRST_NAME, LAST_NAME, AMOUNT_WON)"
                    + " VALUES (?, ?, ? ,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setDouble(4, winnings);
            pstmt.executeUpdate();
            
            // Test
            rs = stmt.executeQuery("SELECT * FROM ListOfWinners");
            while(rs.next())
            {
                System.out.println("Date: " + rs.getDate("DATE_PLAYED") + "\n"
                        + rs.getString("FIRST_NAME") + "\n"  
                        + rs.getString("LAST_NAME") + "\n" 
                        + rs.getDouble("AMOUNT_WON"));
            }
            conn.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
    
    public static void main(String[] args) 
    {
        String first = "Rafael";
        String last = "Marco";
        double amount = 1234.42;
        
        dbListWin(first,last,amount);
    }
}
