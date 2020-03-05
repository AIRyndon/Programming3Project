package programming3project;

import java.util.Random;

/**
 *
 * @author pc
 */
public class House extends Room implements LockedArea {

    /*we match the key passed to Unlock in the class that uses House
    * the key could come from clues. I made it an interface so we can show that we
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

    protected void initializeMovingArea() {
        super.initializeMovingArea();

        for (int row = 0; row < 15; row++) {
            
            if (row == 2) {
                
                movingArea[row][width / 2 - 18] = 'B';
                movingArea[row][width / 2 - 17] = 'E';
                movingArea[row][width / 2 - 16] = 'D';
                movingArea[row][width / 2 - 15] = 'R';
                movingArea[row][width / 2 - 14] = 'O';
                movingArea[row][width / 2 - 13] = 'O';
                movingArea[row][width / 2 - 12] = 'M';
                
                movingArea[row][width / 2 + 10] = 'K';
                movingArea[row][width / 2 + 11] = 'I';
                movingArea[row][width / 2 + 12] = 'T';
                movingArea[row][width / 2 + 13] = 'C';
                movingArea[row][width / 2 + 14] = 'H';
                movingArea[row][width / 2 + 15] = 'E';
                movingArea[row][width / 2 + 16] = 'N';                                        
            }
            
            if (row == 7) {
                movingArea[row][width / 2 - 18] = 'B';
                movingArea[row][width / 2 - 17] = 'A';
                movingArea[row][width / 2 - 16] = 'T';
                movingArea[row][width / 2 - 15] = 'H';
                movingArea[row][width / 2 - 14] = 'R';
                movingArea[row][width / 2 - 13] = 'O';
                movingArea[row][width / 2 - 12] = 'O';
                movingArea[row][width / 2 - 11] = 'M';
                
                movingArea[row][width / 2 + 10] = 'L';
                movingArea[row][width / 2 + 11] = 'I';
                movingArea[row][width / 2 + 12] = 'V';
                movingArea[row][width / 2 + 13] = 'I';
                movingArea[row][width / 2 + 14] = 'N';
                movingArea[row][width / 2 + 15] = 'G';
                movingArea[row][width / 2 + 16] = ' ';
                movingArea[row][width / 2 + 17] = 'R';
                movingArea[row][width / 2 + 18] = 'O';
                movingArea[row][width / 2 + 19] = 'O';
                movingArea[row][width / 2 + 20] = 'M';
            }
            
            if (row == 12) {
                movingArea[row][width / 2 - 18] = 'B';
                movingArea[row][width / 2 - 17] = 'E';
                movingArea[row][width / 2 - 16] = 'D';
                movingArea[row][width / 2 - 15] = 'R';
                movingArea[row][width / 2 - 14] = 'O';
                movingArea[row][width / 2 - 13] = 'O';
                movingArea[row][width / 2 - 12] = 'M';
                
                movingArea[row][width / 2 + 10] = 'B';
                movingArea[row][width / 2 + 11] = 'E';
                movingArea[row][width / 2 + 12] = 'D';
                movingArea[row][width / 2 + 13] = 'R';
                movingArea[row][width / 2 + 14] = 'O';
                movingArea[row][width / 2 + 15] = 'O';
                movingArea[row][width / 2 + 16] = 'M';
            }
                    
            if (row == 4 || row == 9 || row == 14) {

                movingArea[row][width / 2 - 3] = ' ';
                movingArea[row][width / 2 + 3] = ' ';

                for (int col = 1; col < width / 2 - 3; col++) {
                    movingArea[row][col] = '_';
                }

                for (int col = width / 2 + 4; col < width - 1; col++) {
                    movingArea[row][col] = '_';
                }
                
            } else {
                movingArea[row][width / 2 - 3] = '|';
                movingArea[row][width / 2 + 3] = '|';
            }
        }
    }

    /**
     * Printing the house
     */
    @Override
    protected void printRoom() {

        printEntrance("House");

        for (int i = 0; i < this.height - 2; i++) {
            for (int j = 0; j < this.width; j++) {
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

    protected void moving(char move) {

    }

    /**
     * create random hints
     */
    protected void hints() {
        //Randomly assign some hint positions in the ground 
        Random rand = new Random();

        //Need to check if those two have the same position
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
        movingArea[rand.nextInt(height - 2)][rand.nextInt(width - 2)] = 'X';
    }
}
