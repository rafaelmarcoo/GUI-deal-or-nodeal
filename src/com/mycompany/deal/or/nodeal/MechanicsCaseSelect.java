/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rafae
 */

/*
  This class handles the logic for selecting a case 
  in the game. It extends MechanicsControl as it contains all info required in one game and 
  implements the ICaseSelect interface
*/
public class MechanicsCaseSelect extends MechanicsControl implements ICaseSelect
{    
    // Instances of UI classes for displaying cases, getting input, and showing messages
    GameCaseDisplayUI caseUI = new GameCaseDisplayUI();
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    
    // Instances for logging game activities and errors
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();
    
    /*
      Handles the player's case selection process. The player is prompted to choose a case. 
      The method validates the input, handles quitting, and logs the selected case or any errors.
    */ 
    @Override
    public void selectCase(Cases cases) 
    {
        // Display instructions and the available cases
        System.out.println("\n************************ Pick your case! ************************\n");
        System.out.println("Enter 'x' to quit anytime!");
        caseUI.showCases(cases);
        
        // Loop until a valid case is selected or the user decides to quit
        while(true)
        {
            System.out.print("Please select a case to keep for the game! => ");
            String input = inputUI.getInput();
            
            if(input.equalsIgnoreCase("x"))
            {
                // Handle when player decides to quit
                folog.FileOutLog(Player.firstName, Player.lastName, "User quit game.\n\n");
                messageUI.displayExitMessage();
                System.exit(0);
            }
            
            try
            {
                int caseNum = Integer.parseInt(input);
                if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
                {
                    // Handle and log invalid (non-numeric) input
                    foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid case number! - MCaseSelect");
                    System.out.println("Invalid case number! Please try again!\n");
                }
                else
                {
                    // Store the selected case and its value, then remove it from the available cases
                    playerCase = caseNum;
                    playerCaseValue = cases.getCases().get(caseNum);
                    cases.getCases().remove(caseNum);
                    System.out.println("You have selected Case " + playerCase + "!");
                    System.out.println();
                    
                    // Log the selection of the case
                    folog.FileOutLog(Player.firstName, Player.lastName, "Selected case " + playerCase + " for the first time.");
                    
                    break;
                }
            }
            catch(NumberFormatException E)
            {
                // Handle and log invalid (non-numeric) input
                foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid input! - MCaseSelect");
                System.out.println("Invalid input! Only case numbers!\n");
            }
        }
    }
}
