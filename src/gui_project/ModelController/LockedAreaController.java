package gui_project.ModelController;

import gui_project.View.LockedAreaView;

public class LockedAreaController extends ItemBlockController
{
    private LockedArea lockedArea;
    private final LockedAreaView view;
    private KeyPasswordController keyPanel;
    
    public LockedAreaController(MainController mainCtrl, LockedArea lockedArea) 
    {
        super(lockedArea);
        this.lockedArea = lockedArea;
        keyPanel = new KeyPasswordController(mainCtrl, lockedArea);
        view = new LockedAreaView(lockedArea, this);
    }
    
    public KeyPasswordController getKeyPanel() 
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
