/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.JTextArea;

/**
 *
 * @author rafae
 */
public class UICaseDisplay 
{

    private JTextArea caseTextArea;

    // Constructor to accept JTextArea from the FrameMainGame
    public UICaseDisplay(JTextArea caseTextArea) 
    {
        this.caseTextArea = caseTextArea;
    }

//    @Override
    public void showCases(Cases cases) 
    {
        // Clear the previous content
        caseTextArea.setText(""); 
        
        // StringBuilder to construct case display text
        StringBuilder caseDisplay = new StringBuilder();
        int count = 0;
        caseDisplay.append("\n");
        // Loop through case numbers
        for (int i = 1; i <= cases.getCaseNums().length; i++) {
            // If 7 cases are printed in a row, add a newline for formatting
            if (count == 7) {
                caseDisplay.append("\n\n\n\n");
                count = 0;
            }

            // Display 'X' if case has already been opened, else display case number
            if (!cases.getCases().containsKey(i)) {
                caseDisplay.append("      { X }   ");
            } else {
                caseDisplay.append("      { ").append(i).append(" }   ");
            }
            count++;
        }
        
        // Set the constructed text to the JTextArea
        caseTextArea.setText(caseDisplay.toString());
    }
}
