/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

/**
 *
 * @author rafae
 */

// This class handles the case display UI
// It displays the cases in an easy to read/digest display
// using the encapsulated methods like getCaseNums ()
// and to access the HashMap collection using getCases()
public class GameCaseDisplayUI implements ICaseDisplayUI
{
    @Override
    public void showCases(Cases cases)
    {
        // Title
        System.out.println("\nCases:");
        int count = 0;
        
        // For loop to traverse through the HashMap
        for(int i = 1; i <= cases.getCaseNums().length; i++)
        {
            // For formatting
            if(count == 7)
            {
                System.out.println();
                count = 0;
            }
            
            // If case has already been opened, it gets taken off the HashMap, and hence displays 'X'
            if(!cases.getCases().containsKey(i))
            {
                System.out.print("{ X }  ");
            }
            else // else it displays the caseNumber
            {
                System.out.print("{ " + i + " }  ");
            }
            count++;
        }
        System.out.println();
    }
}
