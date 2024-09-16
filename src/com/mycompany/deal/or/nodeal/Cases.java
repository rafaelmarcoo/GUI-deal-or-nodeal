/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author rcman
 */

// This class is responsible for handling of cases and the shuffling of the values in the cases
// Retrieves prize values from a file, shuffling, and storing them in a HashMap collection
// Also contains a FILE READ FUNCTION  /resources/UIresources/prizes.txt
// Used a collection - HashMap 
public class Cases
{
    private HashMap<Integer, Double> cases; 
    private int[] caseNums;
    private double[] caseValues;
    private int totalCases = 26;
    
    public Cases()
    {
        cases = new HashMap<>();
        caseNums = new int[totalCases];
        caseValues = new double[totalCases];
        
        // File Read function - reading the money values from a file
        try 
        {
            FileReader fr = new FileReader("./resources/UIresources/prizes.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            int index = 0;
            while((line = br.readLine()) != null && index < totalCases) // Reading each line of the file and putting it into arrays
            {
                double value = Double.parseDouble(line);
                caseValues[index] = value;
                caseNums[index] = index + 1;
                index++;
            }
            br.close();  
            
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
        catch(FileNotFoundException ex) 
        {
            System.out.println("File not found.");
        }
        catch(IOException e)
        {
            System.out.println("Error reading from file.");
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
}


