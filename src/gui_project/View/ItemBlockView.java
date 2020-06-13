package gui_project.View;

import gui_project.ModelController.ItemBlock;
import gui_project.ModelController.ItemBlockController;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ItemBlockView
{
    private final ItemBlock itemBlock;
    private final ItemBlockController itemBlockCtrl;
    
    public ItemBlockView(ItemBlock itemBlock, ItemBlockController itemBlockCtrl)
    {
        this.itemBlock = itemBlock;
        this.itemBlockCtrl = itemBlockCtrl;
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
