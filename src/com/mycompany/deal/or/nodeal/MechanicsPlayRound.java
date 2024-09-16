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
 This class handles the logic of the standard rounds in the game, where the player
 is required to pick and open a specified number of cases. It provides feedback based on the values revealed.
 It also overrides the playRound method for playing standard rounds, and it implements 
 the ILastPlay and IPlayRound interfaces.
*/
public class MechanicsPlayRound extends MechanicsControl implements IPlayRound
{
    // UI components for input and displaying various game messages
    GameCaseDisplayUI caseUI = new GameCaseDisplayUI();
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    GameGoodCommentUI goodUI = new GameGoodCommentUI();
    GameBadCommentUI badUI = new GameBadCommentUI();
    
    // Components for logging and storing game data
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();
    
    @Override
    public void playRound(Cases cases, int roundNum) 
    {
        int count = 0;
        int casesToPick = 5;
        
        System.out.println("************************ Round " + roundNum + "! ************************");
        System.out.println("\nEnter 'x' to quit anytime!");
        
        // Log the start of the round.
        folog.FileOutLog(Player.firstName, Player.lastName, "Start of Round " + roundNum);
        
        // Loop until the player has picked the required number of cases
        while(count < 5)
        {
            count++;
            caseUI.showCases(cases);
            
            while(true)
            {
                // Get the player's input.
                System.out.print("Please pick a case! (" + casesToPick +
                    " more case(s) to pick.) =>  ");
                String input = inputUI.getInput();
                
                // If the player enters 'x', log the quit action and exit the game.
                if(input.equalsIgnoreCase("x"))
                {
                    folog.FileOutLog(Player.firstName, Player.lastName, "User quit game\n\n");
                    messageUI.displayExitMessage();
                    System.exit(0);
                }
                
                try
                {
                    int caseNum = Integer.parseInt(input);
                    
                    // Validate the case number to ensure it's within the allowed range.
                    if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
                    {
                        foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid Case Number - MPlayRound");
                        System.out.println("Invalid case number! Please try again!\n");
                    }
                    // Check if the case has already been opened.
                    else if(!cases.getCases().containsKey(caseNum))
                    {
                        foerror.FileOutLog(Player.firstName, Player.lastName, "Case already opened - MPlayRound");
                        System.out.println("Case has already been opened! Pick another one!\n");
                    }
                    else
                    {
                        // Display the value in the selected case.
                        System.out.println("-------- Case " + caseNum
                        + " contains: $" + cases.getCases().get(caseNum) + " --------");
                        
                        // Provide comments based on the value in the case.
                        if(cases.getCases().get(caseNum) < 50000.00)
                        {
                            String comment = goodUI.comment();
                            System.out.println(comment);
                        }
                        else
                        {
                            String comment = badUI.comment();
                            System.out.println(comment);
                        }
                        
                        // Log the case selection and its contents.
                        folog.FileOutLog(Player.firstName, Player.lastName, "Opened case " + caseNum +
                                " containing $" + cases.getCases().get(caseNum));

                        // Remove the opened case from the available cases.
                        cases.getCases().remove(caseNum);

                        System.out.println("");
                        casesToPick--;
                        
                        break;
                    }
                }
                catch(NumberFormatException E)
                {
                    // Handle invalid input (non-numeric) and log the error.
                    foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid input! Only case numbers!");
                    System.out.println("Invalid input! Only case numbers!\n");
                }
            }
        }
        // Log the end of the round.
        folog.FileOutLog(Player.firstName, Player.lastName, "End of Round " + roundNum);
    }
}
