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

/*
    This class handles UI messages and updates for the Deal or No Deal game.
*/
public class UIMessages extends MechanicsControl
{
    DBGameLog dbGLog = new DBGameLog();
    
    public void uiRound(JLabel jLabel)
    {
        if(roundNum == 0)
        {
            jLabel.setText("Select a case!");
        }
        else
        {
            jLabel.setText("Round " + roundNum);
        }
    }
    
    public void uiPlayerCase(JLabel jLabel)
    {
        if(roundNum != 0)
            jLabel.setText("Your Case Number is: " + playerCase);
    }
    
    public void uiCasesToOpen(JLabel jLabel, int count)
    {
        if(roundNum != 0)
            jLabel.setText(count + " more cases to open this round!");
    }
    
    public void quitMessage(FrameMainGame frame)
    {
        boolean done = false; // Track if user has made a choice
        Object[] options ={ "Quit", "No" };
        
        // Loop until user has made a choice
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
                case 0: // Quit
                    JOptionPane.showMessageDialog(frame, "Quitting! Bye Bye!");
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "User quit game.");
                    
                    System.exit(0);
                    break;
                    
                case 1: // User backs off
                    done = true;
                    break;
            }
        }
    }
}
