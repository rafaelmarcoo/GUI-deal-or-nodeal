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
 This class serves as a base class for managing the core game mechanics 
 of Deal or No Deal. It keeps static variables for tracking the player, the selected case, 
 the value of the selected case, the current round number, and the collection of cases.
*/
public class MechanicsControl
{
    public static Player player;
    public static int playerCase;
    public static double playerCaseValue;
    public static int roundNum;
    public static Cases cases;
    
    public MechanicsControl()
    {
        cases = new Cases();
        roundNum = 1;
    }
      
}
