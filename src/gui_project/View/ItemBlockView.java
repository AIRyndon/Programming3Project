/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.View;

import gui_project.ModelController.ItemBlock;
import gui_project.ModelController.ItemBlockController;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author pc
 */
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
        return new Rectangle(itemBlock.getLocationX(), itemBlock.getLocationY(), 
                itemBlock.getWidth(), itemBlock.getHeight());
    }
}
