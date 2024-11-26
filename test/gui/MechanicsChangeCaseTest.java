/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafae
 */
public class MechanicsChangeCaseTest 
{
    private MechanicsChangeCase MChangeCase;
    private FrameMainGame frame;
    private Cases cases;
    
    public MechanicsChangeCaseTest() 
    {
        MChangeCase = new MechanicsChangeCase();
        frame = new FrameMainGame(); // You may need to adjust how you create the frame for your testing
        cases = new Cases();
        
        Player player = new Player("TEST", "PLAYER");
        MechanicsPlayRound.player = player;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
            if(rs.next())
                statement.executeUpdate("DROP TABLE TEST_PLAYER_GAMELOG");
            
            conn.close();
        }
        catch(SQLException E) 
        {
            E.printStackTrace();
        }
    }

    /**
     * Test of changeCase method, of class MechanicsChangeCase.
     */
    @Test
    public void testChangeCase_ValidCaseNumber() 
    {
        // Assuming the player starts with case number 1
        MChangeCase.playerCase = 1; 
        MChangeCase.playerCaseValue = cases.getCases().get(1); // Get value of case 1
        
        // Call the changeCase method with valid case number
        MChangeCase.changeCase(frame, cases);
        
        // Validate that the player case has changed
        assertNotEquals(1, MChangeCase.playerCase);
    }
    
    @Test
    public void testChangeCase_CaseAlreadyOpened() 
    {
        // Let's say the player starts with case number 1
        MChangeCase.playerCase = 1; 
        MChangeCase.playerCaseValue = cases.getCases().get(1);
        
        // Simulating the case number 1 has already been opened
        cases.getCases().remove(1); // Remove case 1
        
        // Call changeCase method to test case already opened scenario
        MChangeCase.changeCase(frame, cases);
        
        // Validate that the player case hasn't changed
        assertEquals(1, MChangeCase.playerCase);
    }
}
