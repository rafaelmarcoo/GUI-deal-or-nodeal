/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JButton;

/**
 *
 * @author rafae
 */
public class MechanicsPlayRound 
{
    public void playRound(JButton[] buttons, FrameGame frame) 
    {
        for(int i = 0; i < buttons.length; i++) 
        {
            buttons[i].addActionListener(e -> 
            {
                JButton clickedButton = (JButton) e.getSource();
                clickedButton.setEnabled(false); // Disable case after it's opened
                
                int caseNumber = Integer.parseInt(clickedButton.getText()); // Get case number
                double caseValue = MechanicsControl.cases.getCases().get(caseNumber);
                
                System.out.println("Opened case " + caseNumber + " with value: " + caseValue);
                
                MechanicsControl.cases.getCases().remove(caseNumber); // Remove opened case from list
                
//                frame.updateUI(); // Update game state based on opened case
            });
        }
    }
}
