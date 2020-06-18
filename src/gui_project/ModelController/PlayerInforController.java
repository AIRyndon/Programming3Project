package gui_project.ModelController;

import gui_database.PlayerDatabase;
import gui_project.View.PlayerInforView;
import gui_project.View.StoryPanel;
import java.sql.SQLException;

public class PlayerInforController
{
    private static PlayerDatabase playerDatabase;
    private MainController mainCtrl;
    private PlayerInfor playerInfor;
    private PlayerInforView view;
    private StoryPanel storyPanel;
    
    PlayerInforController(MainController mainCtrl, PlayerInfor playerInfor) throws SQLException 
    {
        this.mainCtrl = mainCtrl;
        this.playerInfor = playerInfor;
        playerDatabase = new PlayerDatabase(playerInfor);
        System.out.println(playerInfor.getName());
        view = new PlayerInforView(mainCtrl, this);
        storyPanel = new StoryPanel(mainCtrl, this);
    }

    public void savePlayerInfo() throws SQLException
    {
        playerDatabase.inputDataRow();
        playerDatabase.printData();
    }
    
    //Getters and setters
    public PlayerInforView getView() 
    {
        return view;
    }

    public PlayerInfor getPlayerInfor() 
    {
        return playerInfor;
    }
    
    public StoryPanel getStoryPanel()
    {
        return storyPanel;
    }
    
    public void setPlayerName(String name)
    {
        this.getPlayerInfor().setName(name);
    }
    
    public void setPlayerAge(int age)
    {
        this.getPlayerInfor().setAge(age);
    }
    
    public void setPlayerGender(char gender)
    {
        this.getPlayerInfor().setGender(gender);
    }
}
