/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

/**
 *
 * @author Angelo
 */
public class NPCController
{
    private final NPC npc;

    public NPCController(NPC npc)
    {
        this.npc = npc;

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
