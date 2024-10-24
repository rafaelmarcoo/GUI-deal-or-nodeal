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
                    System.out.println("Deal");
                    JOptionPane.showMessageDialog(frame, "Deal!");
                    JOptionPane.showMessageDialog(frame, "Congratulations! You will take home $" + offer +
                            "!\n" + "Your case " + playerCase + " contains $" + playerCaseValue);
                    
                    // DB Log
                    dbWin.dbListWin(Player.firstName, Player.lastName, offer);
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "Accepted banker's offer of $" + offer);
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
                    dbELog.dbErrorLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
                    
                    frame.dispose();
                    FrameHome homeFrame = new FrameHome();
                    homeFrame.setVisible(true);
                    break;
                    
                case 1:
                    done = true;
                    System.out.println("No Deal");
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "Rejected banker's offer of $" + offer);
                    
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
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "End of Round " + roundNum);
                    
                    roundNum++;
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "Start of Round " + roundNum);
                    
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
                    uiMessages.quitMessage(frame);
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.firstName, Player.lastName, "User quit game.");
                    
                    break;
            }
        }
    }
}
