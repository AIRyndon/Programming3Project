/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomWorkingView;


/**
 *
 * @author pc
 */
public class RoomWorkingController extends BaseRoomController
{
    private final RoomWorkingView view;
  
    public RoomWorkingController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl)
    {
        view = new RoomWorkingView(mainCtrl, 
                detectiveCtrl,this);
    }

    public RoomWorkingView getView()
    {
        return view;
    }
}
