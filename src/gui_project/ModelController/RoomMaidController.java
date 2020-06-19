/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomMaidView;

public class RoomMaidController extends BaseRoomController
{
    private RoomMaidView view;

    public RoomMaidController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl)
    {
        view = new RoomMaidView(mainCtrl,
                detectiveCtrl,this);
    }

    public RoomMaidView getView()
    {
        return view;
    }
}