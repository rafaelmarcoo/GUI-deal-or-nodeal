/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;


/**
 *
 * @author rcman
 */

/*
 This class serves as a base class for managing the core game mechanics 
 of Deal or No Deal. It keeps static variables for tracking the player, the selected case, 
 the value of the selected case, the current round number, and the collection of cases.
*/
public abstract class MechanicsControl
{
    public static Player player;
    public static int playerCase;
    public static double playerCaseValue;
    public static int roundNum;
    public static Cases cases;
    public static int count;
    
    public MechanicsControl()
    {
        cases = new Cases();
        roundNum = 0;
        count = 5;
    }
    
    public static void resetGame() 
    {
        roundNum = 0;
        count = 5;
        playerCase = -1;
        playerCaseValue = 0.0;
        
        if(player != null) 
        {
            player.reset();  // Reset player name data
        }
    }
}
