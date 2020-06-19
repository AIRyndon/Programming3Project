package gui_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager 
{
    public Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    public ResultSetMetaData resultSetMetaData;
    public PreparedStatement prepareStatement;
    public String generatedColumns[] = { "ID" };

    protected String tableName;
    private static final String USER_NAME = "group4";
    private static final String PASSWORD = "group4";
    private static final String URL = "jdbc:derby:DerbyDB/RPGDetective";

    public DatabaseManager()
    {
        establishConnection();
    }
    
    /**
     *
     * @return
     */
    public int getNextID()
    {
        int lastID = 0;
        
        try 
        {
            resultSet.last();
            lastID = resultSet.getRow();
            resultSet.beforeFirst();
        }
        catch(SQLException ex)
        {
            System.err.println("SQL Exception at GETNEXTID - " + ex.getMessage());
        }
        
        return lastID + 1;
    }
    
    /**
     * starts a connection to the embedded database
     */
    public void establishConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(getUrl(),getUsername(),getPassword());
                
            } 
            catch (SQLException ex)
            {
                System.out.println("SQL exception");
            }
        }
    }
    
    /**
     * 
     * @return a Connection object from the embedded database
     */
    public Connection getConnection()
    {
        return this.connection;
    }

    /**
     *
     */
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
                        System.out.println(",  ");
                    }

                    System.out.print(resultSet.getString(i));
                }
            }
        }
        catch (SQLException ex)
        {
            System.err.println("SQL Exception in PRINTDATA - " + ex.getMessage());
        }
    }

    /**
     *
     * @return the database Username
     */
    public static String getUsername()
    {
        return USER_NAME;
    }

    /**
     *
     * @return the database password
     */
    public static String getPassword()
    {
        return PASSWORD;
    }

    /**
     *
     * @return the database URL
     */
    public static String getUrl()
    {
        return URL;
    }
}
