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
public class RoomGroundController extends BaseRoomController
{

    private final RoomGroundView view;

    public RoomGroundController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl,
            LockedAreaController lockedAreaCtrl)
    {
        view = new RoomGroundView(mainCtrl,
                detectiveCtrl, this,
                keyPasswordCtrl, lockedAreaCtrl);
    }

    public RoomGroundView getView()
    {
        return view;
    }
}
