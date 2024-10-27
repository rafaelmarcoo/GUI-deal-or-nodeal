/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import static gui.MechanicsControl.roundNum;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class UIMessages extends MechanicsControl
{
    public void uiRound(JLabel jLabel)
    {
        if(roundNum == 0)
        {
            jLabel.setText("Please select one case to keep!");
        }
        else
        {
            jLabel.setText("Round " + roundNum);
        }
    }
    
    public void uiName(JLabel jLabel)
    {
        jLabel.setText("Goodluck " + Player.getFirstName() + " " + Player.getLastName() + "!");
    }
    
    public void uiPlayerCase(JLabel jLabel)
    {
        if(roundNum == 0)
        {
            jLabel.setText("Welcome! Please select a case to keep for the game!");
        }
        else
        {
            jLabel.setText("Your Case Number is: " + playerCase);
        }
    }
    
    public void uiCasesToOpen(JLabel jLabel, int count)
    {
        jLabel.setText(count + " more cases to open this round!");
    }
    
    public void quitMessage(FrameMainGame frame)
    {
        boolean done = false;
        Object[] options ={ "Quit", "No" };
        
        while(!done)
        {
            int option = JOptionPane.showOptionDialog
            (
                frame, 
                "Are you sure you want to quit?",
                "Quit?",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]
            );
            
            switch(option)
            {
                case 0:
                    done = true;
                    JOptionPane.showMessageDialog(frame, "Quitting! Bye Bye!");
                    break;
                    
                case 1:
                    done = true;
                    break;
            }
        }
    }
}
