/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author airyn
 */
public class Hint
{
    private String name;
    private String description;
    private int xLocation;
    private int yLocation;

    public Hint(String name, String description, int xLocation, int yLocation)
    {
        this.name = name;
        this.description = description;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the xLocation
     */
    public int getxLocation()
    {
        return xLocation;
    }

    /**
     * @return the yLocation
     */
    public int getyLocation()
    {
        return yLocation;
    }

    @Override
    public String toString()
    {
        return "You found the " + getName() + "." + getDescription();
    }
}
