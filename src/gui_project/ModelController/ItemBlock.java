package gui_project.ModelController;

import java.awt.Rectangle;

/**
 * ItemBlocks are items in the game world that the NPC can hit
 * 
 */
public class ItemBlock extends BaseModel
{
    private final int width;
    private final int height;

    public ItemBlock(int locationX, int locationY, int width, int height)
    {
        setLocationX(locationX);
        setLocationY(locationY);
        this.width = width;
        this.height = height;
    }

    /**
     * 
     * @return the itemBlock's rectangle bound, used for collision detection 
     */
    public Rectangle getBound()
    {
        return new Rectangle(getLocationX(), getLocationY(), width, height);
    }
}
