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
 This class is responsible for managing the flow of the game from start to finish.
 It implements the IGameStart interface, which defines the startGame method.
*/

public class MechanicsStartGame extends MechanicsControl implements IGameStart
{
    // UI components for user interaction
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
    
    // Game mechanics components
    MechanicsCaseSelect Mselect = new MechanicsCaseSelect();
    MechanicsBanker Mbanker = new MechanicsBanker();
    MechanicsPlayRound Mplay = new MechanicsPlayRound();
    MechanicsChangeCase Mchange = new MechanicsChangeCase();
    MechanicsDeal Mdeal = new MechanicsDeal();
    MechanicsLastPlay Mlast = new MechanicsLastPlay();
    MechanicsGetName Mname = new MechanicsGetName();
    
    // File logging components
    FileOutGameLog folog = new FileOutGameLog();
    FileOutErrorLog foerror = new FileOutErrorLog();
       
    @Override
    public void startGame() 
    {   
        // Main game loop
        while(true)
        {
            // Display welcome message and get user response
            messageUI.displayWelcomeMessage();
            String response = inputUI.getInput();
            
            // If the player chooses to start the game
            if(response.equalsIgnoreCase("w"))
            {
                // Get player's name and create a new player
                String firstName = Mname.getFirstName();
                String lastName = Mname.getLastName();
                player = new Player(firstName, lastName);
                folog.FileOutLog(firstName, lastName, "Entered first and last name.");
                
                // Player selects a case
                Mselect.selectCase(cases);
                
                // Play rounds until the 4th round
                while(roundNum <= 4)
                {
                    Mplay.playRound(cases, roundNum);    
                    
                    // Provide option to change case after rounds 3 and 4
                    if(roundNum <= 2)
                    {
                        System.out.println("** You have a chance to change your case from the end Round 3! **");
                    }
                    
                    // Get a banker’s offer and handle the deal or no deal decision
                    System.out.println("End of Round " + roundNum);
                    double offer = Mbanker.bankerOffer(cases);
                    Mdeal.dealornodeal(offer);
                    
                    // Offer the player the chance to change their case in rounds 3 and 4
                    if(roundNum == 3 || roundNum == 4)
                    {
                        while(true)
                        {
                            System.out.print("Having mixed feelings now? You can change your case! (y or n) => ");
                            response = inputUI.getInput();

                            if(response.equalsIgnoreCase("y"))
                            {
                                folog.FileOutLog(firstName, lastName, "Decided to swap case.");
                                Mchange.changeCase(cases);
                                break;
                            }
                            else if(response.equalsIgnoreCase("n"))
                            {
                                folog.FileOutLog(firstName, lastName, "Refused to swap case.");
                                System.out.println();
                                break;
                            }
                            else
                            {
                                foerror.FileOutLog(firstName, lastName, "Invalid input - Swapping Cases - MStartGame");
                                System.out.println("Invalid!\n");
                            }
                        }
                    }
                    roundNum++;
                }
                
                // Final round and the lasy play of the game
                if(roundNum == 5)
                {
                    Mlast.playRound(cases, roundNum);
                    Mlast.lastPlay(cases);
                }
            }
            // If the player chooses to see the tutorial
            else if(response.equalsIgnoreCase("t"))
            {
                messageUI.tutorial();
                continue;
            }
            // If the player chooses to quit the game
            else if(response.equalsIgnoreCase("x"))
            {
                folog.FileOutLog(Player.firstName, Player.lastName, "User quit game.\n\n");
                messageUI.displayExitMessage();
                break;
            }
            // Handle invalid input
            else
            {
                System.out.println("\nInvalid input!");
                continue;
            }
            
            // Log the end of the game
            folog.FileOutLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
            foerror.FileOutLog(Player.firstName, Player.lastName, "Game Finished.\n\n");
            messageUI.displayExitMessage();
            break;
        }
    }
}
