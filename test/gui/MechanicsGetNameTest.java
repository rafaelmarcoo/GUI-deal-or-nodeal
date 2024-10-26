/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import javax.swing.JTextField;
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
public class MechanicsGetNameTest 
{
    private MechanicsGetName MGetName;
    private JTextField fNameField;
    private JTextField lNameField;
    private FrameGetName frame;
    
    public MechanicsGetNameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {
        MGetName = new MechanicsGetName();
        fNameField = new JTextField();
        lNameField = new JTextField();
        frame = new FrameGetName();
    }
    
    @After
    public void tearDown() 
    {
        Player.setFirstName(null);
        Player.setLastName(null);
    }

    /**
     * Test of getName method, of class MechanicsGetName.
     */
     @Test
    public void testEmptyInput() 
    {
        fNameField.setText("");
        lNameField.setText("");
        
        MGetName.getName(frame, fNameField, lNameField);
        
        assertNull("First name should be null", Player.getFirstName());
        assertNull("Last name should be null", Player.getLastName());
    }

    @Test
    public void testInvalidCharacters() 
    {
        fNameField.setText("John3");
        lNameField.setText("Doe!");

        MGetName.getName(frame, fNameField, lNameField);

        assertNull("First name should be null due to invalid characters", Player.getFirstName());
        assertNull("Last name should be null due to invalid characters", Player.getLastName());
    }

    @Test
    public void testValidInput() 
    {
        fNameField.setText("John");
        lNameField.setText("Doe");

        MGetName.getName(frame, fNameField, lNameField);

        assertEquals("First name should be JOHN", "JOHN", Player.getFirstName());
        assertEquals("Last name should be DOE", "DOE", Player.getLastName());
    }
    
}
