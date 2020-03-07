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
public abstract class Room 
{
    abstract protected void hints();
    abstract protected void moving(char move);
    abstract protected void printRoom();
    abstract protected void printWall();

    protected int width;
    protected int height;
    public char[][] movingArea;

    public Room() 
    {
        
    }

    protected void initializeMovingArea()
    {
        this.movingArea = new char[height - 1][width];
        
        //Loops for empty movingArea (ground with wall and gate only)
        for (int i = 0; i < this.height - 2; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                if(j == 0 || j == width - 1)
                {
                    movingArea[i][j] = '|';
                }
                else if(i == 0 && j == width / 2)
                {
                     movingArea[i][j] = 'P';
                }
                else
                {
                     movingArea[i][j] = ' ';
                }
            }
        }
    }
    
    public int getWidth() 
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void printEntrance(String roomName)
    {
        //Print left side
        //Divide the width and roomName length in half to print in the middle
        //The +1 is for the | character
        for (int pos = 0; pos < this.width / 2 - (roomName.length() / 2 + 1); pos++) 
        {
            System.out.print("_");
        }

        //Print room name in the middle
        System.out.print(String.format("|%s|", roomName));

        //Print right side
        //The +2 is for the | character and the next position
        for (int pos = this.width / 2 + (roomName.length() / 2 + 2); pos < this.width; pos++) 
        {
            System.out.print("_");
        }

        System.out.println("");
    }
    
    protected void resetPlayerPosition()
    {
        for(int i = 0; i < this.height - 2; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                if(movingArea[i][j] == 'P')
                {
                    movingArea[i][j] = ' ';
                }
            }
        }
            
        movingArea[0][width / 2] = 'P';
    }
}
