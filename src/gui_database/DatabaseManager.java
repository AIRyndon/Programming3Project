package gui_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Here, database is used to store player information and hints that the player grabbed.
*/
public class DatabaseManager 
{
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public ResultSetMetaData resultSetMetaData;

    protected String tableName;
    private static final String USER_NAME = "group4";
    private static final String PASSWORD = "group4";
    private static final String URL = "jdbc:derby:DerbyDB/RPGDetective";

    public DatabaseManager()
    {
        establishConnection();
    }

    public void establishConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(getURL(),getUSER_NAME(),getPASSWORD());
                
            } 
            catch (SQLException ex)
            {
                System.out.println("SQL exception");
            }
        }
    }
    
    public Connection getConnection()
    {
        return this.connection;
    }

    public void printData()
    {
        statement = null;
        resultSet = null;

        try
        {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();

            while (resultSet.next())
            {
                for (int i = 1; i <= columnsNumber; i++)
                {
                    if (i > 1)
                    {
                        System.out.print(",  ");
                    }

                    System.out.print(resultSet.getString(i));
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(HintDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Getters
    public static String getUSER_NAME()
    {
        return USER_NAME;
    }

    public static String getPASSWORD()
    {
        return PASSWORD;
    }

    public static String getURL()
    {
        return URL;
    }
}
