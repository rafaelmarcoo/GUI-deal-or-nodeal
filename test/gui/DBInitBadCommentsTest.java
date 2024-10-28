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
    Unit Test for DBInitBadComments, testing database table initialization and
    and comment method
*/
public class DBInitBadCommentsTest 
{
    // Instance of DBInitBadComments
    private DBInitBadComments db;
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";

    // Set up before test
    @Before
    public void setUp() 
    {
        db = new DBInitBadComments();
        db.dBInitComments(); // Initialize the database and load comments
    }
    
    // Tear down after test
    // Drop test tables
    @After
    public void tearDown() 
    {
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement(); 
            
            // If table exists, drop it
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "BADCOMMENTSTABLE", null);
            if(rs.next())
            {
                statement.executeUpdate("DROP TABLE BadCommentsTable");
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
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM BadCommentsTable");
            
            // Assert that entries exist
            assertTrue(rs.next());
            int count = rs.getInt("count");
            assertTrue(count > 0); // Check that the count is greater than zero
            
            conn.close(); // Close connection
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testComment() 
    {
        // Retrieve a comment
        String comment = db.comment();
        assertNotNull("Comment should not be null", comment); // comment should not be null
        assertFalse("Comment should not be empty", comment.trim().isEmpty()); // comment should not be empty
    }
    
}
