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
    protected int width;
    protected int height;
    protected char[][] movingArea;
    
    //print room
    abstract protected void initiliseMovingArea();
    abstract protected void printRoom();
}
