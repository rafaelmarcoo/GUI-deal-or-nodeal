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
public class MechanicsLastPlayTest 
{
    private MechanicsLastPlay MLastPlay;
    private FrameMainGame frame;
    private Cases cases;
    
    public MechanicsLastPlayTest() 
    {
        MLastPlay = new MechanicsLastPlay();
        frame = new FrameMainGame();
        cases = new Cases();
        MechanicsLastPlay.playerCase = 1;
        MechanicsLastPlay.playerCaseValue = cases.getCases().get(MechanicsLastPlay.playerCase);
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
     * Test of lastPlay method, of class MechanicsLastPlay.
     */
    @Test
    public void testLastPlay_KeepCase() 
    {
        // Assume player chooses to keep the case

        // Simulate the "Keep your case" action
        MLastPlay.lastPlay(frame, cases);
        
        // Validate that the player's case value remains the same
        assertEquals("Player should keep the initial case value", 
                     cases.getCases().get(MechanicsLastPlay.playerCase), MechanicsLastPlay.playerCaseValue, 0.01);
    }
    
    @Test
    public void testLastPlay_SwapCase() 
    {
        // Assume player chooses to swap the case
        int otherCase = 2; // Set a different case number manually
        MLastPlay.otherCaseNum = otherCase;
        MLastPlay.otherCaseVal = cases.getCases().get(otherCase);

        // Perform the last play action (assume it will swap)
        MLastPlay.lastPlay(frame, cases);

        // Check that the player's case value is updated to the swapped case value
        assertEquals("Player should receive the value of the swapped case",
                     MLastPlay.otherCaseVal, MLastPlay.playerCaseValue, 0.01);
    }   
}
