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
        NPCController butlerCtrl = new NPCController(new NPC("B",100,100));
        NPCController maidCtrl = new NPCController(new NPC("M",200,200));
        NPCController assistantCtrl = new NPCController(new NPC("A",240,240));
        NPCController wifeCtrl = new NPCController(new NPC("W",230,230));
        NPCController daughterCtrl = new NPCController(new NPC("D",150,150));
        NPCController victimCtrl = new NPCController(new NPC("V",300,300));
        
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
        NPCView butlerView = butlerCtrl.getView();
        ItemBlockController butlerBlock = new ItemBlockController(new ItemBlock(butlerView.getBound().x,
                                                                        butlerView.getBound().y,
                                                                        butlerView.getBound().width,
                                                                        butlerView.getBound().height));
        ItemBlockController dogHouse = new ItemBlockController(new ItemBlock(630, 15, 150, 100));           
        ItemBlockController houseArea = new ItemBlockController(new ItemBlock(276,83,200,200));
            
        groundCtrl.addItemBlock(dogHouse);
        groundCtrl.addItemBlock(butlerBlock);
        groundCtrl.addItemBlock(houseArea);
        
        ItemBlockController maidRoomWall = new ItemBlockController(new ItemBlock(10, 15, 200, 110));
        ItemBlockController wifeRoomWall = new ItemBlockController(new ItemBlock(10, 125, 200, 110));
        ItemBlockController butlerRoomWall = new ItemBlockController(new ItemBlock(10, 235, 200, 110));
        ItemBlockController workringRoomWall = new ItemBlockController(new ItemBlock(10, 345, 450, 140));
        
        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(workringRoomWall);
                     
        NPCView assistantView = assistantCtrl.getView();
        ItemBlockController assistantBlock = new ItemBlockController(new ItemBlock(assistantView.getBound().x,
                                                                        assistantView.getBound().y,
                                                                        assistantView.getBound().width,
                                                                        assistantView.getBound().height));
        butlerRoomCtrl.addItemBlock(assistantBlock);
        
        NPCView maidView = maidCtrl.getView();
        ItemBlockController maidBlock = new ItemBlockController(new ItemBlock(maidView.getBound().x,
                                                                        maidView.getBound().y,
                                                                        maidView.getBound().width,
                                                                        maidView.getBound().height));
        maidRoomCtrl.addItemBlock(maidBlock);
        
        NPCView wifeView = wifeCtrl.getView();
        NPCView daughterView = daughterCtrl.getView();
        ItemBlockController wifeBlock = new ItemBlockController(new ItemBlock(wifeView.getBound().x,
                                                                        wifeView.getBound().y,
                                                                        wifeView.getBound().width,
                                                                        wifeView.getBound().height));       
        ItemBlockController daughterBlock = new ItemBlockController(new ItemBlock(daughterView.getBound().x,
                                                                        daughterView.getBound().y,
                                                                        daughterView.getBound().width,
                                                                        daughterView.getBound().height));
        wifeRoomCtrl.addItemBlock(wifeBlock);
        wifeRoomCtrl.addItemBlock(daughterBlock);
        
        NPCView victimView = victimCtrl.getView();
        ItemBlockController victimBlock = new ItemBlockController(new ItemBlock(victimView.getBound().x,
                                                                        victimView.getBound().y,
                                                                        victimView.getBound().width,
                                                                        victimView.getBound().height));
        ItemBlockController lockedArea = new ItemBlockController(new ItemBlock(10, 15, 200, 100));
        workingRoomCtrl.addItemBlock(lockedArea);
        workingRoomCtrl.addItemBlock(victimBlock);
        
        ItemBlockController bed = new ItemBlockController(new ItemBlock(10, 15, 100, 100));       
        maidRoomCtrl.addItemBlock(bed);
        butlerRoomCtrl.addItemBlock(bed);
        wifeRoomCtrl.addItemBlock(bed);
      
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
     * to show - that is why we pass the MainController to the other
     * controllers
     * @param panelName 
     */
    public void showPanel(String panelName)
    {
        view.showPanel(panelName);
    }
}