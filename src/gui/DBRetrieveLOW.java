/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author rafae
 */

/*
    This class contains a method to retrieve all the list of past winners that has 
    played Deal Or No Deal. It returns an ArrayList to be displayed in WinnersFrame
*/
public class DBRetrieveLOW 
{
    public ArrayList<String[]> retrieveWinnersList()
    {
        // ArrayList of list of winners
        ArrayList<String[]> list = new ArrayList<>();
        String dbURL = "jdbc:derby:dealornodealDB;create=true";
        
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            // Query to retrieve the data from table
            ResultSet rs = stmt.executeQuery("SELECT * FROM ListOfWinners");
            
            // Make entries to ArrayList containing String[] of data
            while(rs.next())
            {
                String[] win = new String[4];
                win[0] = rs.getDate("DATE_PLAYED").toString();
                win[1] = rs.getString("FIRST_NAME");
                win[2] = rs.getString("LAST_NAME");
                win[3] = String.valueOf(rs.getDouble("AMOUNT_WON"));
                
                list.add(win);
            }
            conn.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        
        // Return the list
        return list;
    }
}
