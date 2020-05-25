/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomTwoView;
import gui_project.View.RoomView;

/**
 *
 * @author Angelo
 */
public class RoomTwoController
{
    private RoomTwoView view;

    public RoomTwoController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl)
    {
        view = new RoomTwoView(mainCtrl,
                detectiveCtrl, 
                wifeCtrl,this);
    }

    public RoomTwoView getView()
    {
        return view;
    }
}
