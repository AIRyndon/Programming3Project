/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author group
 */
public abstract class Room //Room could be interface for now
{
    protected final int width;
    protected final int height;
    protected char[][] movingArea;
    
    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }  
    
   //print room
    abstract protected void initiliseMovingArea();
    abstract protected void printRoom();

}
