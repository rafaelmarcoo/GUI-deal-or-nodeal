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

/*
    This class handles the banker's offer rounds in the Deal or No Deal game,
    allowing the player to accept, reject, or quit. Each choice updates the game
    state and logs relevant actions in the database.
    Implements IOffer and extends MechanicsControl for game setup.
*/
public class MechanicsOffer extends MechanicsControl implements IOffer
{
    // Mechanics Instances/Components
    MechanicsChangeCase MChange = new MechanicsChangeCase();
    MechanicsLastPlay MLast = new MechanicsLastPlay();
    
    // UI Instances/Components
    UICompare uiCompare = new UICompare();
    UIMessages uiMessages = new UIMessages();
    
    // DB - Instances to log game and errors and results
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    DBListOfWin dbWin = new DBListOfWin();
    
    @Override
    public void makeOffer(FrameMainGame frame, double offer)
    {
        boolean done = false; // Track if user has made a choice
        Object[] options ={ "Deal", "No Deal", "Quit" };
        
        // Loop until user has made a choice
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
                case 0: // Accept Deal
                    done = true;
                    JOptionPane.showMessageDialog(frame, "Deal!");
                    JOptionPane.showMessageDialog(frame, "Congratulations! You will take home $" + offer +
                            "!\n" + "Your case " + playerCase + " contains $" + playerCaseValue);
                    uiCompare.compareValues(frame, offer, playerCaseValue);
                    
                    // DB Log
                    dbWin.dbListWin(Player.getFirstName(), Player.getLastName(), offer);
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Accepted banker's offer of $" + offer);
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Game Finished.\n\n");
                    dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Game Finished.\n\n");
                    
                    // Go home
                    frame.dispose();
                    FrameHome homeFrame = new FrameHome();
                    homeFrame.setVisible(true);
                    break;
                    
                case 1: // Reject Deal
                    done = true;
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Rejected banker's offer of $" + offer);
                    
                    // UI messages depending on the round
                    if(roundNum != 3 || roundNum != 4 )
                    {
                        JOptionPane.showMessageDialog(frame, "No Deal! We move on to the next round!");
                    }
                    else
                    {
                       JOptionPane.showMessageDialog(frame, "No Deal!"); 
                    }
                       
                    // Option to change case during round 3 and round 4
                    if(roundNum == 3 || roundNum == 4)
                        MChange.changeCase(frame, MechanicsControl.cases);
                    
                    // DB Log
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "End of Round " + roundNum);   
                    roundNum++;
                    dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Start of Round " + roundNum);
                    
                    // Update count based on round number
                    count = (roundNum < 5) ? 5 : 4;
                    
                    // Last Play if round has gone to 6
                    if(roundNum == 6)
                        MLast.lastPlay(frame, cases);

                    frame.refreshUI();
                    break;
                    
                case 2: // Quit Game
                    uiMessages.quitMessage(frame);
                    
                    break;
            }
        }
    }
}
