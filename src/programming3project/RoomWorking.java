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
public class RoomWorking extends Room
{

    public RoomWorking(String name)
    {
        setName(name);
        setHeight(12);
        setWidth(72);
        
        initializeMovingArea();
    }

    @Override
    protected void printRoom(String door)
    {
        super.printRoom(door);
        printWall();
    }    
}
