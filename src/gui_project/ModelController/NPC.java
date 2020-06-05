/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.util.Random;

/**
 *
 * @author Angelo
 */
public class NPC extends BaseModel
{
    public static Random random = new Random();
    
    private boolean hasTalked;
    private final String role;
    
    public NPC(String role,int locationX,int locationY)
    {
        this.role = role;
        setLocationX(locationX);
        setLocationY(locationY);
    }
    
    public String getRole()
    {
        return role;
    }
 
    /**
     * @return the hasTalked
     */
    public boolean HasTalked()
    {
        return hasTalked;
    }

    /**
     * @param hasTalked the hasTalked to set
     */
    void setHasTalked(boolean hasTalked)
    {
        this.hasTalked = hasTalked;
    }
    
    public void generateRandomPosition(int xCoord, int yCoord)
    {
        this.setLocationX(xCoord);
        this.setLocationY(yCoord);
    }
}
