package gui_project.ModelController;

import gui_database.PlayerDatabase;
import gui_project.View.PlayerInforView;
import gui_project.View.StoryPanel;

public class PlayerInfoController
{
    private static PlayerDatabase PLAYERDATABASE;
    private MainController mainCtrl;
    private PlayerInfo playerInfo;
    private PlayerInforView view;
    private StoryPanel storyPanel;
    
    public PlayerInfoController(MainController mainCtrl, PlayerInfo playerInfo) 
    {
        this.mainCtrl = mainCtrl;
        this.playerInfo = playerInfo;
        PLAYERDATABASE = new PlayerDatabase(playerInfo);
        view = new PlayerInforView(mainCtrl, this);
        storyPanel = new StoryPanel(mainCtrl, this);
    }

    public void savePlayerInfo()
    {
        PLAYERDATABASE.inputDataRow();
    }
    
    //Getters and setters
    public PlayerInforView getView() 
    {
        return view;
    }

    public PlayerInfo getPlayerInfo() 
    {
        return playerInfo;
    }
    
    public StoryPanel getStoryPanel()
    {
        return storyPanel;
    }
    
    public void setPlayerName(String name)
    {
        playerInfo.setName(name);
    }
    
    public void setPlayerAge(int age)
    {
        playerInfo.setAge(age);
    }
    
    public void setPlayerGender(char gender)
    {
        playerInfo.setGender(gender);
    }
}
