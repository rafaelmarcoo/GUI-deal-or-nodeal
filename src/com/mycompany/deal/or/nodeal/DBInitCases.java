/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

/**
 *
 * @author rafae
 */
public class DBInitCases 
{ 
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    public static void dbInitCases()
    {
        try
        {
            FileReader fr = new FileReader("./resources/UIresources/prizes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            try
            {
                Connection conn = DriverManager.getConnection(dbURL);
                Statement stmt = conn.createStatement();
                
                DatabaseMetaData dbMeta = conn.getMetaData();
                ResultSet rs = dbMeta.getTables(null, null, "PRIZES", null);
                if(!rs.next())
                {
                    stmt.executeUpdate("CREATE TABLE Prizes"
                            + " (MONEY DOUBLE)");
                }
                
                String moneyStr;
                while((moneyStr = br.readLine()) != null)
                {
                    double d = Double.parseDouble(moneyStr);
                    
                    String insertQuery = "INSERT INTO Prizes"
                            + " (MONEY) VALUES(?)";
                    PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                    pstmt.setDouble(1, d);
                    pstmt.executeUpdate();
                }
                br.close();
                
                // Test
                rs = stmt.executeQuery("SELECT * FROM Prizes");
                while(rs.next())
                {
                    System.out.println("Value: " + rs.getDouble("MONEY"));
                }
                conn.close();
            }
            catch(Exception E)
            {
                E.printStackTrace();
            }
        }
        catch(FileNotFoundException E)
        {
            System.out.println("File not found!");
        }
    }
    public static void main(String[] args) 
    {
        dbInitCases();
    }
}
