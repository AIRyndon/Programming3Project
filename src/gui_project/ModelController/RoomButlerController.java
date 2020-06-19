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
public class RoomButlerController extends BaseRoomController
{

    private final RoomButlerView view;

    public RoomButlerController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl)
    {
        view = new RoomButlerView(mainCtrl,
                detectiveCtrl, this, keyPasswordCtrl);
    }

    public RoomButlerView getView()
    {
        return view;
    }
}
