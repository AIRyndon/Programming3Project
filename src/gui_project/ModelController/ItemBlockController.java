/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.ItemBlockView;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
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
}
