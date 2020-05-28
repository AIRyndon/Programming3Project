/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomHouseView;
import gui_project.View.RoomGroundView;

/**
 *
 * @author Angelo
 */
public class RoomHouseController
{
    private RoomHouseView view;

    public RoomHouseController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl)
    {
        view = new RoomHouseView(mainCtrl,
                detectiveCtrl, 
                wifeCtrl,this);
    }

    public RoomHouseView getView()
    {
        return view;
    }
}
