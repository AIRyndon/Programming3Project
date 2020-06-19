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
        itemBlockView = new ItemBlockView(itemBlock, this);
    }

    public void draw(Graphics2D graphics2D)
    {
        itemBlockView.draw(graphics2D);
    }

    public ItemBlock getItemBlock()
    {
        return itemBlock;
    }

    public boolean collisionAction()
    {
        return true;
    }
}
