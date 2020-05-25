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

        DetectiveController detectiveCtrl = new DetectiveController(detective);
        NPCController butlerCtrl = new NPCController(new NPC("Butler"));
        NPCController wifeCtrl = new NPCController(new NPC("Wife"));
        
        RoomController roomCtrl = new RoomController(this,detectiveCtrl,butlerCtrl);
        RoomTwoController roomTwoCtrl = new RoomTwoController(this,detectiveCtrl,wifeCtrl);
        
        view.addPanel(roomCtrl.getView(), roomCtrl.getView().getName());
        view.addPanel(roomTwoCtrl.getView(), roomTwoCtrl.getView().getName());
        
        view.renderView();
    }

    public void showPanel(String panelName)
    {
        view.showPanel(panelName);
    }
}
