/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomButlerView;

/**
 *
 * @author Angelo
 */
//Pass the detective controller to other controllers cuz we want to manage the detective
//in every view
//We pass the MainController so we can change panels
public class RoomButlerController extends BaseRoomController
{   
    private final RoomButlerView view;
  
    public RoomButlerController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController assistantCtrl)
    {
        view = new RoomButlerView(mainCtrl, 
                detectiveCtrl, 
                assistantCtrl, this);
    }

    public RoomButlerView getView()
    {
        return view;
    }
}
