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
        setHeight(7);
        setWidth(48);

        initializeMovingArea();
    }

    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        for (int row = 0; row < getHeight(); row++)
        {
            for (int col = 0; col < getWidth(); col++)
            {
                if (row == 0)
                {
                    movingArea[row][5] = 'B';
                    movingArea[row][6] = 'E';
                    movingArea[row][7] = 'D';
                }
                if (row == getHeight() / 2 && col == 0)
                {
                    movingArea[row][col] = '=';
                }

                if (row == 1 && col > 0 && col <= 10)
                {
                    movingArea[row][col] = '_';
                }
                if (row <= 1 && col == 11)
                {
                    movingArea[row][col] = '|';
                }
            }
        }
    }

    @Override
    protected void printRoom(String door)
    {
        super.printRoom(door);
        printWall();
    }
}
