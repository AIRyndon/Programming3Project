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
public class Room //Room could be interface for now
{
    protected int width;
    protected int height;
    
    public Room(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }
    
    //Getters and setters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    //print room
    public void printRoom()
    {
        
    }
    
}
