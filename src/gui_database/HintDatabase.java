package gui_database;

public class HintDatabase extends DatabaseManager 
{
    private int hint_id = 1;
    
    public HintDatabase()
    {
        super();
        tableName = "HINT";
    }  
}
