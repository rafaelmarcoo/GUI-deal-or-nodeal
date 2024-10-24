/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author rafae
 */
import com.mycompany.deal.or.nodeal.*;
import java.sql.*;
import java.time.LocalDate;


/*
    This class contains a Database WRITE method, saving the winnings of a player
    after a game. It implements IDBListWin
*/
public class DBListOfWin implements IDBListWin
{
    @Override
    public void dbListWin(String firstName, String lastName, double winnings)
    {
        String dbURL = "jdbc:derby:dealornodealDB;create=true";
        LocalDate date = LocalDate.now(); // To get current dare
        
        try
        {
            Connection conn = DriverManager.getConnection(dbURL);
            Statement stmt = conn.createStatement();
            
            // To check if table exists
            DatabaseMetaData dbMeta = conn.getMetaData();
            ResultSet rs = dbMeta.getTables(null, null, "LISTOFWINNERS", null);
            if(!rs.next()) // If table does not exist then create it
            {
                stmt.executeUpdate("CREATE TABLE ListOfWinners ("
                    + "DATE_PLAYED DATE, "
                    + "FIRST_NAME VARCHAR(50), "
                    + "LAST_NAME VARCHAR(50), "
                    + "AMOUNT_WON DOUBLE)");
            }
            
            // Insert the new record into ListOfWinners Table
            String insertQuery = "INSERT INTO ListOfWinners (DATE_PLAYED, FIRST_NAME, LAST_NAME, AMOUNT_WON)"
                    + " VALUES (?, ?, ? ,?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setDouble(4, winnings);
            pstmt.executeUpdate();
            
            conn.close(); // Close connection
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
    }
}
