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
public class Detective extends BaseModel
{

    private int velX = 0;
    private int velY = 0;
    private int speed = 5;
    private int pickedUpHints = 0;
    private int conversationLevel = 1;
    private int groundHouseLocationX = 380;
    private int groundHouseLocationY = 50;
    private int roomHouseLocationX;
    private int roomHouseLocationY;

    public Detective()
    {
        this.setLocationX(200);
        this.setLocationY(50);
    }

    /**
     * @return the speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * make sure our setters have default accessibility so they are only visible
     * within the same package. Outside the package - only the Controller should
     * be able to set a model's state
     *
     * @param speed the speed to set
     */
    void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getVelX()
    {
        return velX;
    }

    void setVelX(int velX)
    {
        this.velX = velX;
        notifyObservers();
    }

    public int getVelY()
    {
        return velY;
    }

    void setVelY(int velY)
    {
        this.velY = velY;
        notifyObservers();
    }

    public int getGroundHouseLocationX()
    {
        return groundHouseLocationX;
    }

    void setGroundHouseLocationX(int groundHouseLocationX)
    {
        this.groundHouseLocationX = groundHouseLocationX;
    }

    public int getGroundHouseLocationY()
    {
        return groundHouseLocationY;
    }

    void setGroundHouseLocationY(int groundHouseLocationY)
    {
        this.groundHouseLocationY = groundHouseLocationY;
    }

    public int getRoomHouseLocationX()
    {
        return roomHouseLocationX;
    }

    void setRoomHouseLocationX(int roomHouseLocationX)
    {
        this.roomHouseLocationX = roomHouseLocationX;
    }

    public int getRoomHouseLocationY()
    {
        return roomHouseLocationY;
    }

    void setRoomHouseLocationY(int roomHouseLocationY)
    {
        this.roomHouseLocationY = roomHouseLocationY;
    }

    /**
     * @return the pickedUpHints
     */
    public int getPickedUpHints()
    {
        return pickedUpHints;
    }

    /**
     * @param pickedUpHints the pickedUpHints to set
     */
    void increasePickedUpHints()
    {
        ++pickedUpHints;
    }

    /**
     * @return the conversationLevel
     */
    public int getConversationLevel()
    {
        return conversationLevel;
    }

    /**
     * @param conversationLevel the conversationLevel to set
     */
    void setConversationLevel(int conversationLevel)
    {
        this.conversationLevel = conversationLevel;
    }
}
