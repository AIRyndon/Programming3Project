package gui_project.ModelController;

import gui_project.View.GuessKillerView;

public class GuessKillerController
{

    private final GuessKillerView view;

    public GuessKillerController(MainController mainCtrl)
    {
        view = new GuessKillerView(mainCtrl);
    }
    
    /**
     * 
     * @return the View
     */
    public GuessKillerView getView()
    {
        return view;
    }
}
