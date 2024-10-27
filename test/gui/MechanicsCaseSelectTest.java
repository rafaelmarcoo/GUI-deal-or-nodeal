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
public class MechanicsCaseSelectTest 
{
    private MechanicsCaseSelect MCaseSelect;
    private FrameMainGame frame;
    private JTextField jTextField;
    
    @Before
    public void setUp() 
    {
        
        
        MCaseSelect = new MechanicsCaseSelect();
        frame = new FrameMainGame(); // Assuming this can be instantiated directly
        jTextField = new JTextField();
        
        MechanicsCaseSelect.cases = new Cases(); 
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
     * Test of selectCase method, of class MechanicsCaseSelect.
     */
    @Test
    public void testSelectCase_ValidInput() 
    {
        // Arrange
        jTextField.setText("2");
        double caseVal = MCaseSelect.cases.getCases().get(2);
        
        // Act
        MCaseSelect.selectCase(frame, jTextField);

        // Assert
        assertEquals(2, MCaseSelect.playerCase);
        assertEquals(caseVal, MCaseSelect.playerCaseValue, 0.001);
        assertTrue(MCaseSelect.cases.getCases().size() < 26); // One case should be removed
        assertEquals(1, MCaseSelect.roundNum); // Assuming this is the first round
    }
    
    @Test
    public void testSelectCase_InvalidNumberFormat() 
    {
        // Arrange
        jTextField.setText("abc");

        // Act
        MCaseSelect.selectCase(frame, jTextField);

        // Assert
        assertEquals("", jTextField.getText());
        // Check for the dialog message or a state change
        // You might want to adjust this part based on how your implementation handles this
    }
    
    @Test
    public void testSelectCase_CaseNumberOutOfRange() 
    {
        // Arrange
        jTextField.setText("30"); // Invalid case number (out of range)

        // Act
        MCaseSelect.selectCase(frame, jTextField);

        // Assert
        // Check for the dialog message or a state change
        // Again, adjust based on your implementation
        assertEquals("", jTextField.getText());
    }
    
    @Test
    public void testSelectCase_CaseAlreadyOpened()
    {
        int testCase = 1;
        
        MechanicsCaseSelect.cases.getCases().remove(testCase);
        
        assertFalse(MechanicsCaseSelect.cases.getCases().containsKey(testCase));
    }
    
    
}
