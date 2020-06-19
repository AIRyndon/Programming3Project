package gui_database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
    HINT table will be reseted every time when the game ends
*/
public class HintDatabase extends DatabaseManager 
{
    private int hint_id = 1;
    
    public HintDatabase()
    {
        super();
        resetTable();
        tableName = "HINT";
    }
    
    public void inputDataRow(String hint_name, String description)
    {
        try
        {
            statement = connection.createStatement();   
            statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
            
            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            
            hint_id = getNextID();    
            
            statement.executeUpdate("INSERT INTO HINT VALUES (" + hint_id + ", '" +
                    hint_name + "', '" + description + "')");            
        }
        catch (SQLException e)
        {
            System.err.println("SQL INSERT exception at " + hint_id + " - " + e.getMessage());
        }
    }
    
    public void resetTable()
    {
        try
        {
            connection = DriverManager.getConnection(getURL(),getUSER_NAME(),getPASSWORD());
            statement = connection.createStatement();
                       
            statement.executeUpdate("TRUNCATE TABLE HINT");            
        }
        catch (SQLException e)
        {
            System.err.println("SQL DELETE exception at " + hint_id + " - " + e.getMessage());
        }
    }
}
