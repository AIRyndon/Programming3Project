package guimoving;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Butler extends NPC 
{
    public static boolean isTalk;

    public Butler(int locationX, int locationY)
    {
        super(locationX, locationY);
    }

    public void update()
    {
        
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawString("B", this.locationX, this.locationY);
        graphics2D.draw(getBound());
        
        if (isTalk)
        {
            graphics2D.drawString("I can talk", this.locationX + 20, this.locationY);
        }
    }

    public Rectangle getBound() 
    {
        return new Rectangle(this.locationX - 4, this.locationY - 12, 15, 15);
    }
}
