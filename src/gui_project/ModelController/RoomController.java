/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.RoomView;
import java.awt.Graphics2D;

/**
 *
 * @author Angelo
 */
//Pass the detective controller to other controllers cuz we want to manage the detective
//in every view
//We pass the MainController so we can change panels
public class RoomController
{   
    private final RoomView view;
  
    public RoomController(MainController mainCtrl, 
            DetectiveController detectiveCtrl,
            NPCController butlerCtrl)
    {
        view = new RoomView(mainCtrl, 
                detectiveCtrl, 
                butlerCtrl, this);
    }

    public RoomView getView()
    {
        return view;
    }
}
