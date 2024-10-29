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
    This class handles the logic for each round in the Deal or No Deal game,
    including validating case selection, displaying results, and prompting the banker offer.
    Implements IPlayRound and extends MechanicsControl for game setup.
*/
public class MechanicsPlayRound extends MechanicsControl implements IPlayRound
{
    // Mechanics Component
    MechanicsBanker MOffer = new MechanicsBanker();
    
    // UI Messages
    UIMessages uiMessages = new UIMessages();
    
    // DB - Instances to log game and errors
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    
    // DB - Comments
    DBInitBadComments dbBadCom = new DBInitBadComments();
    DBInitGoodComments dbGoodCom = new DBInitGoodComments();
    
    // Initialise comments to be used in this class
    public MechanicsPlayRound()
    {
        dbBadCom.dBInitComments();
        dbGoodCom.dBInitComments();
    }
    
    @Override
    public void playRound(FrameMainGame frame, JTextField jTextField1) 
    {        
        String strNum = jTextField1.getText().trim(); // Clean user input
        try
        {
            int caseNum = Integer.parseInt(strNum); // Parse into an integer
            
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length) // If case number out of range
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                jTextField1.setText("");
                
                // DB Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid Case Number - MPlayRound");
            }
            else if(!cases.getCases().containsKey(caseNum)) // If case has already been opened
            {
                JOptionPane.showMessageDialog(frame, "Case has already been opened! Pick another one!");
                jTextField1.setText("");
                
                // DB Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Case already opened - MPlayRound");
            }
            else // Else open case
            {
                String comment;
                if(cases.getCases().get(caseNum) < 50000.00) // comment based off the case value
                {
                    comment = dbGoodCom.comment();
                }
                else
                {
                    comment = dbBadCom.comment();
                }
                
                JOptionPane.showMessageDialog(frame, "Case " + caseNum + " contains: $" 
                        + cases.getCases().get(caseNum) + "\n" + comment);
                
                // DB Log
                dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Opened case " + caseNum +
                                " containing $" + cases.getCases().get(caseNum));
                
                // Remove the case from the pool and reset jTextField
                cases.getCases().remove(caseNum);
                count--;
                jTextField1.setText("");
                frame.refreshUI();
                
                // If 5 cases has been opened, make banker offer
                if(count == 0)
                    MOffer.bankerOffer(frame, cases);
            }
        }
        catch(NumberFormatException E) // Invalid input like letters and such
        {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
            jTextField1.setText("");
            
            // DB Log
            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! Only case numbers! - MPlayRound");
        }
    }
}
