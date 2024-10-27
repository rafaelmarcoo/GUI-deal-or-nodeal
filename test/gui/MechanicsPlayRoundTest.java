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
public class MechanicsPlayRoundTest 
{
    private MechanicsPlayRound MPlayRound;
    private JTextField jTextField;
    private FrameMainGame frame;
    
    @Before
    public void setUp() 
    {     
        MPlayRound = new MechanicsPlayRound();
        frame = new FrameMainGame();
        jTextField = new JTextField();
        
        Cases cases = new Cases();
        MechanicsPlayRound.cases = cases;
        
        Player player = new Player("TEST", "PLAYER");
        MechanicsPlayRound.player = player;
    }
    
    @After
    public void tearDown() 
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:derby:dealornodealDB;create=true");
            Statement statement = conn.createStatement();       
            
            // If tables exist then delete it
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

    /**
     * Test of playRound method, of class MechanicsPlayRound.
     */
    @Test
    public void testPlayRound_Correct() 
    {
        jTextField.setText("1");
        
        MechanicsPlayRound.cases.getCases().put(1, 20000.0);
        
        MPlayRound.playRound(frame, jTextField);
        
        assertFalse(MechanicsPlayRound.cases.getCases().containsKey(1));
        
        assertEquals("", jTextField.getText());
    }
    
     @Test
    public void testPlayRound_InvalidCase() 
    {
        
        // Add an invalid case number to the text field
        jTextField.setText("999");
        
        // Call playRound method
        MPlayRound.playRound(frame, jTextField);
        
        // Check that an invalid case number doesn't remove any cases
        assertTrue(!MechanicsPlayRound.cases.getCases().isEmpty());
    }

    @Test
    public void testPlayRound_NonIntegerInput() 
    {
        // Set non-integer text in the input field
        jTextField.setText("invalid");
        
        // Call playRound method
        MPlayRound.playRound(frame, jTextField);
        
        // Verify the text field was reset, indicating error was handled
        assertEquals("invalid", jTextField.getText());
    }
    
}
