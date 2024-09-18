/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

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
    private int totalCases = 26;
    
    DBInitCases initCases = new DBInitCases(); 
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    public Cases()
    {
        cases = new HashMap<>();
        caseNums = new int[totalCases];
        caseValues = new double[totalCases];

        initCases.dbInitCases();
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM Prizes");
            int index = 0;
            while(rs.next())
            {
                caseValues[index] = rs.getDouble("MONEY");
                caseNums[index] = index + 1;
                index++;
            }
            conn.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }

        // Shuffling of caseValues array using a for loop and a Random object
        Random rand = new Random();
        for(int i = 0; i < caseValues.length; i++)
        {
            int randomIndex = rand.nextInt(caseValues.length);
            double temp = caseValues[randomIndex];
            caseValues[randomIndex] = caseValues[i];
            caseValues[i] = temp;
        }

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
    
    public int getTotalCases() 
    {
        return totalCases;
    }
    
    public static void main(String[] args) 
    {
        Cases gameCases = new Cases();
        // Test
        for(Integer i : gameCases.getCases().keySet()) 
        {
            System.out.println("Case Num: " + i + " Value: " + cases.get(i));
            System.out.println("");
        }
    }
}


