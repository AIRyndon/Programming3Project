package gui_project.ModelController;

import gui_project.View.RoomWifeView;

public class RoomWifeController extends BaseRoomController
{
    private final RoomWifeView view;
  
    public RoomWifeController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl,
            NPCController daughterCtrl)
    {
        view = new RoomWifeView(mainCtrl, 
                detectiveCtrl, this);
    }

    public RoomWifeView getView()
    {
        return view;
    }
}
