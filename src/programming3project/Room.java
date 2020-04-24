/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.ArrayList;

/**
 *
 * @author group
 */
public abstract class Room
{
    public ArrayList<GroundHint> hints = new ArrayList<>();
    protected int width;
    protected int height;
    protected String name;
    protected char[][] movingArea;
    protected Room previousRoom;
    protected Password lock;
    protected int xInitial;
    protected int yInitial;
    protected int xCurrent;
    protected int yCurrent;

    public Room(Room previous)
    {
        previousRoom = previous;
    }

    protected void initializeMovingArea()
    {
        this.movingArea = new char[getHeight() - 1][getWidth()];
        setxInitial(0);
        setyInitial(movingArea[0].length / 2);

        //Loops for empty movingArea (ground with wall and gate only)
        for (int i = 0; i < this.getHeight() - 2; i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if (j == 0 || j == getWidth() - 1)
                {
                    movingArea[i][j] = '|';
                } 
                else if (i == 0 && j == getWidth() / 2)
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

    protected void positionNPC(char person)
    {        
        int y = Game.RANDOM.nextInt(getHeight() - 2);
        int x = Game.RANDOM.nextInt(getWidth() - 2);

        while(movingArea[y][x] != ' ')
        {   
            y = Game.RANDOM.nextInt(getHeight() - 2);
            x = Game.RANDOM.nextInt(getWidth() - 2);
        }
        
        movingArea[y][x] = person;
    }
    
    public void checkPassword()
    {
        this.getLock().promtPassword();
        
        //Remove '#' when user inputs a correct password
        if(this.getLock().isUnlock())
        {        
            removeCharacter('#');
            printRoom(this.getName());
            System.out.println("Congratulations! Your password is correct!");
            System.out.println("DOOR UNLOCKED!");
        }
    }
    
    public void removeCharacter(char character)
    {
        for(int i = 0; i < this.getHeight() - 1; i++)
        {
            for(int j = 0; j < this.getWidth(); j++)
            {
                if(movingArea[i][j] == character)
                {
                    movingArea[i][j] = ' ';

                    break;
                }
            }
        }
    }
    
    protected void printRoom(String door)
    {
        //Print first line
        printEntrance(door);

        //Loops for movingArea  
        for (int i = 0; i < this.getHeight() - 2; i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                System.out.print(movingArea[i][j]);
            }

            System.out.println("");
        }
        
        printWall();
    }

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
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth(); pos++)
        {
            System.out.print("_");
        }

        System.out.println("");
    }

    protected void printWall()
    {
        //Print gate and wall (first row)
        for (int wid = 0; wid < this.getWidth(); wid++)
        {
            if (wid == 0 || wid == getWidth() - 1)
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

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @return the previousRoom
     */
    public Room getPreviousRoom() 
    {
        return previousRoom;
    }

    /**
     * @param previousRoom the previousRoom to set
     */
    public void setPreviousRoom(Room previousRoom) 
    {
        this.previousRoom = previousRoom;
    }

    /**
     * @return the lock
     */
    public Password getLock() 
    {
        return lock;
    }

    /**
     * @param lock the lock to set
     */
    public void setLock(Password lock) {
        this.lock = lock;
    }

    /**
     * @return the xInitial
     */
    public int getxInitial() {
        return xInitial;
    }

    /**
     * @param xInitial the xInitial to set
     */
    public void setxInitial(int xInitial) {
        this.xInitial = xInitial;
    }

    /**
     * @return the yInitial
     */
    public int getyInitial() {
        return yInitial;
    }

    /**
     * @param yInitial the yInitial to set
     */
    public void setyInitial(int yInitial) {
        this.yInitial = yInitial;
    }

    /**
     * @return the xCurrent
     */
    public int getxCurrent() {
        return xCurrent;
    }

    /**
     * @param xCurrent the xCurrent to set
     */
    public void setxCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    /**
     * @return the yCurrent
     */
    public int getyCurrent() {
        return yCurrent;
    }

    /**
     * @param yCurrent the yCurrent to set
     */
    public void setyCurrent(int yCurrent) {
        this.yCurrent = yCurrent;
    }     
}
