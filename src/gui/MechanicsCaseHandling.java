/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author rafae
 */
public class MechanicsCaseHandling implements ActionListener
{
    private JButton caseButton;
    private int caseNum;
    
    public MechanicsCaseHandling(JButton caseButton, int caseNum)
    {
        this.caseButton = caseButton;
        this.caseNum = caseNum;
    }
    
    @Override
    public void actionPerformed(ActionEvent E)
    {
        revealCase(caseNum);
        
        caseButton.setEnabled(false);
    }

    private void revealCase(int caseNum) 
    {
        
    }
}
