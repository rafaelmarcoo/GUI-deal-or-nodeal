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
public class DBInitGoodCommentsTest 
{
    private DBInitGoodComments db;
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    @Before
    public void setUp() 
    {
        db = new DBInitGoodComments();
        db.dBInitComments();
    }
    
    @After
    public void tearDown() 
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement(); 
            
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

    /**
     * Test of dBInitComments method, of class DBInitGoodComments.
     */
    @Test
    public void testDBInitComments() 
    {
        // Check if the table was created and populated correctly
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM GoodCommentsTable");
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

    /**
     * Test of comment method, of class DBInitGoodComments.
     */
    @Test
    public void testComment() 
    {
        String comment = db.comment();
        assertNotNull("Comment should not be null", comment);
        assertFalse("Comment should not be empty", comment.trim().isEmpty());
    }
    
}
