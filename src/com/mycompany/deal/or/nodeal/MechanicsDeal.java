/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rcman
 */

/*
 This class handles the player's decision to either accept 
 or reject the banker's offer at the end of every round. 
 It extends the MechanicsControl class 
 and implements the IDeal interface.
*/
public class MechanicsDeal extends MechanicsControl implements IDeal
{
    // UI components for input, messages, and comparison
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    GameCompareUI compareUI = new GameCompareUI();
    
    // File output utilities for logging game results, game logs, and error logs
    FileOutListOfWin folist = new FileOutListOfWin();
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();
    
    @Override
    public void dealornodeal(double offer)
    {
        while(true)
        {
            // Prompt the player to choose 'Deal' or 'No Deal'
            System.out.print("Deal? Or No Deal ? ('d' or 'n') => ");
            String response = inputUI.getInput();
            
            if(response.equalsIgnoreCase("d"))
            {
                System.out.println("\nCongratulations! You will take home $" + offer + "!");
                System.out.println("Your case " + playerCase + " contains $" + playerCaseValue);
                System.out.println();
                
                // Compare the offer with the player's case value
                compareUI.compareValues(offer, playerCaseValue);
                
                // Log the player's win and the game's completion
                folist.FileOutListWin(Player.firstName, Player.lastName, offer);
                folog.FileOutLog(Player.firstName, Player.lastName, "Accepted banker's offer of $" + offer);
                folog.FileOutLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
                foerror.FileOutLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
                
                // Display exit message and end the game
                messageUI.displayExitMessage();
                System.exit(0);
            }
            else if(response.equalsIgnoreCase("n")) // Player rejects the offer ('n')
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "Rejected banker's offer of $" + offer);
                System.out.println();
                break;
            }
            else if(response.equalsIgnoreCase("x")) // Player chooses to quit the game ('x')
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "User quit game.");
                messageUI.displayExitMessage();
                System.exit(0);
            }
            else // Invalid input handling
            {
                foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid! ('d' or 'n' only!) - MDeal");
                System.out.println("Invalid! ('d' or 'n' only!)\n");
            }
        }
        
    }
}
