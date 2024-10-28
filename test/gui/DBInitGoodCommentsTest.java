/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import java.sql.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafae
 */

/*
    Unit Test for DBInitGoodComments, testing database table initialization for good comments
    and checks/verifies data.
*/
public class DBInitGoodCommentsTest 
{
    // Instance of DBInitGoodComments
    private DBInitGoodComments db;
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    // Set up before test
    @Before
    public void setUp() 
    {
        db = new DBInitGoodComments();
        db.dBInitComments();
    }
    
    // Tear down after test
    // Drop tables used/created during test.
    @After
    public void tearDown() 
    {
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement(); 
            
            // If table exists, then drop it
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "GOODCOMMENTSTABLE", null);
            if(rs.next())
            {
                statement.executeUpdate("DROP TABLE GoodCommentsTable");
            }
            
            conn.close();
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    @Test
    public void testDBInitComments() 
    {
        // Check if the table was created and populated correctly
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM GoodCommentsTable");
            
            // Assert true that the table has entries
            assertTrue(rs.next());
            int count = rs.getInt("count");
            assertTrue(count > 0); // Check that the count is greater than zero
            
            conn.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testComment() 
    {
        // Retrieve a comment;
        String comment = db.comment();
        
        // Assert
        assertNotNull("Comment should not be null", comment); // Should not be null
        assertFalse("Comment should not be empty", comment.trim().isEmpty()); // Should not be empty
    }
    
}
