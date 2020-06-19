package gui_project.ModelController;

import java.awt.Rectangle;
import java.util.ArrayList;

public class BaseRoomController
{

    private ArrayList<ItemBlockController> itemBlockCtrls;

    public BaseRoomController(ArrayList<ItemBlockController> itemBlockCtrls)
    {
        this.itemBlockCtrls = itemBlockCtrls;
    }

    public void addItemBlock(ItemBlockController itemBlockContrl)
    {
        this.getItemBlockCtrls().add(itemBlockContrl);
    }

    public ArrayList<ItemBlockController> getItemBlockCtrls()
    {
        return itemBlockCtrls;
    }

    /**
     *
     * used by the detective to detect if it is colliding with objects in the
     * game world
     */
    public void checkCollisions(int keyCode, MainController mainCtrl,
            DetectiveController detectiveCtrl, Rectangle roomBoundary)
    {
        boolean itemBlockCollision = false;
        boolean groundCollision = false;
        Rectangle boundaryCollision = null;

        for (ItemBlockController itemBlockCtrl : itemBlockCtrls)
        {
            //break out of the loop once a collision found
            if (detectiveCtrl.getView().getBound()
                    .intersects(itemBlockCtrl.getItemBlock().getBound()))
            {
                itemBlockCollision = itemBlockCtrl.collisionAction();

                if (itemBlockCollision)
                {
                    boundaryCollision = itemBlockCtrl.getItemBlock().getBound();
                }

                break;
            } else if (!roomBoundary.contains(detectiveCtrl.getView().getBound()))
            {
                groundCollision = true;
                boundaryCollision = roomBoundary;

                break;
            } else
            {
                //clear the gameText area if there is no collision
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
