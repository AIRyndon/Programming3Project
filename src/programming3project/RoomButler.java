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
public class RoomButler extends Room
{

    public RoomButler(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(6);
        setWidth(48);

        initializeMovingArea();
    }

    @Override
    protected void printRoom(String door)
    {
        super.printRoom(door);
        printWall();
    }
}
