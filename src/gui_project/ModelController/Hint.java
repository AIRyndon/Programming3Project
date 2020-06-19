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
    private final String name;
    private final String description;

    /**
     *
     * @param name
     * @param description
     * @param locationX
     * @param locationY
     * @param width
     * @param height
     */
    public Hint(String name, String description,
            int locationX, int locationY, int width, int height)
    {
        super(locationX, locationY, width, height);
        this.name = name;
        this.description = description;
    }
    
    /**
     *
     * @return the hint's name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the hint's description
     */
    public String getDescription() 
    {
        return description;
    }
    
    /**
     *
     * @return the hint's visibility status
     */
    public boolean isVisible()
    {
        return visible;
    } 
    
    void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
    /**
     *
     * @return the hint's message
     */
    public String getMessage()
    {
        return message;
    }

    void setMessage(String message)
    {
        this.message = message;
        notifyObservers();
    }

    /**
     *
     * @return the hint being picked up
     */
    public boolean isPickedUp()
    {
        return pickedUp;
    }

    void setPickedUp()
    {
        pickedUp = true;
        visible = false;
        setMessage(name + '\n' + description);
    }
}
