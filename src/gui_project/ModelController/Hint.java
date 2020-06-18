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
    
    //Getters and setters
    public String getName()
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }
    
    public boolean isVisible()
    {
        return visible;
    } 
    
    void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    void sendMessage()
    {
        notifyObservers();
    }

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
        message = getName() + '\n' + getDescription();
    }
}
