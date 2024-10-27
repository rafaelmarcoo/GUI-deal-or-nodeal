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
public class MechanicsCaseSelect extends MechanicsControl implements ICaseSelect
{
    // DB - Instances to log game and errors
    DBGameLog dbGLog = new DBGameLog();
    DBErrorLog dbELog = new DBErrorLog();
    
    @Override
    public void selectCase(FrameMainGame frame, JTextField jTextField1)
    {
        String strNum = jTextField1.getText().trim();
        try
        {
            int caseNum = Integer.parseInt(strNum);
            if(caseNum <= 0 || caseNum > cases.getCaseNums().length)
            {
                JOptionPane.showMessageDialog(frame, "Invalid case number! Please try again!");
                jTextField1.setText("");
                
                // DB Error Log
                dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid case number! - MCaseSelect");
            }
            else
            {
                playerCase = caseNum;
                playerCaseValue = cases.getCases().get(caseNum);
                cases.getCases().remove(caseNum);
                roundNum++;
                jTextField1.setText("");
                JOptionPane.showMessageDialog(frame, "You have chosen case " + playerCase + "!");
                
                // DB Log
                dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Selected case " + playerCase + " for the first time.");         
                dbGLog.dbGameLog(Player.getFirstName(), Player.getLastName(), "Start of Round " + roundNum);
                
                frame.refreshUI(); 
            }
        }
        catch(NumberFormatException E)
        {
            JOptionPane.showMessageDialog(frame, "Invalid input! Only case numbers!");
            jTextField1.setText("");
            
            // DB Error Log
            dbELog.dbErrorLog(Player.getFirstName(), Player.getLastName(), "Invalid input! - MCaseSelect");
        }
        
    }
}
