package gui_project.View;

import gui_project.ModelController.NPC;
import gui_project.ModelController.NPCController;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class NPCView 
{
    private final NPC npc;
    private final NPCController npcController;
      
    public NPCView(NPC npc, NPCController controller) 
    {
        this.npc = npc;
        npcController = controller;
    }
    
    public void draw(Graphics2D graphics2D, String symbol) 
    {
        graphics2D.drawString(symbol, npc.getLocationX(), npc.getLocationY());
        graphics2D.draw(getBound());
        
        if (npc.HasTalked())
        {
            graphics2D.drawString("I can talk", npc.getLocationX() + 20, npc.getLocationY());
        }
    }
    
    public Rectangle getBound() 
    {
        return new Rectangle(npc.getLocationX() - 4, npc.getLocationY() - 12, 20, 20);
    }
}
