/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.Random;

/**
 *
 * @author pc
 */
public class House extends Room implements LockedArea {

    /*we match the key passed to Unlock in the class that uses House
    *the key could come from clues. I made it an interface so we can show that we
    * are using interfaces :). And a house can be composed of a lockedRoom, so it kinda makes sense
     */
    private int lockedAreaKey;

    public House(int width, int height) {
        super(width, height);
    }

    @Override
    public boolean unlock(int key) {

        if (key == lockedAreaKey) {
            return true;
        }
        return false;

    }
    
    protected void initiliseMovingArea()
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
        
        //Assign dog's place
        movingArea[0][width - 15] = '|';
        movingArea[0][width - 14] = '_';
        movingArea[0][width - 3] = 'X';
        movingArea[1][width - 14] = '_';
        movingArea[1][width - 9] = 'D';
        movingArea[1][width - 8] = 'O';
        movingArea[1][width - 7] = 'G';
        movingArea[2][width - 15] = '|';
        
        for (int i = width - 14; i < width - 1; i++)
        {
            movingArea[2][i] = '_';
        }
        
        /**
        * Assign HouseArea (because the house is on the ground)
        * Player cannot move in this area, just access through the door 
        * P/S: We may change the loop to satisfy all height and weight, 
        *   not just for specific numbers
        */
        for (int i = 7; i <= 14; i++) 
        {
            for (int j = 17; j < 35; j++) 
            {
                if(i == 7) //House's door
                {
                    if(j <= 17 + 6 || j >= 17 + 12)
                    {
                        movingArea[i][j] = '_';   
                    }
                    else if(j == (17 + 7) || j == (17 + 11))
                    {
                        movingArea[i][j] = '|';   
                    }
                    else
                    {
                        movingArea[i][j] = ' ';   
                    }
                }
                else if(i == 14)
                {
                    if(j == 17 || j == 34)
                    {
                        movingArea[i][j] = '|';   
                    }
                    else
                    {
                        movingArea[i][j] = '_';
                    }
                }
                else //House's area
                {
                    if(j == 17 || j == 34) //House's left and right wall (barrier)
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
        
        //Printing the word "HOUSE" - not good because it is made in manual way
        movingArea[18][23] = 'H';
        movingArea[18][24] = 'O';
        movingArea[18][25] = 'U';
        movingArea[18][26] = 'S';
        movingArea[18][27] = 'E';
        
        //Randomly assign some hint positions in the ground 
        Random rand = new Random();
        
        //Need to check if those two have the same position
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
    }
    
    /**
     * Printing the house
     */
    public void printRoom() {
        //Loops for height
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (j == 0 || j == this.getWidth() - 1) {
                    System.out.print("|");
                } else if (i == 0 || i == this.getHeight() - 1) {
                    System.out.print("----");
                } else {
                    System.out.print("    ");
                }
            }

            System.out.println("");
        }
    }
        
    public void printLockedArea() {
        //Loops for height
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (j == 0 || j == this.getWidth() - 1) {
                    System.out.print("|");
                } else if (i == 0 || i == this.getHeight() - 1) {
                    System.out.print("----");
                } else {
                    System.out.print("    ");
                }
            }

            System.out.println("");
        }
    }
}
