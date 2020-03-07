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

    public RoomButler()
    {
        this.height = 6;
        this.width = 48;
        
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
            for (int i = 0; i < height - 1; i++)
            {
                for (int j = 0; j < width; j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i][j - 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 1] = 'P';
                        
                        return 4;
                    }
                }
            }
        } 
        else if (move == 'd') 
        {
            for (int i = 0; i < height - 1; i++)
            {
                for (int j = 0; j < width; j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 1] = 'P';
                        
                        return 4;
                    }
                }
            }
        }
        else if (move == 's')
        {
            for (int i = 0; i < height - 1; i++)
            {
                for (int j = 0; j < width; j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i + 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i + 1][j] = 'P';
                        
                        return 4;
                    }
                }
            }
        }
        else if (move == 'w') 
        {
            for (int i = 1; i < height - 1; i++)
            {
                for (int j = 0; j < width; j++) 
                {
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i - 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i - 1][j] = 'P';
                        
                        return 4;
                    }
                }
            }
        }
        
        return 4;
    }

    @Override
    protected void printRoom(String door) 
    {
        super.printRoom(door);
        printWall();
    }    
}
