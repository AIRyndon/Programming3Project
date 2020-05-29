/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomWifeView;

/**
 *
 * @author pc
 */
public class RoomWifeController extends ControlItemBlocks
{
    private final RoomWifeView view;
  
    public RoomWifeController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController wifeCtrl,  NPCController daughterCtrl)
    {
        view = new RoomWifeView(mainCtrl, 
                detectiveCtrl, 
                wifeCtrl, daughterCtrl, this);
    }

    public RoomWifeView getView()
    {
        return view;
    }
}
