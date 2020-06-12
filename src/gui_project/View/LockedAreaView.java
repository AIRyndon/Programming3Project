package gui_project.View;

import gui_project.ModelController.LockedArea;
import gui_project.ModelController.LockedAreaController;
import java.awt.Graphics2D;

public class LockedAreaView extends ItemBlockView
{
    private final LockedArea lockedArea;
    private final LockedAreaController lockedAreaCtrl;
    
    public LockedAreaView(LockedArea lockedArea, LockedAreaController lockedAreaCtrl) 
    {
        super(lockedArea, lockedAreaCtrl);
        this.lockedArea = lockedArea;
        this.lockedAreaCtrl = lockedAreaCtrl;
    }
    
    public void draw(Graphics2D graphics2D) 
    {   
        //Should it be in Controller?
        if(!lockedArea.isIsLocked())
        {
            graphics2D.draw(super.getBound());
        }
    }
}
