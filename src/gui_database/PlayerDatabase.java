package gui_database;

import gui_project.ModelController.PlayerInfo;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDatabase extends DatabaseManager
{
    private int player_id = 1;
    PlayerInfo playerInfo;
    
    public PlayerDatabase(PlayerInfo playerInfo)
    {
        super();
        tableName = "PLAYER";
        this.playerInfo = playerInfo;
    }
    
    /**
     * method to insert a row into the Player table in the database
     */
    public void inputDataRow()
    {        
        try
        {
            statement = connection.createStatement();   
            statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
            
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            
            player_id = getNextID();
            
            statement.executeUpdate("INSERT INTO PLAYER VALUES (" + player_id + ", \'" + 
                    playerInfo.getName() + "\', " + playerInfo.getAge() + ", \'" + playerInfo.getGender() + "\')");            
        }
        catch (SQLException e)
        {
            System.err.println("SQL INSERT exception at " + player_id + " - " + e.getMessage());
        }
    }
}
