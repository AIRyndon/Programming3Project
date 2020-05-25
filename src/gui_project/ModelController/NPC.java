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
public class NPC extends BaseModel
{
    private boolean hasTalked;
    private final String role;
    
    public NPC(String role){
        this.role = role;
    }
    
    
    public String getRole(){
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
}
