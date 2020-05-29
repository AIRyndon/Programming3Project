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
public class ControlItemBlocks
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
    
    //I am not sure if View methods should be defined here    
    public void checkCollisions(int keyCode, ArrayList<ItemBlockController> itemBlockCtrls,
            DetectiveController detectiveCtrl, Rectangle roomBoundary)
    {
        //CHECK COLLISIONS
        boolean itemBlockCollision = false;
        boolean groundCollision = false;
        Rectangle boundaryCollision = null;
        
        for(ItemBlockController itemBlockCtrl : itemBlockCtrls)
        {
            if(detectiveCtrl.getView().getBound().intersects(itemBlockCtrl.getItemBlock().getBound()))
            {
                itemBlockCollision = true;
                boundaryCollision = itemBlockCtrl.getItemBlock().getBound();
            }
            else if(!roomBoundary.contains(detectiveCtrl.getView().getBound()))
            {
                groundCollision = true;
                boundaryCollision = roomBoundary;
            }
        }
        
        detectiveCtrl.keyPressed(keyCode, boundaryCollision, itemBlockCollision, groundCollision);
    }
}
