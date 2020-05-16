package guimoving;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Hint extends NPC 
{
    public Hint(int locationX, int locationY)
    {
        super(locationX, locationY);
    }

    public void draw(Graphics2D graphics2D) 
    {
        graphics2D.drawString("!", this.locationX, this.locationY);
        graphics2D.draw(getBound());
        graphics2D.drawString("Key", this.locationX + 5, this.locationY + 25);
    }

    public Rectangle getBound()
    {
        return new Rectangle(this.locationX - 4, this.locationY - 12, 15, 15);
    }
}
