package gui_project.ModelController;

import gui_project.View.RoomMaidView;
import java.util.ArrayList;

public class RoomMaidController extends BaseRoomController
{
    private RoomMaidView view;

    public RoomMaidController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            ArrayList<ItemBlockController> itemBlockCtrl)
    {
        super(itemBlockCtrl);
        view = new RoomMaidView(mainCtrl,
                detectiveCtrl,this);
    }

    public RoomMaidView getView()
    {
        return view;
    }
}