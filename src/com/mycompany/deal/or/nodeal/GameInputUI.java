/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.util.Scanner;

/**
 *
 * @author rafae
 */


// This class is responsible for handling user input in the game.
// It implements the IInputUI interface, ensuring it provides the getInput method 
// for receiving input from the player

public class GameInputUI implements IInputUI
{
    // Implemented method
    @Override
    public String getInput()
    {
        // Prompts input from player
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim(); // returns the input from the user
    }
}
