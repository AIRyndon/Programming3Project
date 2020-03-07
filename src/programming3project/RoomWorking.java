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
    protected int moving(char move) 
    {
        //Invalid input
        if(move != 'a' && move != 's' && move != 'd' && move != 'w')           
        {
            System.out.println("Invalid Input!");
        }
        else if (move == 'a') 
        {
            for (int i = 0; i < getHeight() - 1; i++)
            {
                for (int j = 0; j < getWidth(); j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i][j - 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 1] = 'P';
                        
                        return 6;
                    }
                }
            }
        } 
        else if (move == 'd') 
        {
            for (int i = 0; i < getHeight() - 1; i++)
            {
                for (int j = 0; j < getWidth(); j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 1] = 'P';
                        
                        return 6;
                    }
                }
            }
        }
        else if (move == 's')
        {
            for (int i = 0; i < getHeight() - 1; i++)
            {
                for (int j = 0; j < getWidth(); j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i + 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i + 1][j] = 'P';
                        
                        return 6;
                    }
                }
            }
        }
        else if (move == 'w') 
        {
            for (int i = 1; i < getHeight() - 1; i++)
            {
                for (int j = 0; j < getWidth(); j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i - 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i - 1][j] = 'P';
                        
                        return 6;
                    }
                }
            }
        }
        
        return 6;
    }

    @Override
    protected void printRoom(String door)
    {
        super.printRoom(door);
        printWall();
    }    
}
