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
    This class handles the logic for selecting a game case in the Deal or No Deal game. 
    It implements the ICaseSelect interface and extends MechanicsControl.
*/
public class MechanicsCaseSelect extends MechanicsControl implements ICaseSelect
{
    // DB - Instances to log game and errors
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    
    @Override
    public void selectCase(FrameMainGame frame, JTextField jTextField1)
    {
        // Clean user input
        String strNum = jTextField1.getText().trim();
        try
        {
            int caseNum = Integer.parseInt(strNum); // Parse into an integer
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length) // Check if the case number is valid
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                jTextField1.setText("");
                
                // DB Error Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid case number! - MCaseSelect");
            }
            else
            {
                // Store the selected case and its value
                playerCase = caseNum;
                playerCaseValue = cases.getCases().get(caseNum);
                cases.getCases().remove(caseNum); // Remove selected case from available cases
                roundNum++; // Increment the round number from 0 to 1 (normal gameplay)
                jTextField1.setText("");
                JOptionPane.showMessageDialog(frame, "You have chosen case " + playerCase + "!");
                
                // DB Log
                dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Selected case " + playerCase + " for the first time.");         
                dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Start of Round " + roundNum);
                
                // Refresh UI
                frame.refreshUI(); 
            }
        }
        catch(NumberFormatException E) // Invalid input - letters and such
        {
            JOptionPane.showMessageDialog(frame, "Invalid input! Only case numbers!");
            jTextField1.setText("");
            
            // DB Error Log
            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! - MCaseSelect");
        }
        
    }
}
