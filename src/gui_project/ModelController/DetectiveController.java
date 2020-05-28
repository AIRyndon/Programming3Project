/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.DetectiveView;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
        getView().draw(graphics2D);
    }

    public void keyPressed(KeyEvent e, Rectangle itemBlock, boolean validMove)
    {
        int key = e.getKeyCode();

        switch (key)
        {
            case KeyEvent.VK_W:
            {
                if(!validMove && view.getBound().getMinY() > itemBlock.getMinY())
                {
                    System.out.println(view.getBound().getMinY());
                    System.out.println(itemBlock.getMaxY());
                    System.out.println("W");
                    setVelY(0);
                }
                else 
                    setVelY(-(detective.getSpeed()));
                
                break;
            }
            case KeyEvent.VK_S:
            {
                if(!validMove && view.getBound().getMaxY() < itemBlock.getMaxY())
                {
                    System.out.println(view.getBound().getMaxY());
                    System.out.println(itemBlock.getMinY());
                    System.out.println("S");
                    setVelY(0);
                }
                else 
                    setVelY(detective.getSpeed());
                
                break;
            }
            case KeyEvent.VK_A:
            {
                if(!validMove && view.getBound().getMinX() > itemBlock.getMinX())
                {
                    System.out.println(view.getBound().getMinX());
                    System.out.println(itemBlock.getMaxX());
                    System.out.println("A");
                    setVelY(0);
                }
                else
                    setVelX(-(detective.getSpeed()));
                
                break;
            }
            case KeyEvent.VK_D:
            {
                if(!validMove && view.getBound().getMaxX() < itemBlock.getMaxX())
                {
                    System.out.println(view.getBound().getMaxX());
                    System.out.println(itemBlock.getMinX());
                    System.out.println("D");
                    setVelY(0);
                }
                else
                    setVelX(detective.getSpeed());
                
                break;
            }
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        setVelY(0);
        setVelX(0);
    }

    public DetectiveView getView() 
    {
        return view;
    }
}
