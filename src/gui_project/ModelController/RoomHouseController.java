package gui_project.ModelController;

import gui_project.View.RoomHouseView;
import java.util.ArrayList;

public class RoomHouseController extends BaseRoomController
{

    private final RoomHouseView view;

    public RoomHouseController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl,
            ArrayList<ItemBlockController> itemBlockCtrl)
    {
        super(itemBlockCtrl);
        view = new RoomHouseView(mainCtrl,
                detectiveCtrl, this,
                keyPasswordCtrl);
    }

    public RoomHouseView getView()
    {
        return view;
    }
}
