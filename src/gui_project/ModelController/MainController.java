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

        /*We setup the rooms here - the room names will be Ground, House, Etc -
        we can create the panels using the GUI builder now because 
        we have some code separation, 
        we just add the panel here at project start
         */
        RoomGroundController groundCtrl = new RoomGroundController(this, detectiveCtrl, butlerCtrl);
        RoomHouseController houseCtrl = new RoomHouseController(this, detectiveCtrl, wifeCtrl);
        RoomMaidController maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maidCtrl);
        RoomButlerController butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, assistantCtrl);
        RoomWifeController wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl, wifeCtrl);
        RoomWorkingController workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, wifeCtrl);
        

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
