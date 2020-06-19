package gui_project.ModelController;

import gui_project.View.RoomGroundView;
import java.util.ArrayList;

public class RoomGroundController extends BaseRoomController
{

    private final RoomGroundView view;

    public RoomGroundController(MainController mainCtrl,
            DetectiveController detectiveCtrl,
            KeyPasswordController keyPasswordCtrl,
            LockedAreaController lockedAreaCtrl,
            ArrayList<ItemBlockController> itemBlockCtrl)
    {
        super(itemBlockCtrl);
        view = new RoomGroundView(mainCtrl,
                detectiveCtrl, this,
                keyPasswordCtrl, lockedAreaCtrl);
    }

    public RoomGroundView getView()
    {
        return view;
    }
}
