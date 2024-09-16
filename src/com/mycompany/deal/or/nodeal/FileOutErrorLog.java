/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rafae
 */

// This class handles a FILE WRITE FUNCTION 
// This class writes all the errors a player catches in one game within a file, contained /resources/ErrorLogs
// Also uses date object to write down date and time the error was caught
public class FileOutErrorLog implements IFileOut
{
    @Override
    public void FileOutLog(String firstName, String lastName, String action)
    {
        // Date object
        LocalDate date = LocalDate.now();
        
        // Time object and formatting
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeNow = time.format(formatTime);
        
        // File Write function - writing the error a player catches and the time
        try
        {
            FileOutputStream fos = new FileOutputStream("./resources/ErrorLogs/" + date + "_" +
                    firstName + "_" + lastName + "_Error_Logs.txt", true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(timeNow + " || Error: " + action);
            pw.close();
        }
        catch(FileNotFoundException E)
        {
            System.out.println(E);
        }
    }
}
