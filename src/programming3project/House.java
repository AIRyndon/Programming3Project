package programming3project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class House extends Room implements LockedArea 
{
    /*we match the key passed to Unlock in the class that uses House
     * the key could come from clues. I made it an interface so we can show that we
     * are using interfaces :). And a house can be composed of a lockedRoom, so it kinda makes sense
     */
    private int lockedAreaKey;

    public House()
    {
        this.height = 20;
        this.width = 52;
        initializeMovingArea(); 
    }

    public boolean unlock(int key) 
    {
        if (key == lockedAreaKey)
        {
            return true;
        }

        return false;
    }

    protected void initializeMovingArea()
    {
        super.initializeMovingArea();
        
        for(int i = 0; i < height - 1; i++)
        {
            for(int j = 0; j < width - 1; j++)
            {
                if(i <= 5)
                {
                    if(i == 1)
                    {
                        //MAID'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][6] = 'M';
                        movingArea[i][7] = 'A';
                        movingArea[i][8] = 'I';
                        movingArea[i][9] = 'D';
                        movingArea[i][10] = '\'';
                        movingArea[i][11] = 'S';
                        movingArea[i][13] = 'R';
                        movingArea[i][14] = 'O';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'M';
                    }
                    else if(i == 2)
                    {
                        if(j >= 1 && j <= width / 2 - 3)
                        {
                            movingArea[i][j] = '_';   
                        }
                        
                        //KITCHEN
                        movingArea[i][width / 2 - 3] = '/';
                        movingArea[i][width / 2 + 11] = 'K';
                        movingArea[i][width / 2 + 12] = 'I';
                        movingArea[i][width / 2 + 13] = 'T';
                        movingArea[i][width / 2 + 14] = 'C';
                        movingArea[i][width / 2 + 15] = 'H';
                        movingArea[i][width / 2 + 16] = 'E';
                        movingArea[i][width / 2 + 17] = 'N';
                        movingArea[i][width - 1] = '='; 

                    }
                    else if(i == 5)
                    {
                        if((j >= 1 && j <= width / 2 - 4) || (j >= width / 2 + 11 && j < width - 1))
                        {
                            movingArea[i][j] = '_';
                        }  
                        else
                        {
                            movingArea[i][width / 2 - 3] = '/';
                        }
                    }
                    else if(i == 4)
                    {
                        //BUTLER'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][5] = 'B';
                        movingArea[i][6] = 'U';
                        movingArea[i][7] = 'T';
                        movingArea[i][8] = 'L';
                        movingArea[i][9] = 'E';
                        movingArea[i][10] = 'R';
                        movingArea[i][11] = '\'';
                        movingArea[i][12] = 'S';
                        movingArea[i][14] = 'R';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'O';
                        movingArea[i][17] = 'M'; 
                    }
                    
                    if (j == width / 2 - 3 || j == width / 2 + 3)
                    {
                        movingArea[i][j] = '|';
                    }
                }
                else if(i <= 12 )
                {
                    if(i == 9)
                    {
                        //WIFE'S ROOM
                        movingArea[i][0] = '=';
                        movingArea[i][6] = 'W';
                        movingArea[i][7] = 'I';
                        movingArea[i][8] = 'F';
                        movingArea[i][9] = 'E';
                        movingArea[i][10] = '\'';
                        movingArea[i][11] = 'S';
                        movingArea[i][13] = 'R';
                        movingArea[i][14] = 'O';
                        movingArea[i][15] = 'O';
                        movingArea[i][16] = 'M';
                        
                        //Door
                        movingArea[i][width / 2 - 3] = '/';                        
                        
                        //LIVING ROOM
                        movingArea[i][width / 2 + 6] = 'L';
                        movingArea[i][width / 2 + 7] = 'I';
                        movingArea[i][width / 2 + 8] = 'V';
                        movingArea[i][width / 2 + 9] = 'I';
                        movingArea[i][width / 2 + 10] = 'N';
                        movingArea[i][width / 2 + 11] = 'G';
                        movingArea[i][width / 2 + 13] = 'R';
                        movingArea[i][width / 2 + 14] = 'O';
                        movingArea[i][width / 2 + 15] = 'O';
                        movingArea[i][width / 2 + 16] = 'M';
                    }
                    if(i == 12)
                    {
                        if(j >= 1 && j <= width / 2 + 8)
                        {
                            movingArea[i][j] = '_';                        
                        }
                        
                        movingArea[i][width / 2 - 3] = '|';                        
                        movingArea[i][width / 2 - 2] = '\\';                        
                    }
                    else if(j == width / 2 - 3)
                    {
                        movingArea[i][j] = '|';  
                    }
                    else if(j == width - 1)
                    {
                        movingArea[i][j] = '=';  
                    }
                }
                else if(i == 14)
                {
                    movingArea[i][12] = '|';        
                    movingArea[i][width / 2 + 9] = '|';
                    movingArea[i][width / 2 + 10] = '\\';
                }
                else if(i == 15)
                {
                    //LOCKED AREA
                    movingArea[i][3] = 'L';
                    movingArea[i][4] = 'O';
                    movingArea[i][5] = 'C';
                    movingArea[i][6] = 'K';
                    movingArea[i][7] = 'E';
                    movingArea[i][8] = 'D';
                    
                    //LOCKED DOOR
                    movingArea[i][12] = '#';
                    
                    //WORKING ROOM
                    movingArea[i][width / 2 - 6] = 'W';
                    movingArea[i][width / 2 - 5] = 'O';
                    movingArea[i][width / 2 - 4] = 'R';
                    movingArea[i][width / 2 - 3] = 'K';
                    movingArea[i][width / 2 - 2] = 'I';
                    movingArea[i][width / 2 - 1] = 'N';
                    movingArea[i][width / 2] = 'G';
                    
                    //BOOK ROOM
                    movingArea[i][width / 2 + 9] = '|';
                    movingArea[i][width / 2 + 10] = 'B';
                    movingArea[i][width / 2 + 11] = '\\';
                }
                else if(i == 16)
                {
                    //LOCKED AREA
                    movingArea[i][4] = 'A';
                    movingArea[i][5] = 'R';
                    movingArea[i][6] = 'E';
                    movingArea[i][7] = 'A';
                    
                    //WALL
                    movingArea[i][12] = '|';
                    
                    //WORKING ROOM
                    movingArea[i][width / 2 - 4] = 'R';
                    movingArea[i][width / 2 - 3] = 'O';
                    movingArea[i][width / 2 - 2] = 'O';
                    movingArea[i][width / 2 - 1] = 'M';

                    //BOOK ROOM
                    movingArea[i][width / 2 + 9] = '/';
                    movingArea[i][width / 2 + 10] = 'O';
                    movingArea[i][width / 2 + 11] = 'O';
                    movingArea[i][width / 2 + 12] = '\\';
                }
                else if(i == 17)
                {
                    //WALL
                    movingArea[i][12] = '|';
                    
                    //BOOK ROOM
                    movingArea[i][width / 2 + 9] = '|';
                    movingArea[i][width / 2 + 10] = 'K';
                    movingArea[i][width / 2 + 11] = 'S';
                    movingArea[i][width / 2 + 13] = '\\';
                }
                else 
                {
                    if(j == width / 2 - 14 || j == width / 2 + 9)
                    {
                        movingArea[i][j] = '|'; 
                    }
                }
            }
        }
    }

    /**
     * Printing the house
     */
    @Override
    protected void printRoom() 
    {
        printEntrance("House");

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
        for (int wid = 0; wid < this.width; wid++) 
        {
            if (wid == 0 || wid == width - 1 || wid == 12 || 
                    wid == width / 2 + 9 || wid == width / 2 + 14) 
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

    public void printLockedArea()
    {
        //Loops for height
        for (int i = 0; i < this.getHeight(); i++) 
        {
            for (int j = 0; j < this.getWidth(); j++) 
            {
                if (j == 0 || j == this.getWidth() - 1)
                {
                    System.out.print("|");
                } 
                else if (i == 0 || i == this.getHeight() - 1)
                {
                    System.out.print("----");
                } 
                else 
                {
                    System.out.print("    ");
                }
            }

            System.out.println("");
        }
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
                    //If player wants to access MAID'S ROOM
                    if(i == 2 && j == width / 2 - 2 && movingArea[i][j] == 'P')
                    {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 'y' to access the maid's room");
                        boolean accessPlace = "y".equalsIgnoreCase(scan.nextLine()); 
                        //Using throw - catch to test user input

                        //Check if player wants to access the house
                        if(accessPlace)
                        {
                            return 3;
                        }
                        else
                        {
                            return 2;
                        }
                    }
                    //If player wants to access BUTLER'S ROOM
                    if(i == 5 && j == width / 2 - 2 && movingArea[i][j] == 'P')
                    {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 'y' to access the butler's room");
                        boolean accessPlace = "y".equalsIgnoreCase(scan.nextLine()); 
                        //Using throw - catch to test user input

                        //Check if player wants to access the house
                        if(accessPlace)
                        {
                            return 4;
                        }
                        else
                        {
                            return 2;
                        }
                    }
                    //If player wants to access WIFE'S ROOM 
                    if(i == 9 && j == width / 2 - 2 && movingArea[i][j] == 'P')
                    {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 'y' to access the wife's room");
                        boolean accessPlace = "y".equalsIgnoreCase(scan.nextLine()); 
                        //Using throw - catch to test user input

                        //Check if player wants to access the house
                        if(accessPlace)
                        {
                            return 5;
                        }
                        else
                        {
                            return 2;
                        }
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i][j - 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j - 1] = 'P';
                        
                        return 2;
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
                    //If player hits '='
                    if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == '=')
                    {
                        System.out.println("That is a window!");
                        
                        return 2;
                    }
                    
                    //change player's location
                    if(movingArea[i][j] == 'P' && movingArea[i][j + 1] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i][j + 1] = 'P';
                        
                        return 2;
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
                    //If player wants to access WORKING ROOM
                    if(i == 11 && j == width / 2 - 2 && movingArea[i][j] == 'P')
                    {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 'y' to access the working room");
                        boolean accessPlace = "y".equalsIgnoreCase(scan.nextLine()); 
                        //Using throw - catch to test user input

                        //Check if player wants to access the house
                        if(accessPlace)
                        {
                            return 6;
                        }
                        else
                        {
                            return 2;
                        }
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i + 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i + 1][j] = 'P';
                        
                        return 2;
                    }
                }
            }
        }
        else if (move == 'w') 
        {
            for (int i = 0; i < height - 1; i++)
            {
                for (int j = 0; j < width; j++) 
                {
                    //Get out of the house
                    if(i == 0 && j >= width / 2 - 2 && j <= width / 2 + 2 && movingArea[i][j] == 'P')
                    {
                        Scanner scan = new Scanner(System.in);
                        System.out.println("Press 'y' to get out of the house");
                        boolean accessPlace = "y".equalsIgnoreCase(scan.nextLine()); 
                        //Using throw - catch to test user input

                        //Check if player wants to access the house
                        if(accessPlace)
                        {
                            return 1;
                        }
                        else
                        {
                            return 2;
                        }
                    }
                    //change player's location
                    else if(movingArea[i][j] == 'P' && movingArea[i - 1][j] == ' ')
                    {
                        movingArea[i][j] = ' ';
                        movingArea[i - 1][j] = 'P';
                        
                        return 2;
                    }
                }
            }
        }
        
        return 2;
    }

    /**
     * create random hints
     */
    protected void hints()
    {
        //Randomly assign some hint positions in the ground 
        Random rand = new Random();

        //Need to check if those two have the same position
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
    }
}
