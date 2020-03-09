/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.Scanner;

/**
 *
 * @author pc
 */
public class RoomMaid extends Room
{

    public RoomMaid(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(6);
        setWidth(48);

        initializeMovingArea();
    }

    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();
    }

    @Override
    protected void printRoom(String door)
    {
        super.printRoom(door);
        printWall();
    }
}
