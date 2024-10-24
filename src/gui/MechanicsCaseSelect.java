/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author rafae
 */
public class MechanicsCaseSelect extends MechanicsControl
{
    public void selectCase(FrameMainGame frame, JTextField jTextField1)
    {
        String strNum = jTextField1.getText().trim();
        try
        {
            int caseNum = Integer.parseInt(strNum);
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
            }
            else
            {
                playerCase = caseNum;
                playerCaseValue = cases.getCases().get(caseNum);
                cases.getCases().remove(caseNum);
                roundNum++;
                jTextField1.setText("");

                JOptionPane.showMessageDialog(frame, "You have chosen case " + playerCase + "\nContains: $" + playerCaseValue);
                frame.refreshUI(); 
            }
        }
        catch(NumberFormatException E)
        {
            JOptionPane.showMessageDialog(frame, "Invalid input! Only case numbers!");
        }
        
    }
}
