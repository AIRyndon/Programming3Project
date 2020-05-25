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

    public Detective()
    {
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

    /**
     * @return the velX
     */
    public int getVelX()
    {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    void setVelX(int velX)
    {
        this.velX = velX;
        super.notifyObservers();
    }

    /**
     * @return the velY
     */
    public int getVelY()
    {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    void setVelY(int velY)
    {
        this.velY = velY;
        super.notifyObservers();
    }
}
