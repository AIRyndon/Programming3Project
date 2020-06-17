package gui_database;

import java.sql.DriverManager;

/**
    HINT table will be reseted every time when the game ends
*/
public class HintDatabase extends DatabaseManager 
{
    private static int HINT_ID = 1;
    
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
            statement.executeUpdate("INSERT INTO HINT VALUES (" + HINT_ID + ", '" +
                    hint_name + "', '" + description + "')");
            
            HINT_ID++;
        }
        catch (Throwable e)
        {
            System.err.println("SQL INSERT exception at " + HINT_ID + " - " + e.getMessage());
        }
    }
    
    public void resetTable()
    {
        try
        {
            connection = DriverManager.getConnection(getURL(),getUSER_NAME(),getPASSWORD());
            statement = connection.createStatement();
            
            
            statement.executeUpdate("TRUNCATE TABLE HINT");
            
            //HINT_ID++;
        }
        catch (Throwable e)
        {
            System.err.println("SQL DELETE exception at " + HINT_ID + " - " + e.getMessage());
        }
    }
}
