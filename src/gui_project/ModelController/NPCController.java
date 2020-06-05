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

    public NPCController(NPC npc)
    {
        super(npc);
        this.npc = npc;
        view = new NPCView(npc, this);
    }
     
    public NPC getNPC(){
        return npc;
    }
    
    public NPCView getView(){
        return view;
    }
    
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

    public void setHasTalked(boolean status)
    {
        npc.setHasTalked(status);
    }
}
