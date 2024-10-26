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
public class MechanicsChangeCase extends MechanicsControl implements IChangeCase
{
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
        
        boolean done = false;
                
        Object[] options2 = {"Swap", "Or Pass", "Quit"};
        
        while(!done)
        {
            int option2 = JOptionPane.showOptionDialog
            (
                frame,
                panel,
                "Opportunity",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options2, options2[0]
            );

            switch(option2)
            {
                case 0:
                    String strNum = textField.getText().trim();
                    try
                    {
                        int caseNum = Integer.parseInt(strNum);
                        if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
                        {
                            JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                            textField.setText("");
                            
                            // DB Log
                            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid case number! - ChangeCase");
                        }
                        else if(!cases.getCases().containsKey(caseNum))
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
                    catch(NumberFormatException E)
                    {
                        JOptionPane.showMessageDialog(frame, "Invalid input!");
                        textField.setText("");
                        
                        // DB Log
                        dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! Only case numbers! - MChangeCase");
                    }             
                    break;
                    
                case 1:
                    JOptionPane.showMessageDialog(frame, "No Swap! We move on to the next round then!");
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Player refused swapping cases.");
                    
                    done = true;
                    break;
                    
                case 3:
                    uiMessages.quitMessage(frame);
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "User quit game.\n\n");
                    break;
            }
        }   
    }
}
