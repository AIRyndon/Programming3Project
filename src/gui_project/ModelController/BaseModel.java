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
    private ArrayList<BaseObserver> observers = new ArrayList<>();

    public void registerObserver(BaseObserver obs)
    {
        observers.add(obs);
    }

    public void removeObserver(BaseObserver obs)
    {
        int idx = observers.indexOf(obs);

        if (idx >= 0)
        {
            observers.remove(idx);
        }
    }

    public void notifyObservers()
    {
        for (int i = 0; i < observers.size(); i++)
        {
            BaseObserver obs = observers.get(i);
            obs.update();
        }
    }
}
