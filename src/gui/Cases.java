/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.HashMap;
import java.util.Random;
import java.sql.*;

/**
 *
 * @author rcman
 */

public class Cases
{
    private static HashMap<Integer, Double> cases; 
    private int[] caseNums;
    private double[] caseValues;
    private double[] caseNonShuffled;
    private final static int TOTALCASES = 26;
    
    DBInitCases initCases = new DBInitCases(); 
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    public Cases()
    {
        cases = new HashMap<>();
        caseNums = new int[TOTALCASES];
        caseValues = new double[TOTALCASES];
        caseNonShuffled = new double[TOTALCASES];

        // Initialise the prizes in the tables
        initCases.dbInitCases();
        
        retrievePrizes();
        shuffleArrays();
        fillUpHashMap();
    }
    
    private void retrievePrizes()
    {
        try
        {
            // Establish connection
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            // Query to traverse through the prize values
            ResultSet rs = stmt.executeQuery("SELECT * FROM Prizes");
            int index = 0;
            while(rs.next()) // Then store the values in the array to be used in the hashmap
            {
                caseNonShuffled[index] = rs.getDouble("MONEY");
                caseValues[index] = rs.getDouble("MONEY");
                caseNums[index] = index + 1;
                index++;
            }
            conn.close();
        }
        catch(SQLException E)
        {
            E.printStackTrace();
        }
    }
    
    private void shuffleArrays()
    {
        // Shuffling of caseValues array using a for loop and a Random object
        Random rand = new Random();
        for(int i = 0; i < caseValues.length; i++)
        {
            int randomIndex = rand.nextInt(caseValues.length);
            double temp = caseValues[randomIndex];
            caseValues[randomIndex] = caseValues[i];
            caseValues[i] = temp;
        }
    }
    
    private void fillUpHashMap()
    {
        // Filling in the HashMap with caseNums as the keys and caseValues as the values 
        for(int i = 0; i < caseNums.length; i++)
        {
            cases.put(caseNums[i], caseValues[i]);
        }
    }
    
    // Encapsulation - methods to access the HashMap, arrays - for implementation of game methods
    public HashMap<Integer, Double> getCases() 
    {
        return cases;
    }

    public int[] getCaseNums()
    {
        return caseNums;
    }

    public double[] getCaseValues() 
    {
        return caseValues;
    }
    
    public double[] getUnshuffledValues()
    {
        return caseNonShuffled;
    }
    
    public int getTotalCases() 
    {
        return TOTALCASES;
    }    
}


