/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.*;

/**
 *
 * @author Angelo
 */
public class MainController
{
    private final MainView view;
    private final Detective detective;
    private DetectiveController detectiveCtrl;
    private RoomGroundController groundCtrl;
    private RoomHouseController houseCtrl;
    private RoomMaidController maidRoomCtrl;
    private RoomButlerController butlerRoomCtrl;
    private RoomWifeController wifeRoomCtrl;
    private RoomWorkingController workingRoomCtrl;
    private NPCController butler, maid, assistant, wife, daughter, victim;
    private HintController knife, gloves;
    private ItemBlockController houseArea, maidRoomWall, wifeRoomWall, butlerRoomWall,
            officeRoomWall, bed;
    private LockedAreaController dogHouseLock, officeLock;

    public MainController(Detective detective)
    {
        this.detective = detective;
        view = new MainView();

        setUpNPCController();
        setUpHint();
        setUpRoomController();
        setUpItemBlockController();
        setUpLockedAreaController();
        addItemBlockToRoom();
        
        addAllPanels();
        showPanel("Ground");
        view.renderView();
    }
    
    public void addItemBlockToRoom()
    {
        groundCtrl.addItemBlock(dogHouseLock);
        groundCtrl.addItemBlock(butler);
        groundCtrl.addItemBlock(knife);
        groundCtrl.addItemBlock(houseArea);

        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(officeRoomWall);

        butlerRoomCtrl.addItemBlock(assistant);
        butlerRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(maid);
        maidRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(gloves);

        wifeRoomCtrl.addItemBlock(wife);
        wifeRoomCtrl.addItemBlock(daughter);
        wifeRoomCtrl.addItemBlock(bed);
        
        workingRoomCtrl.addItemBlock(victim);
        workingRoomCtrl.addItemBlock(officeLock);
    }
    
    public void addAllPanels()
    {
        view.addPanel(houseCtrl.getView(), houseCtrl.getView().getName());
        view.addPanel(maidRoomCtrl.getView(), maidRoomCtrl.getView().getName());
        view.addPanel(butlerRoomCtrl.getView(), butlerRoomCtrl.getView().getName());
        view.addPanel(wifeRoomCtrl.getView(), wifeRoomCtrl.getView().getName());
        view.addPanel(workingRoomCtrl.getView(), workingRoomCtrl.getView().getName());
        view.addPanel(groundCtrl.getView(), groundCtrl.getView().getName());
        view.addPanel(dogHouseLock.getKeyPanel().getView(), dogHouseLock.getLockedArea().getName());
        view.addPanel(officeLock.getKeyPanel().getView(), officeLock.getLockedArea().getName());
    }
    
    public void assignHintToNPC()
    {
        butler.setOwnedHint(knife);
        assistant.setOwnedHint(gloves);
    }
    
    public void setUpHint()
    {
        knife = new HintController(new Hint("Knife", 150, 150, 10, 10));
        gloves = new HintController(new Hint("Gloves",250,250,10,10));
        
        assignHintToNPC();
    }
    
    public void setUpItemBlockController()
    {
        houseArea = new ItemBlockController(new ItemBlock(276, 83, 200, 200));
        maidRoomWall = new ItemBlockController(new ItemBlock(10, 15, 200, 110));
        wifeRoomWall = new ItemBlockController(new ItemBlock(10, 125, 200, 110));
        butlerRoomWall = new ItemBlockController(new ItemBlock(10, 235, 200, 110));
        officeRoomWall = new ItemBlockController(new ItemBlock(10, 345, 450, 140));
        bed = new ItemBlockController(new ItemBlock(10, 15, 100, 100));
    }
    
    public void setUpLockedAreaController()
    {
        dogHouseLock = new LockedAreaController(this, new LockedArea(630, 15, 150, 100, "DogHouseLock"));
        officeLock = new LockedAreaController(this, new LockedArea(10, 15, 200, 469, "OfficeLock"));
    }
    
    public void setUpNPCController()
    {
        detectiveCtrl = new DetectiveController(detective);
        butler = new NPCController(new NPC("Butler", "B", 100, 100, 20, 20));
        maid = new NPCController(new NPC("Maid", "M", 200, 200, 20, 20));
        assistant = new NPCController(new NPC("Assistant", "A", 240, 240, 20, 20));
        wife = new NPCController(new NPC("Wife", "W", 230, 230, 20, 20));
        daughter = new NPCController(new NPC("Daughter", "D", 150, 150, 20, 20));
        victim = new NPCController(new NPC("Victim", "V", 300, 300, 20, 20));
    }
    
    public void setUpRoomController()
    {
        groundCtrl = new RoomGroundController(this, detectiveCtrl, butler);
        houseCtrl = new RoomHouseController(this, detectiveCtrl, wife);
        maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maid);
        butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, assistant);
        wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl, wife, daughter);
        workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, victim);        
    }

    public void updateConversationLevel()
    {
        if (butler.hasTalkedWithPlayer()
                && wife.hasTalkedWithPlayer()
                && daughter.hasTalkedWithPlayer()
                && maid.hasTalkedWithPlayer())
        {
            assistant.unlockLines();
            NPC.conversationLevel = 2;
        } else if (NPC.conversationLevel == 2)
        {
            wife.unlockLines();
            NPC.conversationLevel = 3;
        } else if (NPC.conversationLevel == 3)
        {
            maid.unlockLines();
            daughter.unlockLines();
        }
    }

    /**
     * This is what's ultimately called in the other panels to change what panel
     * to show - that is why we pass the MainController to the other controllers
     *
     * @param panelName
     */
    public void showPanel(String panelName)
    {
        view.showPanel(panelName);
    }
}
