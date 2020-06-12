package gui_project.ModelController;

import gui_project.View.PasswordInputView;

public class PasswordInputController
{
    private final PasswordInputView view;
    private LockedArea lockedArea;
    
    public PasswordInputController(MainController mainCtrl, LockedArea lockedArea)
    {
        view = new PasswordInputView(mainCtrl, lockedArea);
        this.lockedArea = lockedArea;
    }
    
    public PasswordInputView getView()
    {
        return view;
    }
}
