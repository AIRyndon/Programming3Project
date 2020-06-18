package gui_database;

import gui_project.ModelController.PlayerInfor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDatabase extends DatabaseManager
{
    private int player_id = 1;
    PlayerInfor playerInfo;
    
    public PlayerDatabase(PlayerInfor playerInfo) throws SQLException
    {
        super();
        tableName = "PLAYER";
        this.playerInfo = playerInfo;
    }
    
    public void inputDataRow() throws SQLException
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
        catch (Throwable e)
        {
            System.err.println("SQL INSERT exception at " + player_id + " - " + e.getMessage());
        }
    }
    
    public void createPlayerTable()
    {
        
    }
}
