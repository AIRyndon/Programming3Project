/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.ItemBlockView;
import java.awt.Graphics2D;

/**
 *
 * @author pc
 */
public class ItemBlockController 
{
    private final ItemBlock itemBlock;
    private final ItemBlockView itemBlockView;
    
    public ItemBlockController(ItemBlock itemBlock)
    {
        this.itemBlock = itemBlock;
        itemBlockView = new ItemBlockView(this.getItemBlock(), this);
    }
    
    public void draw(Graphics2D graphics2D)
    {
        //todo - draw a rectangle
        itemBlockView.draw(graphics2D);
    }

    public ItemBlock getItemBlock() 
    {
        return itemBlock;
    }
}
