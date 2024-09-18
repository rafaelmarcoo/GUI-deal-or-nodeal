/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deal.or.nodeal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Random;

/**
 *
 * @author rafae
 */
public class DBInitGoodComments implements IDBInitComments, ICommentUI
{
    public static final String dbURL = "jdbc:derby:dealornodealDB;create=true";
    
    @Override
    public void dBInitComments()
    {        
        try
        {
            FileReader fr = new FileReader("./resources/UIresources/goodcomment.txt");
            BufferedReader br = new BufferedReader(fr);
            
            try
            {
                Connection conn = DriverManager.getConnection(dbURL);
                Statement stmt = conn.createStatement();
                
                DatabaseMetaData dbMeta = conn.getMetaData();
                ResultSet rs = dbMeta.getTables(null, null, "GOODCOMMENTSTABLE", null);
                if(!rs.next())
                {
                    stmt.executeUpdate("DROP TABLE GoodCommentsTable");
                }
                
                stmt.executeUpdate("CREATE TABLE GoodCommentsTable"
                            + " (ID INT PRIMARY KEY, "
                            + "COMMENT VARCHAR(256))");
                
                String line;
                int id = 1;
                while((line = br.readLine()) != null)
                {
                    String insertQuery = "INSERT INTO GoodCommentsTable"
                            + " (ID, COMMENT) VALUES (?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(insertQuery);
                    pstmt.setInt(1, id);
                    pstmt.setString(2, line);
                    pstmt.executeUpdate();
                    id++;
                }
                br.close();
                
//                // Test
//                rs = stmt.executeQuery("SELECT * FROM GoodCommentsTable");
//                while(rs.next())
//                {
//                    System.out.println("ID: " + rs.getInt("ID") + "\n"
//                            + "Comment: " + rs.getString("COMMENT") + "\n");  
//                }
//                System.out.println("");
                conn.close();
            }
            catch(Exception E)
            {
                E.printStackTrace();
            } 
        }
        catch(FileNotFoundException E)
        {
            System.out.println("File not found!");
        }
    }
    
    @Override
    public String comment()
    {
        int numOfComments = 10;
        String comment = "";
        Random rand = new Random();
        int id = rand.nextInt(numOfComments) + 1;
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL);
            
            String query = "SELECT COMMENT FROM GoodCommentsTable WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                comment = rs.getString("COMMENT");
            }
            
//            // Test
//            System.out.println("ID: " + id + "\n" + "Comment: " + comment);
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        
        return comment;
    }
    
//    public static void main(String[] args) 
//    {
//        DBInitGoodComments();
//        comment();
//    }
}
