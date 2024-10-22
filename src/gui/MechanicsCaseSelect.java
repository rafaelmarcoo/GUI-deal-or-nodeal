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
public class MechanicsCaseSelect extends MechanicsControl
{
    public void selectCase(JButton[] buttons, FrameGame frame)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            int caseNum = i + 1;
            buttons[i].addActionListener(e -> 
            {
                playerCase = caseNum;
                playerCaseValue = cases.getCases().get(caseNum);
                cases.getCases().remove(caseNum);
                
                System.out.println("Player selected case " + caseNum + " with value: " + MechanicsControl.playerCaseValue);
                
                JButton clickedButton = (JButton) e.getSource();
                clickedButton.setEnabled(false);
                
                roundNum++;
                
                frame.nextRound();
//                frame.dispose();
//                FrameGame gameFrame = new FrameGame();
//                gameFrame.setVisible(true);
            });
        }
    }
}
