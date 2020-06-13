/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project;

import gui_project.ModelController.Detective;
import gui_project.ModelController.MainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class RPGAppGUI
{

    /*
    MVC pattern important parts
    - the view should not change a model's state, 
    only a model's controller should change its state
    - the view's work is anything related to showing something on screen (like drawing)
    
    - the model's work is to send updates to listeners when its state changes (pretty much like ActionListener)
      > our detective has a sample of this pattern between the model and the view
    - the model can also talk with the database in our case (will set it up later)
    
    - the controller can be used to respond to the events sent by the view
      > our mainController is the one responsible for changing panels
    - the controller creates the view, it also changes its model's state, which
        is why our setter methods are not public but has default visibility(package)
     */
    
    /*Todo: move all key press and key release methods from View to Controller */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Detective detective = new Detective();
                try 
                {
                    new MainController(detective);
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(RPGAppGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
