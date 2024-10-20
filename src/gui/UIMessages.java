/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import com.mycompany.deal.or.nodeal.Player;
import javax.swing.JLabel;

/**
 *
 * @author rafae
 */
public class UIMessages extends MechanicsControl
{
    public void uiRound(JLabel jLabel)
    {
        jLabel.setText("Round " + roundNum);
    }
    
    public void uiName(JLabel jLabel)
    {
        jLabel.setText("Goodluck " + Player.firstName + " " + Player.lastName + "!");
    }
    
}
