/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author pc
 */
public class House extends Room implements LockedArea
{
    /*we match the key passed to Unlock in the class that uses House
    *the key could come from clues. I made it an interface so we can show that we
    * are using interfaces :). And a house can be composed of a lockedRoom, so it kinda makes sense
    */
    private int lockedAreaKey;
    
    public House(int width,int height){
        super(width,height);
    }
    
    @Override
    public boolean unlock(int key){
        
        if (key == lockedAreaKey) {
            return true;
        }
        return false;
    }
}
