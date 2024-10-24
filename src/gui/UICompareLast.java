/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;

/**
 *
 * @author rcman
 */

// This class handles the message displayed in the UI when the player gets a bad or good value
// Implements ICompareValuesUI interface
// Extends MechanicsControl as it contains all info required in one game
public class UICompareLast extends MechanicsControl implements ICompareValuesUI
{
    @Override
    public void compareValues(FrameMainGame frame, double playerValue, double otherValue)
    {
        // If the player's case is higher than the otherValue, then it's a good comment. Else, no
        if(playerValue > otherValue)
        {
            JOptionPane.showMessageDialog(frame, "Great choice! Congratulations!");
        }
        else
        {
            JOptionPane.showMessageDialog(frame, "Oopppps bad luck! Better luck next time!");
        }
    }
}
