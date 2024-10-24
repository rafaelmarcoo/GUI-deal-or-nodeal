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
public class MechanicsPlayRound extends MechanicsControl implements IPlayRound
{
    MechanicsBanker MOffer = new MechanicsBanker();
    
    UIMessages uiMessages = new UIMessages();
    
    // DB - Instances to log game and errors
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    
    // DB - Comments
    DBInitBadComments dbBadCom = new DBInitBadComments();
    DBInitGoodComments dbGoodCom = new DBInitGoodComments();
    
    @Override
    public void playRound(FrameMainGame frame, JTextField jTextField1) 
    {        
        String strNum = jTextField1.getText().trim();
        try
        {
            int caseNum = Integer.parseInt(strNum);
            
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                
                // DB Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid Case Number - MPlayRound");
            }
            else if(!cases.getCases().containsKey(caseNum))
            {
                JOptionPane.showMessageDialog(frame, "Case has already been opened! Pick another one!");
                
                // DB Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Case already opened - MPlayRound");
            }
            else
            {
                String comment;
                if(cases.getCases().get(caseNum) < 50000.00)
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
                
                cases.getCases().remove(caseNum);
                count--;
                jTextField1.setText("");
                frame.refreshUI();
                
                if(count == 0)
                {
                    MOffer.bankerOffer(frame, cases);
                }
            }
        }
        catch(NumberFormatException E)
        {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
            
            // DB Log
            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! Only case numbers! - MPlayRound");
        }
    }
}
