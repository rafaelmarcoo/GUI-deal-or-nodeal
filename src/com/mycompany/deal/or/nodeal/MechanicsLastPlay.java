/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.util.Iterator;

/**
 *
 * @author rcman
 */

/*
 This class handles the final round of the game, where the player
 can decide whether to keep their current case or swap it with the last remaining case on display.
 It also overrides the playRound method for playing standard rounds, and it implements 
 the ILastPlay and IPlayRound interfaces.
*/
public class MechanicsLastPlay extends MechanicsControl implements ILastPlay, IPlayRound
{
    // UI components for input and displaying various game messages
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    GameCaseDisplayUI caseUI = new GameCaseDisplayUI();
    GameGoodCommentUI goodUI = new GameGoodCommentUI();
    GameBadCommentUI badUI = new GameBadCommentUI();
    GameCompareLastUI compareUI = new GameCompareLastUI();
    
    // Components for logging and storing game data
    FileOutListOfWin folist = new FileOutListOfWin();
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();

    // Variables to store the number and value of the other (last) case
    int otherCaseNum;
    double otherCaseVal;
    
    @Override
    public void lastPlay(Cases cases)
    {
        System.out.println("-- This is the last play of the game! --");
        System.out.println("You can either keep your case! Or \nswap it with the"
                + " last case on display");
        
        // Display the remaining cases to the player
        caseUI.showCases(cases);
        
        // Loop until the player makes a valid choice (keep or swap)
        while(true)
        {
            System.out.print("Keep your case or swap it? ('k' or 's') => ");
            String response = inputUI.getInput();
            
            // Player chooses to keep their case
            if(response.equalsIgnoreCase("k"))
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "Chose to keep case " + playerCase);
                
                // Get the last remaining case number and value using an Iterator
                Iterator<Integer> caseIterator = cases.getCases().keySet().iterator();
                if(caseIterator.hasNext())
                {
                    otherCaseNum = caseIterator.next();
                }
                otherCaseVal = cases.getCases().get(otherCaseNum);
                
                // Display the final values and compare them
                System.out.println("\nYour case " + playerCase + " contains $" + playerCaseValue);
                System.out.println("\nThe other case " + otherCaseNum + " contains $" + otherCaseVal + "\n");
                compareUI.compareValues(playerCaseValue, otherCaseVal);
                
                // Log the win and update the list of winners
                folist.FileOutListWin(Player.firstName, Player.lastName, playerCaseValue);
                folog.FileOutLog(Player.firstName, Player.lastName, "Won $" + playerCaseValue);
                
                System.out.println();
                break;
            }
            // Player chooses to swap their case
            else if(response.equalsIgnoreCase("s"))
            {   
                // Get the last remaining case number and value using an Iterator
                Iterator<Integer> caseIterator = cases.getCases().keySet().iterator();
                if(caseIterator.hasNext())
                {
                    otherCaseNum = caseIterator.next();
                }
                otherCaseVal = cases.getCases().get(otherCaseNum);
                
                folog.FileOutLog(Player.firstName, Player.lastName, "Chose to swap case " + playerCase + 
                        " with case " + otherCaseNum);
                
                // Display the results of the swap
                System.out.println("\nYou swapped your case " + playerCase + " for case " + otherCaseNum);
                System.out.println("\nYour new case " + otherCaseNum + " contains $ " + otherCaseVal + "\n");
                System.out.println("Your old case " + playerCase + " contains $ " + playerCaseValue + "\n");
                compareUI.compareValues(otherCaseVal, playerCaseValue);
                
                // Log the win and update the list of winners
                folist.FileOutListWin(Player.firstName, Player.lastName, otherCaseVal);
                folog.FileOutLog(Player.firstName, Player.lastName, "Won $" + otherCaseVal);
                
                System.out.println();
                break;
            }
            // Player chooses to quit the game
            else if(response.equalsIgnoreCase("x"))
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "User quit game.\n\n");
                messageUI.displayExitMessage();
                System.exit(0);
            }
            // Invalid input handling
            else
            {
                foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid input - MLastPlay");
                System.out.println("Invalid!\n");
            }
        }
        
        
    }
    
    // Override playRound for MechanicsLastPlay use
    @Override
    public void playRound(Cases cases, int roundNum)
    {
        int count = 0;
        int casesToPick = 4; // Only 4 cases of the last round
        
        System.out.println("************************ Round " + roundNum + "! ************************");
        System.out.println("\nEnter 'x' to quit anytime!");
        
        folog.FileOutLog(Player.firstName, Player.lastName, "Start of Round " + roundNum);

        while(count < 4)
        {
            count++;
            caseUI.showCases(cases);
            
            while(true)
            {
                System.out.print("Please pick a case! (" + casesToPick +
                    " more case(s) to pick.) =>  ");
                String input = inputUI.getInput();
                
                if(input.equalsIgnoreCase("x"))
                {
                    folog.FileOutLog(Player.firstName, Player.lastName, "User quit game\n\n");
                    messageUI.displayExitMessage();
                    System.exit(0);
                }
                
                try
                {
                    int caseNum = Integer.parseInt(input);
                    
                    if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
                    {
                        foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid Case Number - MLastPlay - MPlayRound");
                        System.out.println("Invalid case number! Please try again!\n");
                    }
                    else if(!cases.getCases().containsKey(caseNum))
                    {
                        foerror.FileOutLog(Player.firstName, Player.lastName, "Case already opened - MLastPlay - MPlayRound");
                        System.out.println("Case has already been opened! Pick another one!\n");
                    }
                    else
                    {
                        System.out.println("-------- Case " + caseNum
                        + " contains: $" + cases.getCases().get(caseNum) + " --------");
                        
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

                        cases.getCases().remove(caseNum);

                        System.out.println("");
                        casesToPick--;
                        
                        break;
                    }
                }
                catch(NumberFormatException E)
                {
                    foerror.FileOutLog(Player.firstName, Player.lastName, "Invalid input - MLastPlay - MPlayRound");
                    System.out.println(E + ". Invalid input! Only case numbers!\n");
                }
            }
            folog.FileOutLog(Player.firstName, Player.lastName, "End of Round " + roundNum);
        }
    }
}
