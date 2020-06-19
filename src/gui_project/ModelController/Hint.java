package gui_project.ModelController;

public class Hint extends ItemBlock
{
    private boolean pickedUp = false;
    private boolean visible = false;
    private String message = "";
    private String name;
    private String description;

    public Hint(int locationX, int locationY, int width, int height)
    {
        super(locationX, locationY, width, height);
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
    
    public String getMessage()
    {
        return message;
    }

    void setMessage(String message)
    {
        this.message = message;
        notifyObservers();
    }

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
