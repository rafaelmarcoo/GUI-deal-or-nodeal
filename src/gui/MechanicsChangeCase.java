/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author rafae
 */

/*
    This class handles the logic for selecting a game case in the Deal or No Deal game. 
    It implements the ICaseSelect interface and extends MechanicsControl.
*/
public class MechanicsChangeCase extends MechanicsControl implements IChangeCase
{
    // UI Instance
    UIMessages uiMessages = new UIMessages();
    
    // DB - Instances to log game and errors
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    
    @Override
    public void changeCase(FrameMainGame frame, Cases cases)
    {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Having mixed feelings now? You can change your case!\n Enter a case number:");
        JTextField textField = new JTextField(10);
        panel.add(label);
        panel.add(textField);
        
        boolean done = false; // Track if user has made a choice
                
        // Button options
        Object[] options = {"Swap", "Or Pass", "Quit"};
        
        // Loop until player makes a valid choice
        while(!done)
        {
            int option = JOptionPane.showOptionDialog
            (
                frame,
                panel,
                "Opportunity",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]
            );

            // Handle decisions
            switch(option)
            {
                // Swap case option
                case 0:
                    String strNum = textField.getText().trim(); // Clean input
                    try
                    {
                        int caseNum = Integer.parseInt(strNum); // Parse into an integer
                        if(caseNum <= 0 || caseNum > cases.getCaseNums().length) // Validate case number
                        {
                            JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                            textField.setText("");
                            
                            // DB Log
                            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid case number! - ChangeCase");
                        }
                        else if(!cases.getCases().containsKey(caseNum)) // Check if case has already been opened
                        {
                            JOptionPane.showMessageDialog(frame, "Case has already been opened! Pick another one!");
                            textField.setText("");
                            
                            // DB Log
                            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Case has already been opened! - ChangeCase");
                        }
                        else
                        {
                            // Temporarily store the current case and value
                            int temp = playerCase;
                            double tempValue = playerCaseValue;

                            // Add the previous case back to available cases
                            cases.getCases().put(temp, tempValue);

                            // Update the player with the new case and value, and remove it from the available cases
                            playerCase = caseNum;
                            playerCaseValue = cases.getCases().get(caseNum);
                            cases.getCases().remove(caseNum);
                            
                            JOptionPane.showMessageDialog(frame, "You swapped your case " + temp + " with case " + playerCase + "!");
                            JOptionPane.showMessageDialog(frame, "Onto the next round!");
                            
                            // DB Log
                            dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Swapped case " + temp + " with case " + caseNum);
                            
                            frame.refreshUI();
                            done = true;
                        }
                    }
                    catch(NumberFormatException E) // Invalid input - letters and such
                    {
                        JOptionPane.showMessageDialog(frame, "Invalid input!");
                        textField.setText("");
                        
                        // DB Log
                        dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! Only case numbers! - MChangeCase");
                    }             
                    break;
                    
                case 1: // Pass option
                    JOptionPane.showMessageDialog(frame, "No Swap! We move on to the next round then!");
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Player refused swapping cases.");
                    
                    done = true;
                    break;
                    
                case 2: // Quit option
                    // Display quit dialog
                    uiMessages.quitMessage(frame);
                    
                    break;
            }
        }   
    }
}
