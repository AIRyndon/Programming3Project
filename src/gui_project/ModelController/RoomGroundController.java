/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomGroundView;

/**
 *
 * @author Angelo
 */
//Pass the detective controller to other controllers cuz we want to manage the detective
//in every view
//We pass the MainController so we can change panels
public class RoomGroundController
{   
    private final RoomGroundView view;
  
    public RoomGroundController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController butlerCtrl)
    {
        view = new RoomGroundView(mainCtrl, 
                detectiveCtrl, 
                butlerCtrl, this);
        
        
    }

    public RoomGroundView getView()
    {
        return view;
    }
}
