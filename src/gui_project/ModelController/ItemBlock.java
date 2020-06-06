/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.awt.Rectangle;

/**
 *
 * @author pc
 */
public class ItemBlock extends BaseModel
{

    private int width;
    private int height;

    public ItemBlock(int locationX, int locationY, int width, int height)
    {
        setLocationX(locationX);
        setLocationY(locationY);
        this.width = width;
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    void setHeight(int height)
    {
        this.height = height;
    }

    public Rectangle getBound()
    {
        return new Rectangle(getLocationX(), getLocationY(), width, height);
    }
}
