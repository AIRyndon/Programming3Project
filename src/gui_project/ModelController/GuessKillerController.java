/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.GuessKillerView;

/**
 *
 * @author Angelo
 */
public class GuessKillerController
{

    private final GuessKillerView view;

    public GuessKillerController(MainController mainCtrl)
    {
        view = new GuessKillerView(mainCtrl);
    }

    public GuessKillerView getView()
    {
        return view;
    }
}
