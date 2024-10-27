/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author rafae
 */
public class MechanicsOffer extends MechanicsControl implements IOffer
{
    MechanicsChangeCase MChange = new MechanicsChangeCase();
    MechanicsLastPlay MLast = new MechanicsLastPlay();
    
    UICompare uiCompare = new UICompare();
    UIMessages uiMessages = new UIMessages();
    
    // DB - Instances to log game and errors and results
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    DBListOfWin dbWin = new DBListOfWin();
    
    @Override
    public void makeOffer(FrameMainGame frame, double offer)
    {
        boolean done = false;
        Object[] options ={ "Deal", "No Deal", "Quit" };
        
        while(!done)
        {
            int option = JOptionPane.showOptionDialog
            (
                frame, 
                "Banker has offered you $" + offer + "\nDeal or No Deal?",
                "Round " + roundNum + " banker's offer",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.INFORMATION_MESSAGE, 
                null, options, options[0]
            );

            switch(option) 
            {
                case 0:
                    done = true;
                    JOptionPane.showMessageDialog(frame, "Deal!");
                    JOptionPane.showMessageDialog(frame, "Congratulations! You will take home $" + offer +
                            "!\n" + "Your case " + playerCase + " contains $" + playerCaseValue);
                    uiCompare.compareValues(frame, playerCaseValue, offer);
                    
                    // DB Log
                    dbWin.dbListWin(Player.getFirstName(), Player.getLastName(), offer);
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Accepted banker's offer of $" + offer);
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Game Finished.\n\n");
                    dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Game Finished.\n\n");
                    
                    frame.dispose();
                    FrameHome homeFrame = new FrameHome();
                    homeFrame.setVisible(true);
                    break;
                    
                case 1:
                    done = true;
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Rejected banker's offer of $" + offer);
                    
                    if(roundNum != 3 || roundNum != 4 )
                    {
                        JOptionPane.showMessageDialog(frame, "No Deal! We move on to the next round!");
                    }
                    else
                    {
                       JOptionPane.showMessageDialog(frame, "No Deal!"); 
                    }
                       
                    if(roundNum == 3 || roundNum == 4)
                    {
                        MChange.changeCase(frame, MechanicsControl.cases);
                    }
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "End of Round " + roundNum);
                    
                    roundNum++;
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Start of Round " + roundNum);
                    
                    if(roundNum < 5)
                    {
                        count = 5;
                    }
                    else
                    {
                        count = 4;
                    }
                    
                    if(roundNum == 6)
                    {
                        MLast.lastPlay(frame, cases);
                    }

                    frame.refreshUI();
                    break;
                    
                case 2:
                    done = true;
                    uiMessages.quitMessage(frame);
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "User quit game.");
                    
                    frame.dispose();
                    FrameHome home = new FrameHome();
                    home.setVisible(true);
                    
                    break;
            }
        }
    }
}
