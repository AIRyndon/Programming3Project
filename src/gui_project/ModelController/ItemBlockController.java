package gui_project.ModelController;

import gui_project.View.ItemBlockView;
import java.awt.Graphics2D;

public class ItemBlockController
{
    private final ItemBlock itemBlock;
    private final ItemBlockView itemBlockView;

    public ItemBlockController(ItemBlock itemBlock)
    {
        this.itemBlock = itemBlock;
        itemBlockView = new ItemBlockView(itemBlock);
    }

    public void draw(Graphics2D graphics2D)
    {
        itemBlockView.draw(graphics2D);
    }
    
    /**
     * 
     * @return the ItemBlock 
     */
    public ItemBlock getItemBlock()
    {
        return itemBlock;
    }

    /**
     * 
     * @return true if the detective should collide with the itemBlock 
     */
    public boolean collisionAction()
    {
        return true;
    }
}
