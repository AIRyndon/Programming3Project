package gui_project.View;

import gui_project.ModelController.LockedArea;
import gui_project.ModelController.LockedAreaController;
import java.awt.Graphics2D;

public class LockedAreaView extends ItemBlockView
{
    private final LockedArea lockedArea;
    
    public LockedAreaView(LockedArea lockedArea, LockedAreaController lockedAreaCtrl) 
    {
        super(lockedArea, lockedAreaCtrl);
        this.lockedArea = lockedArea;
    }
    
    @Override
    public void draw(Graphics2D graphics2D) 
    {   
        if(!lockedArea.isUnlocked())
        {
            graphics2D.draw(super.getBound());
        }
    }
}
