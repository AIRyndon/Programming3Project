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
        NPCController wifeCtrl = new NPCController(new NPC("Wife"));

        /*We setup the rooms here - the room names will be Ground, House, Etc -
        we can create the panels using the GUI builder now because 
        we have some code separation, 
        we just add the panel here at project start
         */
        RoomController roomCtrl = new RoomController(this, detectiveCtrl, butlerCtrl);
        RoomTwoController roomTwoCtrl = new RoomTwoController(this, detectiveCtrl, wifeCtrl);

        view.addPanel(roomCtrl.getView(), roomCtrl.getView().getName());
        view.addPanel(roomTwoCtrl.getView(), roomTwoCtrl.getView().getName());

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
