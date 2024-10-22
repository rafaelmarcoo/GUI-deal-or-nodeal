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
public class MechanicsPlayRound extends MechanicsControl
{
    MechanicsOffer MOffer = new MechanicsOffer();
    
    UIMessages uiMessages = new UIMessages();
    
    // DB - Comments
    DBInitBadComments dbBadCom = new DBInitBadComments();
    DBInitGoodComments dbGoodCom = new DBInitGoodComments();
    
    public void playRound(FrameMainGame frame, JTextField jTextField1) 
    {        
        try
        {
            int caseNum = Integer.parseInt(jTextField1.getText());
            
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
            }
            else if(!cases.getCases().containsKey(caseNum))
            {
                JOptionPane.showMessageDialog(frame, "Case has already been opened! Pick another one!");
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
        }
    }
}
