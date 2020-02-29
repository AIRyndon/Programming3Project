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
        initializeMovingArea();
    }

    public boolean unlock(int key) {

        if (key == lockedAreaKey) {
            return true;
        }
        return false;

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
