package gui_project.View;

import gui_project.ModelController.ItemBlock;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ItemBlockView
{
    private final ItemBlock itemBlock;
    
    public ItemBlockView(ItemBlock itemBlock)
    {
        this.itemBlock = itemBlock;
    }
    
    public void draw(Graphics2D graphics2D) 
    {
        graphics2D.draw(getBound());
    }
    
    public Rectangle getBound() 
    {
        return itemBlock.getBound();
    }
}
