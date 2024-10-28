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
    This class holds information about the player
*/
public class Player 
{
    private static String firstName;
    private static String lastName;
    
    public Player(String f, String l)
    {
        Player.firstName = f;
        Player.lastName = l;
    } 
    
    // Reset player variables
    public void reset() 
    {
        Player.setFirstName("");
        Player.setLastName("");
    }

    /**
     * @return the firstName
     */
    public static String getFirstName() 
    {
        return firstName;
    }

    /**
     * @param aFirstName the firstName to set
     */
    public static void setFirstName(String aFirstName) 
    {
        firstName = aFirstName;
    }

    /**
     * @return the lastName
     */
    public static String getLastName() 
    {
        return lastName;
    }

    /**
     * @param aLastName the lastName to set
     */
    public static void setLastName(String aLastName) 
    {
        lastName = aLastName;
    }
}
