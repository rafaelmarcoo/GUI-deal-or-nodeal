/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rcman
 */

// This class is responsible for comparing the player's case value 
// against another value (the banker's offer) in the game.
// Extends MechanicsControl as it contains all info required in one game
// and implements the ICompareValuesUI interface
public class GameCompareUI extends MechanicsControl implements ICompareValuesUI
{
    @Override
    public void compareValues(double playerValue, double otherValue)
    {
        if(playerValue > otherValue)
        {
            System.out.println("Great deal! Congratulations! Amazing!");
        }
        else
        {
            System.out.println("Oopppps bad luck! Better luck next time!");
        }
    }
}
