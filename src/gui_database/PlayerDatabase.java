package gui_database;

import static gui_database.DatabaseManager.getPASSWORD;
import static gui_database.DatabaseManager.getURL;
import static gui_database.DatabaseManager.getUSER_NAME;
import java.sql.DriverManager;

/**
    * I planned to have 3 tables: Hint (5 hints), Player (all players), 
    * and Hint_grab (composite entity - records all hints 
    * the player grabbed, will be reseted every time when the game ends);
    * 
    * However, my mind is lagged, so I just created 2 tables first: 
    * Player and Hint (Hint works as Hint_grab), we do not have any table 
    * that holds the five hints at the beginning.
*/
public class PlayerDatabase extends DatabaseManager
{
    private  static int PLAYER_ID = 1;
    
    public PlayerDatabase()
    {
        super();
        tableName = "PLAYER";
    }
    
    public void inputDataRow(String player_name, int player_age, char player_gender)
    {
        try
        {
            statement = connection.createStatement();         
            statement.executeUpdate("INSERT INTO PLAYER VALUES (" + PLAYER_ID + ", \'" + 
                    player_name + "\', " + player_age + ", \'" + player_gender + "\')");
            
            PLAYER_ID++;
        }
        catch (Throwable e)
        {
            System.err.println("SQL INSERT exception at " + PLAYER_ID + " - " + e.getMessage());
        }
    }
    
    public void createPlayerTable(){
        
    }
}
