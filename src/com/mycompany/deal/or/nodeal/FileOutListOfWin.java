/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;

/**
 *
 * @author rcman
 */

// This class handles a FILE WRITE FUNCTION 
// This class writes the list of winners and the value they won in their games within a file, 
// contained in /resources/ListOfWinnings/listofwinnings.txt
// Also uses date object to write down date
public class FileOutListOfWin
{
    public void FileOutListWin(String firstName, String lastName, double winnings)
    {
        // Date object
        LocalDate date = LocalDate.now();
        
        
        // File Write function - writes the winnings of a player when the game finishes
        try
        {
            FileOutputStream fos = new FileOutputStream("./resources/ListOfWinnings/listofwinnings.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(date + " || " + firstName + " " + lastName + " - Winnings: $" + winnings);
            pw.close();
        }
        catch(FileNotFoundException E)
        {
            System.out.println(E);
        }
    }
    
}
