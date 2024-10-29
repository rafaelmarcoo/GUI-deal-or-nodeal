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
    Unit test for MechanicsPlayRound,  validating the playRound 
    method for handling correct case selection, invalid case numbers, and non integer input.
*/
public class MechanicsPlayRoundTest 
{
    // Instances required
    private MechanicsPlayRound MPlayRound;
    private JTextField jTextField;
    private FrameMainGame frame;
    
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    // Set up before test
    @Before
    public void setUp() 
    {     
        // Initialise instances
        MPlayRound = new MechanicsPlayRound();
        frame = new FrameMainGame();
        jTextField = new JTextField();
        
        // Initialise cases
        Cases cases = new Cases();
        MechanicsPlayRound.cases = cases;
        
        // Initialise test player
        Player player = new Player("TEST", "PLAYER");
        MechanicsPlayRound.player = player;
    }
    
    // Tear down
    @After
    public void tearDown() 
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();       
            
            // If tables exist, then drop it
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "TEST_PLAYER_GAMELOG", null);
            ResultSet rs2 = dbMeta.getTables(null, null, "TEST_PLAYER_ERRORLOG", null);
            if(rs.next())
                statement.executeUpdate("DROP TABLE TEST_PLAYER_GAMELOG");
            if(rs2.next())
                statement.executeUpdate("DROP TABLE TEST_PLAYER_ERRORLOG");
            
            conn.close();
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    @Test
    public void testPlayRound_Correct() 
    {
        // Set up - Valid input
        jTextField.setText("1");
        
        // Run method
        MPlayRound.playRound(frame, jTextField);
        
        // Assert 
        assertFalse(MechanicsPlayRound.cases.getCases().containsKey(1)); // False because case has been removed 
        assertEquals("", jTextField.getText()); // jTextField has been cleared
    }
    
    @Test
    public void testPlayRound_InvalidCase() 
    {
        // Set up - Invalid case number
        jTextField.setText("999");
        int initialSize = MechanicsPlayRound.cases.getCases().size();
        
        // Run method
        MPlayRound.playRound(frame, jTextField);
        
        // Assert that the size of HashMap has not changed, meaning no case was removed
        assertEquals("No cases should be removed for an invalid selection", initialSize, MechanicsPlayRound.cases.getCases().size());
    }

    @Test
    public void testPlayRound_NonIntegerInput() 
    {
        // Set up - non-integer text
        jTextField.setText("invalid");
        
        // Call playRound method
        MPlayRound.playRound(frame, jTextField);
        
        // Assert the text field was reset, indicating error was handled
        assertEquals("", jTextField.getText());
    }
    
}
