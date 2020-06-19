package gui_database;

import java.sql.SQLException;

public class HintDatabase extends DatabaseManager 
{    
    public HintDatabase()
    {
        super();
        tableName = "HINT";
    }
    
    public String getDataRow(int ID)
    {
        String dataRow = "";
        
        try
        {
            statement = connection.createStatement();
            
            resultSet = statement.executeQuery("SELECT HINT_NAME, DESCRIPTION FROM " + tableName
                + " WHERE HINT_ID = " + ID);
            
            resultSet.next();
            dataRow = resultSet.getString(1) + "/" + resultSet.getString(2);
        }
        catch (SQLException e)
        {
            System.err.println("SQL INSERT exception at GETTING HINT INFO - " + e.getMessage());
        }
        
        return dataRow;
    }
}
