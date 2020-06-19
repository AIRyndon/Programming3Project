package gui_project.ModelController;

import gui_project.View.RoomWorkingView;

public class RoomWorkingController extends BaseRoomController
{

    private final RoomWorkingView view;

    public RoomWorkingController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl,
            LockedAreaController lockedAreaCtrl)
    {
        view = new RoomWorkingView(mainCtrl,
                detectiveCtrl, this,
                keyPasswordCtrl, lockedAreaCtrl);
    }

    public RoomWorkingView getView()
    {
        return view;
    }
}
