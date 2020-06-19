package gui_project.ModelController;

import gui_project.View.RoomWifeView;
import java.util.ArrayList;

public class RoomWifeController extends BaseRoomController
{

    private final RoomWifeView view;

    public RoomWifeController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            ArrayList<ItemBlockController> itemBlockCtrl)
    {
        super(itemBlockCtrl);
        view = new RoomWifeView(mainCtrl,
                detectiveCtrl, this);
    }

    public RoomWifeView getView()
    {
        return view;
    }
}
