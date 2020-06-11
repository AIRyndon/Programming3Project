package gui_project.ModelController;

import java.awt.Rectangle;
import java.util.ArrayList;

public class BaseRoomController
{
    private ArrayList<ItemBlockController> itemBlockCtrls = new ArrayList<ItemBlockController>();

    public void addItemBlock(ItemBlockController itemBlockContrl)
    {
        this.getItemBlockCtrls().add(itemBlockContrl);
    }

    public ArrayList<ItemBlockController> getItemBlockCtrls()
    {
        return itemBlockCtrls;
    }

    public void checkCollisions(int keyCode, ArrayList<ItemBlockController> itemBlockCtrls,
            DetectiveController detectiveCtrl, Rectangle roomBoundary)
    {
        boolean itemBlockCollision = false;
        boolean groundCollision = false;
        Rectangle boundaryCollision = null;

        for (ItemBlockController itemBlockCtrl : itemBlockCtrls)
        {
            //break out of the loop once you find a collision
            if (detectiveCtrl.getView().getBound()
                    .intersects(itemBlockCtrl.getItemBlock().getBound()))
            {          
                if (itemBlockCtrl instanceof NPCController)
                {
                    NPCController npcCtrl = (NPCController) itemBlockCtrl;
                    npcCtrl.setSpeaking(true);
                    
                } else if (itemBlockCtrl instanceof HintController)
                {
                    HintController hintCtrl = (HintController) itemBlockCtrl;
                    
                    if (!hintCtrl.getHint().isVisible())
                    {
                        //a special case for hints because you don't want to bump into
                        //them when they aren't visible
                        break;                 
                    }
                    
                    hintCtrl.pickup();
                }

                itemBlockCollision = true;
                boundaryCollision = itemBlockCtrl.getItemBlock().getBound();
                break;
            } 
            else if (!roomBoundary.contains(detectiveCtrl.getView().getBound()))
            {
                groundCollision = true;
                boundaryCollision = roomBoundary;
                break;
            } 
            else
            {
                if (itemBlockCtrl instanceof NPCController)
                {
                    ((NPCController) itemBlockCtrl).setSpeaking(false);
                } 
                else if (itemBlockCtrl instanceof HintController)
                {
                    ((HintController) itemBlockCtrl).clearMessageArea();
                }
            }
        }
        
        detectiveCtrl.keyPressed(keyCode, boundaryCollision, itemBlockCollision, groundCollision);
    }
}
