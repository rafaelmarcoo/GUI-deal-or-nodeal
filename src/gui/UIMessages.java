/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JLabel;

/**
 *
 * @author rafae
 */
public class UIMessages extends MechanicsControl
{
    public void uiRound(JLabel jLabel)
    {
        if(roundNum == 0)
        {
            jLabel.setText("Please select one case to keep!");
        }
        else
        {
            jLabel.setText("Round " + roundNum);
        }
    }
    
    public void uiName(JLabel jLabel)
    {
        jLabel.setText("Goodluck " + Player.firstName + " " + Player.lastName + "!");
    }
    
    public void uiPlayerCase(JLabel jLabel)
    {
        if(roundNum == 0)
        {
            jLabel.setText("Welcome! Please select a case to keep for the game!");
        }
        else
        {
            jLabel.setText("Your Case Number is: " + playerCase);
        }
    }
    
    public void uiCasesToOpen(JLabel jLabel, int count)
    {
        jLabel.setText(count + " more cases to open this round!");
    }
}
