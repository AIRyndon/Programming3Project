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
        NPCController butlerCtrl = new NPCController(new NPC("Butler", "B", 100, 100, 20, 20));
        NPCController maidCtrl = new NPCController(new NPC("Maid", "M", 200, 200, 20, 20));
        NPCController assistantCtrl = new NPCController(new NPC("Assistant", "A", 240, 240, 20, 20));
        NPCController wifeCtrl = new NPCController(new NPC("Wife", "W", 230, 230, 20, 20));
        NPCController daughterCtrl = new NPCController(new NPC("Daughter", "D", 150, 150, 20, 20));
        NPCController victimCtrl = new NPCController(new NPC("Victim", "V", 300, 300, 20, 20));

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

        //Setup ItemBlocks (eg. DogHouse, room walls, etc.) to advoid player collision
        ItemBlockController dogHouse = new ItemBlockController(new ItemBlock("DogHouse", 630, 15, 150, 100));
        ItemBlockController houseArea = new ItemBlockController(new ItemBlock("HouseArea", 276, 83, 200, 200));
        ItemBlockController maidRoomWall = new ItemBlockController(new ItemBlock("MaidWall", 10, 15, 200, 110));
        ItemBlockController wifeRoomWall = new ItemBlockController(new ItemBlock("WifeWall", 10, 125, 200, 110));
        ItemBlockController butlerRoomWall = new ItemBlockController(new ItemBlock("ButlerWall", 10, 235, 200, 110));
        ItemBlockController officeRoomWall = new ItemBlockController(new ItemBlock("OfficeWall", 10, 345, 450, 140));
        ItemBlockController lockedArea = new ItemBlockController(new ItemBlock("LockedArea", 10, 15, 200, 100));
        ItemBlockController bed = new ItemBlockController(new ItemBlock("Bed", 10, 15, 100, 100));

        groundCtrl.addItemBlock(dogHouse);
        groundCtrl.addItemBlock(butlerCtrl);
        groundCtrl.addItemBlock(houseArea);

        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(officeRoomWall);

        butlerRoomCtrl.addItemBlock(assistantCtrl);
        butlerRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(maidCtrl);
        maidRoomCtrl.addItemBlock(bed);

        wifeRoomCtrl.addItemBlock(wifeCtrl);
        wifeRoomCtrl.addItemBlock(daughterCtrl);
        wifeRoomCtrl.addItemBlock(bed);
        workingRoomCtrl.addItemBlock(victimCtrl);
        workingRoomCtrl.addItemBlock(lockedArea);

        view.addPanel(houseCtrl.getView(), houseCtrl.getView().getName());
        view.addPanel(maidRoomCtrl.getView(), maidRoomCtrl.getView().getName());
        view.addPanel(butlerRoomCtrl.getView(), butlerRoomCtrl.getView().getName());
        view.addPanel(wifeRoomCtrl.getView(), wifeRoomCtrl.getView().getName());
        view.addPanel(workingRoomCtrl.getView(), workingRoomCtrl.getView().getName());
        view.addPanel(groundCtrl.getView(), groundCtrl.getView().getName());

        showPanel("Ground");
        view.renderView();
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
