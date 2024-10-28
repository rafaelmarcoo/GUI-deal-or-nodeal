/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rafae
 */

/*
    This class validates and processes the player's first and last name.
    Implements IGetName and extends MechanicsControl for game setup.
*/
public class MechanicsGetName extends MechanicsControl implements IGetName
{
    // DB - Instances to log game
    DBGameLog dbGLog = new DBGameLog();
    
    @Override
    public void getName(FrameGetName frame, JTextField jTextField1, JTextField jTextField2)
    {
        // Retrieve user input
        String fName = jTextField1.getText().trim();
        String lName = jTextField2.getText().trim();
        
        // Check if inputs are empty, then give error
        if(fName.matches("^\\s*$") || lName.matches("^\\s*$"))
        {
            JOptionPane.showMessageDialog(frame, "One or more inputs are empty!");
            jTextField1.setText("");
            jTextField2.setText("");
        }
        else if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$")) // No allowed whitespaces or special characters. Else try again
        {
            JOptionPane.showMessageDialog(frame, "No whitespaces or special characters or numbers allowed");
            jTextField1.setText("");
            jTextField2.setText("");
        }
        else
        {
            // Set fName and lName to upper case;
            fName = fName.toUpperCase();
            lName = lName.toUpperCase();
            
            // Set firstName as fName and lastName as lName in MechanicsControl.java
            Player.setFirstName(fName);
            Player.setLastName(lName);
            
            // DB Log
            dbGLog.dbGameLog(fName, lName, "Entered first and last name.");
            
            JOptionPane.showMessageDialog(frame, "Welcome " + Player.getFirstName() + " " + Player.getLastName() + "! \nGood Luck !!!");
            
            // Then direct user to the main game frame
            FrameMainGame gameFrame = new FrameMainGame();
            gameFrame.setVisible(true);
            JOptionPane.showMessageDialog(gameFrame, "Welcome to Deal Or No Deal!\nTo get started, pick one case to keep!");
            frame.dispose();
        }
    }
}
