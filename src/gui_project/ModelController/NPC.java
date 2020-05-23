/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

/**
 *
 * @author Angelo
 */
public class NPC extends BaseModel
{
    private boolean hasTalked;
    private int locationX = 0;
    private int locationY = 0;

    /**
     * @return the locationY
     */
    public int getLocationY()
    {
        return locationY;
    }

    /**
     * @param locationY the locationY to set
     */
    void setLocationY(int locationY)
    {
        this.locationY = locationY;
    }

    /**
     * @return the locationX
     */
    public int getLocationX()
    {
        return locationX;
    }

    /**
     * @param locationX the locationX to set
     */
    void setLocationX(int locationX)
    {
        this.locationX = locationX;
    }

    /**
     * @return the hasTalked
     */
    public boolean HasTalked()
    {
        return hasTalked;
    }

    /**
     * @param hasTalked the hasTalked to set
     */
    void setHasTalked(boolean hasTalked)
    {
        this.hasTalked = hasTalked;
    }
}
