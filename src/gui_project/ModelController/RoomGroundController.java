package gui_project.ModelController;

import gui_project.View.RoomGroundView;

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
