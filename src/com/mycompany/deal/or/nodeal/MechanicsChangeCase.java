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
 This class is handles the logic for allowing a player to change 
 their selected case from the end of round 3 in the game.
 It extends MechanicsControl as it contains all info required in one game 
 and implements the IChangeCase interface
*/
public class MechanicsChangeCase extends MechanicsControl implements IChangeCase
{
    // Instances of UI classes for displaying cases, getting input, and showing messages
    GameCaseDisplayUI caseUI = new GameCaseDisplayUI();
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    
    // Instances for logging game activities and errors
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();

    @Override
    public void changeCase(Cases cases)
    {
        System.out.println("\n************************ Select a new case! ************************\n");
        System.out.println("Enter 'x' to quit the game anytime!");
        caseUI.showCases(cases);
        
        // Loop until a valid case is selected, the player backs out, or the user decides to quit
        while(true)
        {
            // Display instructions and the available cases and gets input from player
            System.out.print("Please select your new case! (enter 'b' to back out) => ");
            String input = inputUI.getInput(); 
            
            // Handle quitting the game
            if(input.equalsIgnoreCase("x"))
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "User quit game.\n\n");
                messageUI.displayExitMessage();
                System.exit(0);
            }
            
            // Handle backing out from swapping the case
            if(input.equalsIgnoreCase("b"))
            {                
                folog.FileOutLog(Player.firstName, Player.lastName, "Backed out from swapping case.");
                System.out.println();
                break;
            }
            
            try
            {
                // Parse and validate the case number input
                int caseNum = Integer.parseInt(input);
                if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
                {
                    System.out.println("Invalid case number! Please try again!\n");
                    foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid case number! - ChangeCase");
                }
                else
                {
                    // Temporarily store the current case and value
                    int temp = playerCase;
                    double tempValue = playerCaseValue;
                    
                    // Add the previous case back to available cases
                    cases.getCases().put(temp, tempValue);
                            
                    // Update the player with the new case and value, and remove it from the available cases
                    playerCase = caseNum;
                    playerCaseValue = cases.getCases().get(caseNum);
                    cases.getCases().remove(caseNum);
                    System.out.println("You have chosen Case " + playerCase + "!");
                    System.out.println();         

                    // Log the case swap action
                    folog.FileOutLog(Player.firstName, Player.lastName, "Swapped case " + temp + " with case " + caseNum);
                    
                    break;
                }
            }
            catch(NumberFormatException E)
            {
                // Handle and log invalid (non-numeric) input
                foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid input! Only case numbers! - MChangeCase");
                System.out.println("Invalid input! Only case numbers!\n");
            }
        }
    }
}
