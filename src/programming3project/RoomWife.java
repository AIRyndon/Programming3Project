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
public class RoomWife extends Room
{

    public RoomWife(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(14);
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
                if (row == 1 || row == 4)
                {
                    if (col > 0 && col <= 12)
                    {
                        movingArea[row][col] = '_';
                    }
                    if (row != 1 && col == 13)
                    {
                        movingArea[row][col] = '|';
                    }
                }
                if (row == 2)
                {
                    movingArea[row][13] = '|';
                }
                if (row == 3)
                {
                    movingArea[row][5] = 'B';
                    movingArea[row][6] = 'E';
                    movingArea[row][7] = 'D';
                    movingArea[row][13] = '|';
                }

                if (row == getHeight() / 2 && col == 0)
                {
                    movingArea[row][col] = '=';
                }
            }
        }
        
        //Wife is in her bed room
        positionNPC('W');
        positionNPC('D');
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
                    //Identify 'D' of "BED" vs 'D' of "Daughter"
                    if(movingArea[i][j] == person && (i != 3 || j != 7))
                    {
                        if(i < 5 && i > 1 && j < 13)
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
