package gui_project.ModelController;

import gui_project.View.LockedAreaView;
import gui_project.View.PasswordInputPanel;
import java.awt.Graphics2D;

public class LockedAreaController extends ItemBlockController
{
    private LockedArea lockedArea;
    private LockedAreaView view;
    private PasswordInputPanel passwordInputPanel;

    public LockedAreaController(MainController mainCtrl, LockedArea lockedArea)
    {
        super(lockedArea);
        this.lockedArea = lockedArea;
        view = new LockedAreaView(lockedArea, this);
        passwordInputPanel = new PasswordInputPanel(mainCtrl, this);
    }

    public LockedArea getLockedArea()
    {
        return lockedArea;
    }

    public PasswordInputPanel getPasswordInputPanel()
    {
        return passwordInputPanel;
    }

    public LockedAreaView getView()
    {
        return view;
    }

    public void disableLockedArea()
    {
        lockedArea.setUnLocked(true);
//        removeLockedAreaView();
    }

    @Override
    public boolean collisionAction()
    {
        return !lockedArea.isUnLocked();
    }

    @Override
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
//    
//    public void removeLockedAreaView()
//    {
//        
//    }
}
