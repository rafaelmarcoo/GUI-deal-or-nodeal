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
 This class handles the input for obtaining the player's first 
 and last names, ensuring they are valid inputs. It implements the IGetName interface.
*/
public class MechanicsGetName implements IGetName
{
    GameInputUI inputUI = new GameInputUI();
    GameMessageUI messageUI = new GameMessageUI();
        
    @Override
    public String getFirstName()
    {
        System.out.println();
        String first;
        
        // Loop until a valid first name is entered
        while(true)
        {
            try
            {
                System.out.print("Please enter your first name => ");
                first = inputUI.getInput();
                
                // Allow the player to exit the game by entering 'x'
                if(first.equalsIgnoreCase("x"))
                {
                    messageUI.displayExitMessage();
                    System.exit(0);
                }
                
                // Validate that the name contains only letters and spaces
                if(!first.matches("[a-zA-Z ]+"))
                {
                    throw new IllegalArgumentException("Invalid name! No numbers or special characters!");
                }
                
                break;
            }
            catch(IllegalArgumentException E)
            {
                System.out.println("Try again!");
            }
        }
        
        // Convert the name to uppercase before returning
        first = first.toUpperCase();
        return first;
    }
    
    @Override
    public String getLastName()
    {
        String last;
        
        // Loop until a valid last name is entered
        while(true)
        {
            try
            {
                System.out.print("Please enter your last name => ");
                last = inputUI.getInput();
                
                // Allow the player to exit the game by entering 'x'
                if(last.equalsIgnoreCase("x"))
                {
                    messageUI.displayExitMessage();
                    System.exit(0);
                }
                
                // Validate that the name contains only letters and spaces
                if(!last.matches("[a-zA-Z ]+"))
                {
                    throw new IllegalArgumentException("Invalid name! No numbers or special characters!");
                }
                
                break;
            }
            catch(IllegalArgumentException E)
            {
                System.out.println("Try again!");
            }
        }
        
        // Convert the name to uppercase before returning
        last = last.toUpperCase();
        return last;
    }
}
