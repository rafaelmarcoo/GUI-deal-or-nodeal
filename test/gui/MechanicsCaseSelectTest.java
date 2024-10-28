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
    Unit tests for MechanicsCaseSelect. Tests include selecting valid and invalid cases 
    and checking the behavior when a case is already opened or out of range.
*/
public class MechanicsCaseSelectTest 
{
    // Instances for tests
    private MechanicsCaseSelect MCaseSelect;
    private FrameMainGame frame;
    private JTextField jTextField;
    
    // DB URL
    private static final String URL = "jdbc:derby:dealornodealDB;create=true";
    
    // Set up before test
    @Before
    public void setUp() 
    {
        MCaseSelect = new MechanicsCaseSelect();
        frame = new FrameMainGame();
        jTextField = new JTextField();
        
        // Initialise cases and test player
        MechanicsCaseSelect.cases = new Cases(); 
        Player player = new Player("TEST", "PLAYER");
        MechanicsPlayRound.player = player;
    }
    
    // Tear down after test
    @After
    public void tearDown() 
    {
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(URL);
            Statement statement = conn.createStatement();       
            
            // If tables exist then drop it
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
    public void testSelectCase_ValidInput() 
    {
        // Set up
        jTextField.setText("2");
        double caseVal = MCaseSelect.cases.getCases().get(2);
        
        // Run the method
        MCaseSelect.selectCase(frame, jTextField);
        
        // Assert that the case has been selected and chosen and the case has been removed from the pool
        assertEquals(2, MCaseSelect.playerCase);
        assertEquals(caseVal, MCaseSelect.playerCaseValue, 0.001);
        assertTrue(MCaseSelect.cases.getCases().size() < 26); // One case should be removed
        assertEquals(1, MCaseSelect.roundNum); // Assuming this is the first round
    }
    
    @Test
    public void testSelectCase_InvalidNumberFormat() 
    {
        // Set up
        jTextField.setText("abc"); // No letters allowed

        // Run method
        MCaseSelect.selectCase(frame, jTextField);

        // Assert the jTextField has been cleared after an invalid input
        assertEquals("", jTextField.getText());
    }
    
    @Test
    public void testSelectCase_CaseNumberOutOfRange() 
    {
        // Set up
        jTextField.setText("30"); // Out of Range

        // Run Method
        MCaseSelect.selectCase(frame, jTextField);

        // Assert the jTextField has been cleared after an invalid input
        assertEquals("", jTextField.getText());
    } 
}
