package guimoving;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DogHouse extends NPC 
{
    public static boolean isLock = false;

    public DogHouse(int locationX, int locationY) 
    {
        super(locationX, locationY);
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawString("DOG HOUSE", this.locationX, this.locationY + 15);
        graphics2D.draw(getBound());
        
        if (!isLock)
        {
            graphics2D.drawString("It is locked!", this.locationX, this.locationY + 50);
        } 
        else 
        {
            graphics2D.drawString("Door unlock!", this.locationX, this.locationY + 50);
        }
    }

    public Rectangle getBound()
    {
        return new Rectangle(this.locationX - 4, this.locationY - 12, 84, 50);
    }
}
