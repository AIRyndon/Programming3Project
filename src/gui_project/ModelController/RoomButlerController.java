package gui_project.ModelController;

import gui_project.View.RoomButlerView;

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
