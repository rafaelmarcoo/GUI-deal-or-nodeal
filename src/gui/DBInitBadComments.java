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
import java.util.Random;
/**
 *
 * @author rafae
 */

/*
    This class contains a WRITE AND READ database method to read comments from a file and store it in a
    table to be utilised in the game. It also has a method to retrieve a random comment
*/
public class DBInitBadComments implements IDBInitComments, ICommentUI
{
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    @Override
    public void dBInitComments()
    {
        String dbURL = "jdbc:derby:dealornodealDB;create=true";
        
        try
        {
            // Read the comments from a file
            FileReader fr = new FileReader("./resources/UIresources/badcomment.txt");
            BufferedReader br = new BufferedReader(fr);
            
            try
            {
                Connection conn = DriverManager.getConnection(dbURL); // Make databse connection
                Statement stmt = conn.createStatement();
                
                // To check if table exists, then drop it
                DatabaseMetaData dbMeta = conn.getMetaData();
                ResultSet rs = dbMeta.getTables(null, null, "BADCOMMENTSTABLE", null);
                if(rs.next())
                {
                    stmt.executeUpdate("DROP TABLE BadCommentsTable");
                }
                // Then create the table
                stmt.executeUpdate("CREATE TABLE BadCommentsTable"
                            + " (ID INT PRIMARY KEY, "
                            + "COMMENT VARCHAR(256))");
                
                // Then insertion of the comments into BadCommentsTable with an id
                String line;
                int id = 1;
                while((line = br.readLine()) != null)
                {
                    String insertQuery = "INSERT INTO BadCommentsTable"
                            + " (ID, COMMENT) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                    pstmt.setInt(1, id);
                    pstmt.setString(2, line);
                    pstmt.executeUpdate();
                    id++;
                }
                br.close();
                
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
    
    @Override
    public String comment()
    {
        // Get a random int id to be used in the query of retrieving a comment
        int numOfComments = 10;
        String comment = "";
        Random rand = new Random();
        int id = rand.nextInt(numOfComments) + 1;
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL); // Database Connection
            
            // Query to retrieve a random comment
            String query = "SELECT COMMENT FROM BadCommentsTable WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                comment = rs.getString("COMMENT");
            }
            
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        
        return comment;
    }
    
}
