/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rcman
 */

// This class handles a FILE READ FUNCTION 
// This class handles the remarks displayed in the UI when a player opens a high valued case,
// read from a file contained in /resources/UIresources/badcomment.txt
// Used a collection - ArrayList to store the remarks/comments
public class GameBadCommentUI implements ICommentUI // implements ICommentUI
{
    // Collection used
    private ArrayList<String> comments;
    
    // Constructor which uses FILE READ to read the comments
    public GameBadCommentUI()
    {
        comments = new ArrayList<>();
        
        try
        {
            FileReader fr = new FileReader("./resources/UIresources/badcomment.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while((line = br.readLine()) != null)
            {
                comments.add(line);
            }
            br.close();
        }
        catch(FileNotFoundException E)
        {
            System.out.println("File not found.");
        }
        catch(IOException E)
        {
            System.out.println("Error reading from this file.");
        }
    }
    
    // Implemented method to randomly pick a bad comment from the ArrayList
    @Override
    public String comment()
    {
        String comment;
        int size = comments.size();
        Random rand = new Random();
        int index = rand.nextInt(size);
        comment = comments.get(index);
        
        return comment;
    }
}
