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
public class DBInitCasesTest 
{
    private DBInitCases db;
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    @Before
    public void setUp() 
    {
        db = new DBInitCases();
        db.dbInitCases();
    }
    
    @After
    public void tearDown() 
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement(); 
            
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "PRIZES", null);
            if(rs.next())
            {
                statement.executeUpdate("DROP TABLE Prizes");
            }
            
            conn.close();
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    /**
     * Test of dbInitCases method, of class DBInitCases.
     */
    @Test
    public void testDbInitCases() 
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS count FROM Prizes");
            
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
