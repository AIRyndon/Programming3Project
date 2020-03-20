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
    public RoomWorking(String name, Room previous)
    {
        super(previous);
        this.setLock(new Password());
        setName(name);
        setHeight(12);
        setWidth(72);

        initializeMovingArea();        
    }
    
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        //Assign Locked Area
        for (int i = 0; i < getHeight() - 1; i++)
        {
            for (int j = 0; j < getWidth(); j++)
            {
                if(j == getWidth() / 4)
                {
                    movingArea[i][j] = '|';
                }
                
                //Wall of Book room
                if(j == getWidth() / 2 + 15 && i != 9)
                {
                    movingArea[i][j] = '|';
                }
                
                if(j == getWidth() - 1)
                {
                    movingArea[i][j] = ' ';
                }
            }
        }
        
        //Book Room's wall
        int j;
        for (int i = 0; i < getHeight() - 1; i++)
        {
            j = getWidth() / 2 + 15 + i * 2;
            movingArea[i][j] = '\\';
        }
        
        //Hints in Book room
        movingArea[4][getWidth() - 19] = '@';
        
        //Locked area
        movingArea[getHeight() / 2 - 1][getWidth() / 4] = '#';
        
        //Drawing table
        for(int row = getWidth() / 2 - 2; row < getWidth() / 2 + 3; row++)
        {
            movingArea[getHeight() - 4][row] = '_';
        }
        
        movingArea[getHeight() - 3][getWidth() / 2 - 3] = '|';
        movingArea[getHeight() - 3][getWidth() / 2 - 2] = 'C';
        movingArea[getHeight() - 3][getWidth() / 2 - 1] = 'h';
        movingArea[getHeight() - 3][getWidth() / 2] = 'a';
        movingArea[getHeight() - 3][getWidth() / 2 + 1] = 'i';
        movingArea[getHeight() - 3][getWidth() / 2 + 2] = 'r';
        movingArea[getHeight() - 3][getWidth() / 2 + 3] = '|';
        
        positionNPC('V');
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
                        if(j < getWidth() / 4 || j > getWidth() / 2 + 14)
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
    
    @Override
    public void printEntrance(String roomName)
    {
        //Print left side
        //Divide the width and roomName length in half to print in the middle
        //The +1 is for the | character
        for (int pos = 0; pos < this.getWidth() / 2 - (roomName.length() / 2 + 1); pos++)
        {
            System.out.print("_");
        }

        //Print room name in the middle
        System.out.print(String.format("|%s|", roomName));

        //Print right side
        //The +2 is for the | character and the next position
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth() - 20; pos++)
        {
            System.out.print("_");
        }

        System.out.println("");
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
            if (wid == 0 || wid == getWidth() - 1 || wid == 18 || wid == getWidth() / 2 + 15
                    || wid == getWidth() / 2 - 3 || wid == getWidth() / 2 + 3)
            {
                System.out.print("|");
            } 
            else
            {
                System.out.print("_");
            }
        }

        System.out.println();
    }
}
