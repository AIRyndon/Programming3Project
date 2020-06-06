/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.util.ArrayList;

/**
 * All our models should inherit from this one, so the
 * models can use Observer Pattern with the views
 *
 */
public class BaseModel
{
    private ArrayList<BaseObserver> characterObs = new ArrayList<>();
    private int locationY;
    private int locationX;
    
    public int getLocationY()
    {
        return locationY;
    }
    
    void setLocationY(int locationY)
    {
        this.locationY = locationY;
    }
    
    public int getLocationX()
    {
        return locationX;
    }
    
    void setLocationX(int locationX)
    {
        this.locationX = locationX;
    }

    public void registerObserver(BaseObserver obs)
    {
        characterObs.add(obs);
    }

    public void removeObserver(BaseObserver obs)
    {
        int idx = characterObs.indexOf(obs);

        if (idx >= 0)
        {
            characterObs.remove(idx);
        }
    }
   
    public void notifyObservers()
    {
        for (int i = 0; i < characterObs.size(); i++)
        {
            BaseObserver obs = characterObs.get(i);
            obs.update(this);
        }
    }
}
