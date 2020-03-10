package programming3project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class House extends Room implements LockedArea
{

    /*we match the key passed to Unlock in the class that uses House
     * the key could come from clues. I made it an interface so we can show that we
     * are using interfaces :). And a house can be composed of a lockedRoom, so it kinda makes sense
     */
    private int lockedAreaKey;

    public House(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(20);
        setWidth(52);
        initializeMovingArea();
    }

    public boolean unlock(int key)
    {
        if (key == lockedAreaKey)
        {
            return true;
        }

        return false;
    }

    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        for (int i = 0; i < getHeight() - 1; i++)
        {
            for (int j = 0; j < getWidth() - 1; j++)
            {
                if (i <= 5)
                {
                    if (i == 1)
                    {
                        //MAID'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][6] = 'M';
                        movingArea[i][7] = 'A';
                        movingArea[i][8] = 'I';
                        movingArea[i][9] = 'D';
                        movingArea[i][10] = '\'';
                        movingArea[i][11] = 'S';
                        movingArea[i][13] = 'R';
                        movingArea[i][14] = 'O';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'M';
                    } else if (i == 2)
                    {
                        if (j >= 1 && j <= getWidth() / 2 - 3)
                        {
                            movingArea[i][j] = '_';
                        }

                        //KITCHEN
                        movingArea[i][getWidth() / 2 - 3] = '/';
                        movingArea[i][getWidth() / 2 + 11] = 'K';
                        movingArea[i][getWidth() / 2 + 12] = 'I';
                        movingArea[i][getWidth() / 2 + 13] = 'T';
                        movingArea[i][getWidth() / 2 + 14] = 'C';
                        movingArea[i][getWidth() / 2 + 15] = 'H';
                        movingArea[i][getWidth() / 2 + 16] = 'E';
                        movingArea[i][getWidth() / 2 + 17] = 'N';
                        movingArea[i][getWidth() - 1] = '=';

                    } else if (i == 5)
                    {
                        if ((j >= 1 && j <= getWidth() / 2 - 4) || (j >= getWidth() / 2 + 11 && j < getWidth() - 1))
                        {
                            movingArea[i][j] = '_';
                        } else
                        {
                            movingArea[i][getWidth() / 2 - 3] = '/';
                        }
                    } else if (i == 4)
                    {
                        //BUTLER'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][5] = 'B';
                        movingArea[i][6] = 'U';
                        movingArea[i][7] = 'T';
                        movingArea[i][8] = 'L';
                        movingArea[i][9] = 'E';
                        movingArea[i][10] = 'R';
                        movingArea[i][11] = '\'';
                        movingArea[i][12] = 'S';
                        movingArea[i][14] = 'R';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'O';
                        movingArea[i][17] = 'M';
                    }

                    if (j == getWidth() / 2 - 3 || j == getWidth() / 2 + 3)
                    {
                        movingArea[i][j] = '|';
                    }
                } else if (i <= 12)
                {
                    if (i == 9)
                    {
                        //WIFE'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][6] = 'W';
                        movingArea[i][7] = 'I';
                        movingArea[i][8] = 'F';
                        movingArea[i][9] = 'E';
                        movingArea[i][10] = '\'';
                        movingArea[i][11] = 'S';
                        movingArea[i][13] = 'R';
                        movingArea[i][14] = 'O';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'M';

                        //Door
                        movingArea[i][getWidth() / 2 - 3] = '/';

                        //LIVING ROOM
                        movingArea[i][getWidth() / 2 + 6] = 'L';
                        movingArea[i][getWidth() / 2 + 7] = 'I';
                        movingArea[i][getWidth() / 2 + 8] = 'V';
                        movingArea[i][getWidth() / 2 + 9] = 'I';
                        movingArea[i][getWidth() / 2 + 10] = 'N';
                        movingArea[i][getWidth() / 2 + 11] = 'G';
                        movingArea[i][getWidth() / 2 + 13] = 'R';
                        movingArea[i][getWidth() / 2 + 14] = 'O';
                        movingArea[i][getWidth() / 2 + 15] = 'O';
                        movingArea[i][getWidth() / 2 + 16] = 'M';
                    }
                    if (i == 12)
                    {
                        if (j >= 1 && j <= getWidth() / 2 + 8)
                        {
                            movingArea[i][j] = '_';
                        }

                        movingArea[i][getWidth() / 2 - 3] = '|';
                        movingArea[i][getWidth() / 2 - 2] = '/';
                    } else if (j == getWidth() / 2 - 3)
                    {
                        movingArea[i][j] = '|';
                    } else if (j == getWidth() - 1)
                    {
                        movingArea[i][j] = '=';
                    }
                } else if (i == 14)
                {
                    movingArea[i][12] = '|';
                    movingArea[i][getWidth() / 2 + 9] = '|';
                    movingArea[i][getWidth() / 2 + 10] = '\\';
                } else if (i == 15)
                {
                    //LOCKED AREA
                    movingArea[i][3] = 'L';
                    movingArea[i][4] = 'O';
                    movingArea[i][5] = 'C';
                    movingArea[i][6] = 'K';
                    movingArea[i][7] = 'E';
                    movingArea[i][8] = 'D';

                    //LOCKED DOOR
                    movingArea[i][12] = '#';

                    //WORKING ROOM
                    movingArea[i][getWidth() / 2 - 6] = 'W';
                    movingArea[i][getWidth() / 2 - 5] = 'O';
                    movingArea[i][getWidth() / 2 - 4] = 'R';
                    movingArea[i][getWidth() / 2 - 3] = 'K';
                    movingArea[i][getWidth() / 2 - 2] = 'I';
                    movingArea[i][getWidth() / 2 - 1] = 'N';
                    movingArea[i][getWidth() / 2] = 'G';

                    //BOOK ROOM
                    movingArea[i][getWidth() / 2 + 9] = '|';
                    movingArea[i][getWidth() / 2 + 10] = 'B';
                    movingArea[i][getWidth() / 2 + 11] = '\\';
                } else if (i == 16)
                {
                    //LOCKED AREA
                    movingArea[i][4] = 'A';
                    movingArea[i][5] = 'R';
                    movingArea[i][6] = 'E';
                    movingArea[i][7] = 'A';

                    //WALL
                    movingArea[i][12] = '|';

                    //WORKING ROOM
                    movingArea[i][getWidth() / 2 - 4] = 'R';
                    movingArea[i][getWidth() / 2 - 3] = 'O';
                    movingArea[i][getWidth() / 2 - 2] = 'O';
                    movingArea[i][getWidth() / 2 - 1] = 'M';

                    //BOOK ROOM
                    movingArea[i][getWidth() / 2 + 9] = '/';
                    movingArea[i][getWidth() / 2 + 10] = 'O';
                    movingArea[i][getWidth() / 2 + 11] = 'O';
                    movingArea[i][getWidth() / 2 + 12] = '\\';
                } else if (i == 17)
                {
                    //WALL
                    movingArea[i][12] = '|';

                    //BOOK ROOM
                    movingArea[i][getWidth() / 2 + 9] = '|';
                    movingArea[i][getWidth() / 2 + 10] = 'K';
                    movingArea[i][getWidth() / 2 + 11] = 'S';
                    movingArea[i][getWidth() / 2 + 13] = '\\';
                } else
                {
                    if (j == getWidth() / 2 - 14 || j == getWidth() / 2 + 9)
                    {
                        movingArea[i][j] = '|';
                    }
                }
            }
        }        
    }

    /**
     * Printing the house
     */
    protected void printRoom(String door)
    {
        super.printRoom(door);

        printWall();
    }

    /**
     * Printing the wall
     */
    @Override
    protected void printWall()
    {
        //Print gate and wall (first row)
        for (int wid = 0; wid < this.getWidth(); wid++)
        {
            if (wid == 0 || wid == getWidth() - 1 || wid == 12
                    || wid == getWidth() / 2 + 9 || wid == getWidth() / 2 + 14)
            {
                System.out.print("|");
            } else
            {
                System.out.print("_");
            }
        }

        System.out.println();
    }

    public void printLockedArea()
    {
        //Loops for height
        for (int i = 0; i < this.getHeight(); i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if (j == 0 || j == this.getWidth() - 1)
                {
                    System.out.print("|");
                } else if (i == 0 || i == this.getHeight() - 1)
                {
                    System.out.print("----");
                } else
                {
                    System.out.print("    ");
                }
            }

            System.out.println("");
        }
    }
}
