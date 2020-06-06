package gui_project.View;

import gui_project.ModelController.NPC;
import gui_project.ModelController.NPCController;
import java.awt.Graphics2D;

public class NPCView extends ItemBlockView
{
    private final NPC npc;
    private final NPCController npcController;
      
    public NPCView(NPC npc, NPCController controller) 
    {
        super(npc,controller);
        this.npc = npc;
        npcController = controller;
    }
    
    public void draw(Graphics2D graphics2D, String symbol) 
    {
        super.draw(graphics2D);
        graphics2D.drawString(symbol, npc.getLocationX() + 4, npc.getLocationY() + 12);
    }
}
