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
        
        for(ItemBlockController itemBlockCtrl : itemBlockCtrls)
        {
            if(detectiveCtrl.getView().getBound().intersects(itemBlockCtrl.getItemBlock().getBound()))
            {
                if (itemBlockCtrl instanceof NPCController)
                {
                    System.out.println(((NPCController) itemBlockCtrl).getNPC().getFirstLine()); 
                }else if (itemBlockCtrl instanceof HintController)
                {
                    System.out.println("Im a hint!!");
                }
                              
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
