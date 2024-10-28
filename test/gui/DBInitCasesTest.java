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
    Unit Test for DBInitCases, testing database table initialization for cases 
    and checking the existence of prize entries
*/
public class DBInitCasesTest 
{
    // Instance of DBInitCases
    private DBInitCases db;
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    // Set up before test
    @Before
    public void setUp() 
    {
        db = new DBInitCases();
        db.dbInitCases();
    }
    
    // Tear down after test
    // To drop any existing tables that were used/created during testing
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
            ResultSet rs = dbMeta.getTables(null, null, "PRIZES", null);
            if(rs.next())
            {
                statement.executeUpdate("DROP TABLE Prizes");
            }
            
            conn.close(); // Close connection
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    @Test
    public void testDbInitCases() 
    {
        // Check if prizes were populated properly
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM Prizes");
            
            // Assert that entries exist
            assertTrue(rs.next());
            int count = rs.getInt("count");
            assertTrue("Prize count should be > 0", count > 0);
            
            conn.close();
        }
        catch(SQLException E)
        {
            E.printStackTrace();
        }
    }
}
