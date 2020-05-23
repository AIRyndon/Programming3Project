/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.DetectiveView;

/**
 *
 * @author Angelo
 */
public class DetectiveController extends NPCController
{
    private final Detective detective;
    private final DetectiveView view;

    public DetectiveController(Detective detective)
    {
        super(detective);
        this.detective = detective;
        view = new DetectiveView(detective, this);
    }

    public void setVelX(int velX)
    {
        detective.setVelX(velX);
    }

    public void setVelY(int velY)
    {
        detective.setVelY(velY);
    }
}
