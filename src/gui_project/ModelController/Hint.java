/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.util.ArrayList;

/**
 *
 * @author Angelo
 */
public class Hint extends ItemBlock
{

    private boolean pickedUp = false;
    private boolean visible = false;
    private String message = "";
    private String name;
    private String description;

    public Hint(String name, String description,
            int locationX, int locationY, int width, int height)
    {
        super(locationX, locationY, width, height);
        this.name = name;
        this.description = description;
    }

    /**
     * @return the visible
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    void setVisible(boolean visible)
    {
        this.visible = visible;
        if (!visible)
        {
            message = name + '\n' + description;
        }
    }

    void sendMessage()
    {
        notifyObservers();
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isPickedUp()
    {
        return pickedUp;
    }

    void setPickedUp()
    {
        pickedUp = true;
        visible = false;
    }
}
