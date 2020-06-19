package gui_project.ModelController;

import gui_project.View.RoomHouseView;

public class RoomHouseController extends BaseRoomController
{

    private final RoomHouseView view;

    public RoomHouseController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl)
    {
        view = new RoomHouseView(mainCtrl,
                detectiveCtrl, this,
                keyPasswordCtrl);
    }

    public RoomHouseView getView()
    {
        return view;
    }
}
