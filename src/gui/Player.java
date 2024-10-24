/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author rcman
 */
public class Player 
{
    public static String firstName;
    public static String lastName;
    
    public Player(String f, String l)
    {
        Player.firstName = f;
        Player.lastName = l;
    } 
    
    public void reset() 
    {
        Player.firstName = "";
        Player.lastName = "";
    }
}
