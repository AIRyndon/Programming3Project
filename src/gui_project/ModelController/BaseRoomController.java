/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
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

    //I am not sure if a View method should be defined here   
    public void checkCollisions(int keyCode, ArrayList<ItemBlockController> itemBlockCtrls,
            DetectiveController detectiveCtrl, Rectangle roomBoundary)
    {
        boolean itemBlockCollision = false;
        boolean groundCollision = false;
        Rectangle boundaryCollision = null;

        for (ItemBlockController itemBlockCtrl : itemBlockCtrls)
        {
            if (detectiveCtrl.getView().getBound()
                    .intersects(itemBlockCtrl.getItemBlock().getBound()))
            {
                //break out of the loop once you find a collision
                if (itemBlockCtrl instanceof NPCController)
                {
                    NPCController npcCtrl = (NPCController) itemBlockCtrl;

                    npcCtrl.setSpeaking(true);
                    
                    //this should be inside the npc, to check if the hint has
                    //to be revealed based on conversation level
                    //so we can also change the NPC line to include that a hint 
                    //popped up
                    if (npcCtrl.getNPC().getOwnedHint() != null)
                    {
                        npcCtrl.getNPC().getOwnedHint().reveal();
                    }

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
            } else if (!roomBoundary.contains(detectiveCtrl.getView().getBound()))
            {
                groundCollision = true;
                boundaryCollision = roomBoundary;
                break;
            } else
            {
                if (itemBlockCtrl instanceof NPCController)
                {
                    ((NPCController) itemBlockCtrl).setSpeaking(false);
                } else if (itemBlockCtrl instanceof HintController)
                {
                    ((HintController) itemBlockCtrl).clearMessageArea();
                }
            }
        }
        detectiveCtrl.keyPressed(keyCode, boundaryCollision, itemBlockCollision, groundCollision);
    }
}
