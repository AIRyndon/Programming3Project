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

    public MainController(Detective detective)
    {
        view = new MainView();

        //we setup our NPCs here
        DetectiveController detectiveCtrl = new DetectiveController(detective);
        NPCController butlerCtrl = new NPCController(new NPC("Butler"));
        NPCController maidCtrl = new NPCController(new NPC("Maid"));
        NPCController assistantCtrl = new NPCController(new NPC("Assistant"));
        NPCController wifeCtrl = new NPCController(new NPC("Wife"));
        NPCController daughterCtrl = new NPCController(new NPC("Daughter"));
        NPCController victimCtrl = new NPCController(new NPC("Victim"));
        
        /*We setup the rooms here - the room names will be Ground, House, Etc -
        we can create the panels using the GUI builder now because 
        we have some code separation, 
        we just add the panel here at project start
         */
        RoomGroundController groundCtrl = new RoomGroundController(this, detectiveCtrl, butlerCtrl);
        RoomHouseController houseCtrl = new RoomHouseController(this, detectiveCtrl, wifeCtrl);
        RoomMaidController maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maidCtrl);
        RoomButlerController butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, assistantCtrl);
        RoomWifeController wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl, wifeCtrl, daughterCtrl);
        RoomWorkingController workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, victimCtrl);
        
        //Setup ItemBlocks (eg. DogHouse, room walls, etc.) to advoid player access
        ItemBlockController dogHouse = new ItemBlockController(new ItemBlock(630, 15, 150, 100));
        ItemBlockController maidRoomWall = new ItemBlockController(new ItemBlock(10, 15, 200, 110));
        ItemBlockController wifeRoomWall = new ItemBlockController(new ItemBlock(10, 125, 200, 110));
        ItemBlockController butlerRoomWall = new ItemBlockController(new ItemBlock(10, 235, 200, 110));
        ItemBlockController workringRoomWall = new ItemBlockController(new ItemBlock(10, 345, 450, 140));
        ItemBlockController bed = new ItemBlockController(new ItemBlock(10, 15, 100, 100));
        ItemBlockController lockedArea = new ItemBlockController(new ItemBlock(10, 15, 200, 100));
        
        groundCtrl.addItemBlock(dogHouse);
        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(workringRoomWall);
        maidRoomCtrl.addItemBlock(bed);
        butlerRoomCtrl.addItemBlock(bed);
        wifeRoomCtrl.addItemBlock(bed);
        workingRoomCtrl.addItemBlock(lockedArea);
        
        view.addPanel(groundCtrl.getView(), groundCtrl.getView().getName());
        view.addPanel(houseCtrl.getView(), houseCtrl.getView().getName());
        view.addPanel(maidRoomCtrl.getView(), maidRoomCtrl.getView().getName());
        view.addPanel(butlerRoomCtrl.getView(), butlerRoomCtrl.getView().getName());
        view.addPanel(wifeRoomCtrl.getView(), wifeRoomCtrl.getView().getName());
        view.addPanel(workingRoomCtrl.getView(), workingRoomCtrl.getView().getName());

        view.renderView();
    }

    /**
     * This is what's ultimately called in the other panels to change what panel
     * to show - that is why we pass the MainController to the other
     * controllers
     * @param panelName 
     */
    public void showPanel(String panelName)
    {
        view.showPanel(panelName);
    }
}
