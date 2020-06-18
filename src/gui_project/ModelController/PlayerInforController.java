package gui_project.ModelController;

import gui_database.PlayerDatabase;
import gui_project.View.PlayerInforView;
import gui_project.View.StoryPanel;

public class PlayerInforController
{
    private static PlayerDatabase playerDatabase = new PlayerDatabase();
    private MainController mainCtrl;
    private PlayerInfor playerInfor;
    private PlayerInforView view;
    private StoryPanel storyPanel;
    
    PlayerInforController(MainController mainCtrl, PlayerInfor playerInfor) 
    {
        this.mainCtrl = mainCtrl;
        this.playerInfor = playerInfor;
        System.out.println(playerInfor.getName());
        view = new PlayerInforView(mainCtrl, this);
        storyPanel = new StoryPanel(mainCtrl, this);
    }

    public void savePlayerInfo()
    {
        playerDatabase.inputDataRow(playerInfor.getName(), playerInfor.getAge(), 
                playerInfor.getGender());
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
