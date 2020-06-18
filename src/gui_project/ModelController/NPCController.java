/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.NPCView;
import java.awt.Graphics2D;

/**
 *
 * @author Angelo
 */
public class NPCController extends ItemBlockController
{

    private final NPC npc;
    private final NPCView view;
    private HintController hintCtrl;

    public NPCController(NPC npc)
    {
        super(npc);
        this.npc = npc;
        view = new NPCView(npc, this);
    }

    public NPC getNPC()
    {
        return npc;
    }

    public NPCView getView()
    {
        return view;
    }

    public void setOwnedHint(HintController hintCtrl)
    {
        this.hintCtrl = hintCtrl;
    }

    @Override
    public void draw(Graphics2D graphics2D)
    {
        view.draw(graphics2D, npc.getSymbol());
    }

    public void setLocationX(int locationX)
    {
        npc.setLocationX(locationX);
    }

    public void setLocationY(int locationY)
    {
        npc.setLocationY(locationY);
    }

    public void setSpeaking(boolean status)
    {
        npc.setSpeaking(status);
    }

    public void tryToPlaceHint()
    {
        if (npc.isLinesUnlocked())
        {
            if (hintCtrl != null)
            {
                hintCtrl.reveal();
            }
        }
    }

    boolean hasTalkedWithPlayer()
    {
        return npc.isDiscovered();
    }

    void unlockLines()
    {
        npc.unlockLines(true);
    }
}
