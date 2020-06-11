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
        getDetective().setVelX(velX);
    }

    public void setVelY(int velY)
    {
        getDetective().setVelY(velY);
    }

    public void setLocationX(int locationX)
    {
        getDetective().setLocationX(locationX);
    }

    public void setLocationY(int locationY)
    {
        getDetective().setLocationY(locationY);
    }
    
    public void setGroundHouseLocationX(int previousLocationX) 
    {
        getDetective().setGroundHouseLocationX(previousLocationX);
    }
    
    public void setGroundHouseLocationY(int previousLocationY) 
    {
        getDetective().setGroundHouseLocationY(previousLocationY);
    }
    
    public void setRoomHouseLocationX(int previousLocationX) 
    {
        getDetective().setRoomHouseLocationX(previousLocationX);
    }
    
    public void setRoomHouseLocationY(int previousLocationY) 
    {
        getDetective().setRoomHouseLocationY(previousLocationY);
    }
    
    public Detective getDetective() 
    {
        return detective;
    }
    
    public DetectiveView getView() 
    {
        return view;
    }

    public void draw(Graphics2D graphics2D)
    {
        getView().draw(graphics2D);
    }
    
    public void updateGroundHouseLocation()
    {
        int previousX = getDetective().getGroundHouseLocationX();
        int previousY = getDetective().getGroundHouseLocationY();
        setGroundHouseLocationX(getDetective().getLocationX());
        setGroundHouseLocationY(getDetective().getLocationY());
        setLocationX(previousX);
        setLocationY(previousY + 15);
    }

    public void saveHouseLocation(int newLocationX, int newLocationY)
    {
        setRoomHouseLocationX(getDetective().getLocationX() + 5);
        setRoomHouseLocationY(getDetective().getLocationY() - 5);
        setLocationX(newLocationX);
        setLocationY(newLocationY);
    }
    
    public void keyPressed(int keyCode, Rectangle itemBlock, 
            boolean houseCollision, boolean roomBoundaryCollision)
    {
        switch (keyCode)
        {
            case KeyEvent.VK_W:
            {
                if((houseCollision && view.getBound().getMinY() >= itemBlock.getMinY()) ||
                    (roomBoundaryCollision && view.getBound().getMinY() <= itemBlock.getMinY()))
                {
                    setVelY(0);
                }
                else 
                    setVelY(-(detective.getSpeed()));
                
                break;
            }
            case KeyEvent.VK_S:
            {
                if((houseCollision && view.getBound().getMaxY() <= itemBlock.getMaxY()) ||
                    (roomBoundaryCollision && view.getBound().getMaxY() >= itemBlock.getMaxY()))
                {
                    setVelY(0);
                }
                else 
                    setVelY(detective.getSpeed());
                
                break;
            }
            case KeyEvent.VK_A:
            {
                if((houseCollision && view.getBound().getMinX() >= itemBlock.getMinX()) ||
                    (roomBoundaryCollision && view.getBound().getMinX() <= itemBlock.getMinX()))
                {
                    setVelX(0);
                }
                else
                    setVelX(-(detective.getSpeed()));
                
                break;
            }
            case KeyEvent.VK_D:
            {
                if((houseCollision && view.getBound().getMaxX() <= itemBlock.getMaxX()) ||
                    (roomBoundaryCollision && view.getBound().getMaxX() >= itemBlock.getMaxX()))
                {
                    setVelX(0);
                }
                else
                    setVelX(getDetective().getSpeed());
                
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
}
