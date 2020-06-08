package gui_project.ModelController;

import gui_project.View.KeyPasswordView;

public class KeyPasswordController
{
    private final KeyPasswordView view;
    private LockedArea lockedArea;
    
    public KeyPasswordController(MainController mainCtrl, LockedArea lockedArea)
    {
        view = new KeyPasswordView(mainCtrl, lockedArea);
        this.lockedArea = lockedArea;
    }
    
    public KeyPasswordView getView()
    {
        return view;
    }
}
