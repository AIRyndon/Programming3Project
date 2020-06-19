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