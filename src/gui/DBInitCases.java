/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.deal.or.nodeal.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

/**
 *
 * @author rafae
 */

/*
    This class has a WRITE DB method which involves reading prize values from a file
    and storing it in a table to be used in Cases.java
*/
public class DBInitCases 
{ 
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    public void dbInitCases()
    {
        try
        {
            // Read the file to get the prize values
            FileReader fr = new FileReader("./resources/UIresources/prizes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            try
            {
                // Establish connection and create statement
                Connection conn = DriverManager.getConnection(dbURL);
                Statement stmt = conn.createStatement();
                
                // If table exists, drop it
                DatabaseMetaData dbMeta = conn.getMetaData();
                ResultSet rs = dbMeta.getTables(null, null, "PRIZES", null);
                if(rs.next())
                {
                    stmt.executeUpdate("DROP TABLE Prizes");
                }
                // Then create table Prizes
                stmt.executeUpdate("CREATE TABLE Prizes"
                    + " (MONEY DOUBLE)");
                
                // Insert the values and update the table
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
                
//                // Test
//                rs = stmt.executeQuery("SELECT * FROM Prizes");
//                while(rs.next())
//                {
//                    System.out.println("Value: " + rs.getDouble("MONEY"));
//                }
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
//    public static void main(String[] args) 
//    {
//        dbInitCases();
//    }
}
