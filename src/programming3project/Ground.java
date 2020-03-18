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
public class Ground extends Room 
{
    public Ground(String name, Room previous)
    {
        super(previous);
        this.setLock(new Locks());
        setName(name);
        setHeight(24);
        setWidth(52);
        initializeMovingArea();
    }

    /**
     * initialize MovingArea Create a 2D array representing for hints(X), space(
     * ), and player(P) to allow and show moving
     */
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        //Assign dog's place
        movingArea[0][getWidth() - 15] = '|';
        movingArea[1][getWidth() - 15] = '#';
        movingArea[1][getWidth() - 14] = ' ';
        movingArea[1][getWidth() - 9] = 'd';
        movingArea[1][getWidth() - 8] = 'o';
        movingArea[1][getWidth() - 7] = 'g';
        movingArea[2][getWidth() - 15] = '|';

        for (int i = getWidth() - 14; i < getWidth() - 1; i++)
        {
            movingArea[2][i] = '_';
        }

        /**
         * Assign HouseArea (because the house is on the ground) Player cannot
         * move in this area, just access through the door
         */
        for (int i = 7; i <= 14; i++)
        {
            for (int j = 17; j < 35; j++)
            {
                if (i == 7) //House's door
                {
                    if (j <= 17 + 6 || j >= 17 + 12)
                    {
                        movingArea[i][j] = '_';
                    } 
                    else if (j == (17 + 7) || j == (17 + 11))
                    {
                        movingArea[i][j] = '|';
                    } 
                    else
                    {
                        movingArea[i][j] = '/';
                    }
                } 
                else if (i == 14)
                {
                    if (j == 17 || j == 34)
                    {
                        movingArea[i][j] = '|';
                    } else
                    {
                        movingArea[i][j] = '_';
                    }
                } 
                else //House's area
                {
                    if (j == 17 || j == 34) //House's left and right wall (barrier)
                    {
                        movingArea[i][j] = '|';
                    }
                    else //Empty area inside the house
                    {
                        movingArea[i][j] = ' ';
                    }
                }
            }
        }

        //Printing the word "HOUSE"
        movingArea[11][24] = 'H';
        movingArea[11][25] = 'O';
        movingArea[11][26] = 'U';
        movingArea[11][27] = 'S';
        movingArea[11][28] = 'E';

        //The butler is in the ground
        positionNPC('B');

    }
    
    @Override
    protected void positionNPC(char person)
    {
        super.positionNPC(person);
        boolean stop = false;
        
        while(!stop)
        {
            for (int i = 0; i <= getHeight() - 2; i++)
            {   
                for (int j = 0; j <= getWidth() - 2; j++)
                {
                    if(movingArea[i][j] == person)
                    {
                        if((i < 3 && j > getWidth() - 15) || (i >= 7 && i <= 14 && j >= 17 && j <= 34))
                        {
                            movingArea[i][j] = ' ';
                            super.positionNPC(person);
                        }
                        else
                        {
                            stop = true;
                        }
                    }
                }
            }
        }
    }
}
