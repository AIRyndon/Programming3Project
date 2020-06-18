package gui_project.ModelController;

import gui_project.View.ItemBlockView;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ItemBlockController
{
    private final ItemBlock itemBlock;
    private final ItemBlockView itemBlockView;
    private ArrayList<ItemBlockController> itemBlockCtrls = new ArrayList<>();

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

    public void addItemBlock(ItemBlockController itemBlockContrl)
    {
        this.getItemBlockCtrls().add(itemBlockContrl);
    }

    public ArrayList<ItemBlockController> getItemBlockCtrls()
    {
        return itemBlockCtrls;
    }

//    public void removeItemBlock(ItemBlockController itemBlockContrl)
//    {
//        itemBlockCtrls.remove(itemBlockContrl);
//    }

    public boolean collisionAction()
    {
        return true;
    }
}
