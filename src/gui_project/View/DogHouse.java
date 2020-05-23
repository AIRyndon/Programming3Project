package gui_project.View;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DogHouse
{
    private boolean locked = false;
    private int locationY;
    private int locationX;

    public DogHouse(int locationX, int locationY) 
    {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawString("DOG HOUSE", locationX, locationY + 15);
        graphics2D.draw(getBound());
        
        if (!locked)
        {
            graphics2D.drawString("It is locked!", locationX, locationY + 50);
        } 
        else 
        {
            graphics2D.drawString("Door unlock!", locationX, locationY + 50);
        }
    }

    public Rectangle getBound()
    {
        return new Rectangle(locationX - 4, locationY - 12, 84, 50);
    }

    /**
     * @return the isLock
     */
    public boolean IsLocked()
    {
        return locked;
    }

    /**
     * @param isLock the isLock to set
     */
    public void setLock(boolean isLock)
    {
        this.locked = isLock;
    }
}
