package gui_project.ModelController;

import gui_project.View.LockedAreaView;

public class LockedAreaController extends ItemBlockController
{
    private LockedArea lockedArea;
    private final LockedAreaView view;
    private PasswordInputController keyPanel;
    
    public LockedAreaController(MainController mainCtrl, LockedArea lockedArea) 
    {
        super(lockedArea);
        this.lockedArea = lockedArea;
        keyPanel = new PasswordInputController(mainCtrl, lockedArea);
        view = new LockedAreaView(lockedArea, this);
    }
    
    public PasswordInputController getPasswordInputController() 
    {
        return keyPanel;
    }
    
    public LockedArea getLockedArea() 
    {
        return lockedArea;
    }
    
    public LockedAreaView getView() 
    {
        return view;
    }
    
    
}
