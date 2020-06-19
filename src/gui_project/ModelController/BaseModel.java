package gui_project.ModelController;

import java.util.ArrayList;

public class BaseModel
{
    private ArrayList<BaseObserver> observers = new ArrayList<>();
    private int locationY;
    private int locationX;
    
    /**
     *
     * @return the item's Y coordinate
     */
    public int getLocationY()
    {
        return locationY;
    }
    
    void setLocationY(int locationY)
    {
        this.locationY = locationY;
    }
    
    /**
     *
     * @return the item's X coordinate
     */
    public int getLocationX()
    {
        return locationX;
    }
    
    void setLocationX(int locationX)
    {
        this.locationX = locationX;
    }

    /**
     * adds observers to model changes
     * @param obs
     */
    public void registerObserver(BaseObserver obs)
    {
        observers.add(obs);
    }
   
    /**
     *
     */
    public void notifyObservers()
    {
        for (int i = 0; i < observers.size(); i++)
        {
            BaseObserver obs = observers.get(i);
            obs.update(this);
        }
    }
}
