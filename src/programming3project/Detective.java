/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author airyn
 */
public class Detective extends Person 
{
    //player position in the grid
    private int xCoord;
    private int yCoord;
    private String background;
    public Actions playerActions;
    private char[][] playArea;
    private Room currentRoom;

    public Detective(String name, char gender, int age, char[][] playArea, Room currentRoom)
    {
        super(name, gender, age);
        this.playArea = playArea;
        this.currentRoom = currentRoom;

        //set initial position
        xCoord = 0;
        yCoord = playArea[0].length / 2;

        /*
         after pressing w,a,s,d check content of movingArea, if its a wall, don't change position else, change position or grab an item if available
        
         //pass the movingArea back to the area you are in, and let it redraw
        */
    }

    public void move(char keyPress) 
    {
        char item = '\0';

        if ((yCoord > 0 && yCoord < playArea[0].length - 1) || (xCoord > 0 && xCoord < playArea.length - 2)) 
        {
            if (keyPress == 'a') 
            {
                item = playArea[xCoord][yCoord - 1];
               
                if (item == ' ')
                {
                    playArea[xCoord][yCoord] = ' ';
                    yCoord -= 1;
                    playArea[xCoord][yCoord] = 'P';
                }
            }
            else if (keyPress == 'd')
            {
                item = playArea[xCoord][yCoord + 1];
                
                if (item == ' ') 
                {
                    playArea[xCoord][yCoord] = ' ';
                    yCoord += 1;
                    playArea[xCoord][yCoord] = 'P';
                }
            } 
            else if (keyPress == 'w') 
            {
                item = playArea[xCoord - 1][yCoord];
                
                if (item == ' ') 
                {

                    playArea[xCoord][yCoord] = ' ';
                    xCoord -= 1;
                    playArea[xCoord][yCoord] = 'P';
                }
            } 
            else if (keyPress == 's') 
            {
                item = playArea[xCoord + 1][yCoord];
                
                if (item == ' ') 
                {
                    playArea[xCoord][yCoord] = ' ';
                    xCoord += 1;
                    playArea[xCoord][yCoord] = 'P';
                }
            }
        }

        getCurrentRoom().printRoom("Room");
    }

    /**
     * @return the background
     */
    public String getBackground() 
    {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) 
    {
        this.background = background;
    }

    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(Room currentRoom) 
    {
        this.currentRoom = currentRoom;
    }
    
    public char[][] getPlayArea()
    {
        return playArea;
    }
    
    public void setPlayArea(char[][] playArea)
    {
        this.playArea = playArea;
    }
    
    @Override
    public String toString() 
    {
        String output = "";

        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";//Do we need to create another attribute for age? => Then ask user
        output += "Background: " + this.getBackground() + "\n\n";

        return output;
    }
}
