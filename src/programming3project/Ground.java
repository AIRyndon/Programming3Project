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
public class Ground extends Room
{
    public Ground()
    {
        this.height = 24;
        this.width = 52;
        initializeMovingArea();     
    }
    
    /**
     * initialize MovingArea
     * Create a 2D array representing for hints(X), space( ), and player(P)
     * to allow and show moving
     */
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();
        
        //Assign dog's place
        movingArea[0][width - 15] = '|';
        movingArea[0][width - 14] = '_';
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
        
        //Printing the word "HOUSE"
        movingArea[11][24] = 'H';
        movingArea[11][25] = 'O';
        movingArea[11][26] = 'U';
        movingArea[11][27] = 'S';
        movingArea[11][28] = 'E';
        
        hints();
    }
    
    /**
     * create random hints
     */
    protected void hints()
    {
        //Randomly assign some hint positions in the ground 
        Random rand = new Random();
        
        int firstHei = rand.nextInt(height - 2);
        int firstWid = rand.nextInt(width - 2);
        int secondHei = rand.nextInt(height - 2);
        int secondWid = rand.nextInt(width - 2);
        
        //Check if those two hints are in the same position
        //Hints replace space only
        //Hints cannot be inside the house
        boolean checkedFirst = false;
        boolean checkedSecond = false;
        while(!(checkedFirst && checkedSecond))
        {
            if(firstHei != secondHei || firstWid != secondWid) 
            {
                if(movingArea[firstHei][firstWid] == ' ' &&  movingArea[secondHei][secondWid] == ' ')
                {
                    if(firstHei >= 7 && firstHei <= 14 && firstWid >= 17 && firstWid <= 35)
                    {
                        firstHei = rand.nextInt(height - 2);
                        firstWid = rand.nextInt(width - 2);
                    }
                    else
                    {
                        checkedFirst = true;
                    }
                    
                    if(secondHei >= 7 && secondHei <= 14 && secondWid >= 17 && secondWid <= 35)
                    {
                        secondHei = rand.nextInt(height - 2);
                        secondWid = rand.nextInt(width - 2);
                    }
                    else
                    {
                        checkedSecond = true;
                    }
                }
            }
        }
        
        //Assign hints 
        movingArea[firstHei][firstWid] = 'B';
        movingArea[secondHei][secondWid] = 'W';
        movingArea[0][width - 3] = 'X'; //One hint inside the dog's house
    }
    
    /**
     * Printing the ground
     */
    @Override
    protected void printRoom()  
    {
        //Print first line
        printEntrance("Gate");
        
        //Loops for movingArea  
        for (int i = 0; i < this.height - 2; i++)
        {
            for (int j = 0; j < this.width; j++)
            {
                System.out.print(movingArea[i][j]);
            }
            
            System.out.println("");
        }
        
        printWall();    
    }
    
    /**
    * Printing the wall
    */
    @Override
    protected void printWall() 
    {
        //Print gate and wall (first row)
        for (int wid = 0; wid < this.width; wid++) {
            if (wid == 0 || wid == width - 1) {
                System.out.print("|");
            } else {
                System.out.print("_");
            }
        }
        
        System.out.println();
    }
        
    @Override
    protected void moving(char move)
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
                    //If player wants to get out of the dog's house
                    if(i == 1 && j == width - 13 && movingArea[i][j] == 'P')
                    {
                        System.out.println("Get out of the dog's house");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 2] = 'P';
                        
                        return;
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i][j - 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 1] = 'P';
                        
                        return;
                    }
                    //If player gots 'X'
                    else if(movingArea[i][j] == 'P' && movingArea[i][j - 1] == 'X')
                    {
                        System.out.println("Hint: ");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 1] = 'P';
                        
                        return;
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
                    //If player wants to access the dog's house
                    if(movingArea[i][j] == 'P' && i == 1 && j == width - 15)
                    {
                        System.out.println("Access the dog's house");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 2] = 'P';
                        
                        return;
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 1] = 'P';
                        
                        return;
                    }
                    //If player gots 'X'
                    else if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == 'X')
                    {
                        System.out.println("Hint: ");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 1] = 'P';
                        
                        return;
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
                    //If player wants to access the house
                    
                    if(i == 6 && (j == 25 || j == 26 || j == 27) && movingArea[i][j] == 'P')
                    {
                        System.out.println("If you want to access the house, please enter 'q'");
                        
                        return;
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i + 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i + 1][j] = 'P';
                        
                        return;
                    }
                    //If player gots 'X'
                    else if(movingArea[i][j] == 'P' && movingArea[i + 1][j] == 'X')
                    {
                        System.out.println("Hint: ");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i + 1][j] = 'P';
                        
                        return;
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
                        
                        return;
                    }             
                    //If player gots 'X'                    
                    else if(movingArea[i][j] == 'P' && movingArea[i - 1][j] == 'X')
                    {
                        System.out.println("Hint: ");
                        
                        movingArea[i][j] = ' ';
                        movingArea[i - 1][j] = 'P';
                        
                        return;
                    }
                }
            }
        }
    }
}
