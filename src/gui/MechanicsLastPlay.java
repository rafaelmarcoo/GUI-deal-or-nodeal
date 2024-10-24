/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class MechanicsLastPlay extends MechanicsControl
{
    UIMessages uiMessages = new UIMessages();
    
    // Variables to store the number and value of the other (last) case
    int otherCaseNum;
    double otherCaseVal;
    
    public void lastPlay(FrameMainGame frame, Cases cases)
    {
        JOptionPane.showMessageDialog(frame, "This is the last play of the game!");
        JOptionPane.showMessageDialog(frame, "You can either keep your case! Or \nswap it with the"
                + " last case on display");
        
        boolean done = false;
        Object[] options = {"Keep your case", "Swap cases", "Quit"};
        
        Iterator<Integer> caseIterator = cases.getCases().keySet().iterator();
        if(caseIterator.hasNext())
        {
            otherCaseNum = caseIterator.next();
        }
        otherCaseVal = cases.getCases().get(otherCaseNum);
    
        while(!done)
        {
            int option = JOptionPane.showOptionDialog
            (
                frame, 
                "Swap your case " + playerCase + " with the last case on display, case " + otherCaseNum + "?",
                "Round " + roundNum + " banker's offer",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]
            );
            
            switch(option)
            {
                case 0:
                    done = true;
                    
                    JOptionPane.showMessageDialog(frame, "Your case " + playerCase + " contains $" + playerCaseValue
                        + "\nThe other case " + otherCaseNum + " contains $" + otherCaseVal);
                    JOptionPane.showMessageDialog(frame, "Thanks for playing!");
                    
                    
                    frame.dispose();
                    FrameHome homeFrame = new FrameHome();
                    homeFrame.setVisible(true);
                    
                    break;
                    
                case 1:
                    done = true;
                    
                    JOptionPane.showMessageDialog(frame, "You swapped your case " + playerCase + " for case " + otherCaseNum);
                    JOptionPane.showMessageDialog(frame, "Your new case " + otherCaseNum + " contains $ " + otherCaseVal
                        + "\nYour old case " + playerCase + " contains $ " + playerCaseValue);
                    JOptionPane.showMessageDialog(frame, "Thanks for playing!");
                    
                    frame.dispose();
                    FrameHome home = new FrameHome();
                    home.setVisible(true);
                    
                    break;
                    
                case 2:
                    uiMessages.quitMessage(frame);
                    break;
            }
        }
    }
}
