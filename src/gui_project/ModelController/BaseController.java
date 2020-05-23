/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.MainView;

/**
 *
 * @author Angelo
 */
public class BaseController
{
    MainView view;

    public BaseController()
    {
        view = new MainView(this);
        view.renderView();
    }
}
