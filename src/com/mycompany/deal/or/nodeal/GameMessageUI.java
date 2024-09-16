/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rafae
 */


// This class is responsible for displaying various messages to the player 
// in the Deal or No Deal game. It implements the IMessageUI interface, ensuring it provides 
// methods to display the welcome message, tutorial, and exit message.

public class GameMessageUI implements IMessageUI
{
    // Displays welcome message to the player, with options to start, quit, and for the tutorial
    @Override
    public void displayWelcomeMessage() 
    {
        System.out.println("\nWelcome to Deal or No Deal!");
        System.out.print("Enter 'w' to start a game! || Enter 't' for a tutorial! || To quit, enter 'x'!  => ");
    }
    
    // Displays game tutorial
    @Override
    public void tutorial()
    {
        System.out.println("\n--------------------------------------------------"
                + "----------------------------------------------------------------------------------");
        System.out.println("\nThere are 26 cases, each containing various amount of prize money from as little as"
                + " $0.01 to a whopping $1,000,000.");
        System.out.println("\nHow to play:");
        System.out.println(" - You choose one case to keep for the game.");
        System.out.println(" - You will then select and open a number of cases each round, revealing the amounts.");
        System.out.println(" - After each round, the banker will offer you a cash amount to quit the game.");
        System.out.println(" - You can choose to 'Deal' and take the offer or 'No Deal' and continue playing");
        System.out.println(" - You have the chance to change cases from the end of round 3 if you ever feel like doing so!");
        System.out.println(" - In the last play, where there is only one case left, you have the choice to keep your case or swap it with the last one!");
        System.out.println("\n***** Good luck and have fun! *****");
        System.out.println("\n--------------------------------------------------"
                + "----------------------------------------------------------------------------------");
    }
    
    // Displays exit message
    @Override
    public void displayExitMessage()
    {
        System.out.println("\n****************************************************************************************************************************");
        System.out.println("Thank you for playing Deal or No Deal! See you soon :)");
        System.out.println("****************************************************************************************************************************");
    }
}
