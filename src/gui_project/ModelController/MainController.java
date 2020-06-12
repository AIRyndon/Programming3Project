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
    private NPCController butlerCtrl, maidCtrl, assistantCtrl, wifeCtrl, daughterCtrl, victimCtrl;
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
        groundCtrl.addItemBlock(butlerCtrl);
        groundCtrl.addItemBlock(knife);
        groundCtrl.addItemBlock(houseArea);

        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(officeRoomWall);

        butlerRoomCtrl.addItemBlock(assistantCtrl);
        butlerRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(maidCtrl);
        maidRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(gloves);

        wifeRoomCtrl.addItemBlock(wifeCtrl);
        wifeRoomCtrl.addItemBlock(daughterCtrl);
        wifeRoomCtrl.addItemBlock(bed);
        
        workingRoomCtrl.addItemBlock(victimCtrl);
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
        view.addPanel(dogHouseLock.getPasswordInputController().getView(), dogHouseLock.getLockedArea().getName());
        view.addPanel(officeLock.getPasswordInputController().getView(), officeLock.getLockedArea().getName());
    }
    
    public void assignHintToNPC()
    {
        butlerCtrl.getNPC().setOwnedHint(knife);
        assistantCtrl.getNPC().setOwnedHint(gloves);
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
        butlerCtrl = new NPCController(new NPC("Butler", "B", 100, 100, 20, 20));
        maidCtrl = new NPCController(new NPC("Maid", "M", 200, 200, 20, 20));
        assistantCtrl = new NPCController(new NPC("Assistant", "A", 240, 240, 20, 20));
        wifeCtrl = new NPCController(new NPC("Wife", "W", 230, 230, 20, 20));
        daughterCtrl = new NPCController(new NPC("Daughter", "D", 150, 150, 20, 20));
        victimCtrl = new NPCController(new NPC("Victim", "V", 300, 300, 20, 20));
    }
    
    public void setUpRoomController()
    {
        groundCtrl = new RoomGroundController(this, detectiveCtrl, butlerCtrl);
        houseCtrl = new RoomHouseController(this, detectiveCtrl, wifeCtrl);
        maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maidCtrl);
        butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, assistantCtrl);
        wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl, wifeCtrl, daughterCtrl);
        workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, victimCtrl);        
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
