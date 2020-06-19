package gui_project.View;

import gui_project.ModelController.Detective;
import gui_project.ModelController.MainController;
import gui_project.ModelController.PlayerInfo;
import gui_project.ModelController.PlayerInfoController;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoryPanelTest 
{   
    public StoryPanelTest() 
    {
    
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
        
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
        
    }
    
    @Before
    public void setUp() 
    {
        
    }
    
    @After
    public void tearDown()
    {
        
    }

    /**
     * Test of update method, of class StoryPanel. 
     * 
     * Check if playerNameLabel of StoryPanel will change when playerInforCtrl is changed.
     */
    @Test
    public void testUpdate_UpdateShouldBeTriggered_AfterPlayerInforChanged() throws SQLException
    {
        System.out.println("Update player name");
        //Arrange
        MainController mainCtrl = new MainController(new Detective());
        PlayerInfo baseModel = new PlayerInfo();
        PlayerInfoController playerInforCtrl = new PlayerInfoController(mainCtrl, baseModel);
        StoryPanel baseObserver = new StoryPanel(mainCtrl, playerInforCtrl);
        
        //Act
        playerInforCtrl.setPlayerName("Tester");      
        
        //Assert
        assertEquals("Tester", baseObserver.getPlayerName());
    }
}
