/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.DetectiveView;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Angelo
 */
public class DetectiveController
{

    private final Detective detective;
    private final DetectiveView view;

    public DetectiveController(Detective detective)
    {
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

    public void setLocationX(int locationX)
    {
        detective.setLocationX(locationX);
    }

    public void setLocationY(int locationY)
    {
        detective.setLocationY(locationY);
    }

    public void draw(Graphics2D graphics2D)
    {
        view.draw(graphics2D);
    }

    public void keyPressed(KeyEvent e)
    {
        view.keyPressed(e);
    }

    public void keyReleased(KeyEvent e)
    {
        view.keyReleased(e);
    }
}
