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
public class MechanicsGetName 
{
    public void getName(FrameGetName frame, JTextField jTextField1, JTextField jTextField2)
    {
        // Retrieve user input
        String fName = jTextField1.getText().trim();
        String lName = jTextField2.getText().trim();
        
        // Check if inputs are empty, then give error
        if(fName.matches("^\\s*$") || lName.matches("^\\s*$"))
        {
            JOptionPane.showMessageDialog(frame, "One or more inputs are empty!");
        }
        else if(!fName.matches("^[a-zA-Z]+$") || !lName.matches("^[a-zA-Z]+$")) // No allowed whitespaces or special characters. Else try again
        {
            JOptionPane.showMessageDialog(frame, "No whitespaces or special characters or numbers allowed");
        }
        else
        {
            // Set fName and lName to upper case;
            fName = fName.toUpperCase();
            lName = lName.toUpperCase();
            
            // Set firstName as fName and lastName as lName in MechanicsControl.java
            Player.firstName = fName;
            Player.lastName = lName;
            
            JOptionPane.showMessageDialog(frame, "Welcome " + Player.firstName + " " + Player.lastName + "! \nGood Luck !!!");
            
            FrameMainGame gameFrame = new FrameMainGame();
            gameFrame.setVisible(true);
            JOptionPane.showMessageDialog(gameFrame, "Welcome to Deal Or No Deal!\nTo get started, pick one case to keep!");
            frame.dispose();
        }
    }
}
