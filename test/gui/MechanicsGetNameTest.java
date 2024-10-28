/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import java.sql.*;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafae
 */

/*
    Unit test for MechanicsGetName, validating the 
    getName method for handling empty, invalid, and valid input.
*/
public class MechanicsGetNameTest 
{
    // Instances required
    private MechanicsGetName MGetName;
    private JTextField fNameField;
    private JTextField lNameField;
    private FrameGetName frame;
    
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    // Set up before test
    @Before
    public void setUp() 
    {
        // Initialise instances
        MGetName = new MechanicsGetName();
        fNameField = new JTextField();
        lNameField = new JTextField();
        frame = new FrameGetName();
    }
    
    // Tear down after test
    // Drop tables created and used during testing
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
            ResultSet rs = dbMeta.getTables(null, null, "TEST_PLAYER_GAMELOG", null);
            if(rs.next())
                statement.executeUpdate("DROP TABLE TEST_PLAYER_GAMELOG");
            
            conn.close();
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    @Test
    public void testEmptyInput() 
    {
        // Set up - Empty inputs
        fNameField.setText("");
        lNameField.setText("");
        
        // Run method
        MGetName.getName(frame, fNameField, lNameField);
        
        // Assert that jTextField has been cleared due to invalid input
        assertEquals("Should be cleared", "", fNameField.getText());
        assertEquals("Should be cleared", "", lNameField.getText());
    }

    @Test
    public void testInvalidCharacters() 
    {
        // Set up - Invalid characters
        fNameField.setText("John3");
        lNameField.setText("Doe!");

        // Run method
        MGetName.getName(frame, fNameField, lNameField);

        // Assert that jTextField has been cleared
        assertEquals("Should be cleared", "", fNameField.getText());
        assertEquals("Should be cleared", "", lNameField.getText());
    }

    @Test
    public void testValidInput() 
    {
        // Set up - VALID Inputs
        fNameField.setText("TEST");
        lNameField.setText("PLAYER");

        // Run method
        MGetName.getName(frame, fNameField, lNameField);

        // Assert that Player first name and last name match
        assertEquals("First name should be TEST", "TEST", Player.getFirstName());
        assertEquals("Last name should be PLAYER", "PLAYER", Player.getLastName());
    }
    
}
