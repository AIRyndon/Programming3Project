/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project;

import gui_project.ModelController.Detective;
import gui_project.ModelController.MainController;

/**
 *
 * @author Angelo
 */
public class RPGAppGUI
{

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Detective detective = new Detective();
                new MainController(detective);
            }
        });
    }
}
