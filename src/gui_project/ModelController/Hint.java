package gui_project.ModelController;

public class Hint extends ItemBlock
{
    private boolean pickedUp = false;
    private boolean visible = true;
    private String message = "";
    private String name;
    private String description;

    public Hint(int locationX, int locationY, int width, int height)
    {
        super(locationX, locationY, width, height);
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
    
    void setNameAndDescription(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
}
