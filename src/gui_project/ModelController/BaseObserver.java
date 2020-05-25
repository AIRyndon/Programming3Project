/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

/**
 *
 * All our other observer interfaces must implement this base,
 * as this is used by the BaseModel to notify
 */
public interface BaseObserver
{
    void update();
}
