/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Ground extends Room {

    public Ground(String name) {
              
        setName(name);
        setHeight(24);
        setWidth(52);
        initializeMovingArea();
    }

    /**
     * initialize MovingArea Create a 2D array representing for hints(X), space(
     * ), and player(P) to allow and show moving
     */
    @Override
    protected void initializeMovingArea() {
        super.initializeMovingArea();

        //Assign dog's place
        movingArea[0][getWidth() - 15] = '|';
        movingArea[0][getWidth() - 14] = '_';
        movingArea[1][getWidth() - 14] = '/';
        movingArea[1][getWidth() - 9] = 'D';
        movingArea[1][getWidth() - 8] = 'O';
        movingArea[1][getWidth() - 7] = 'G';
        movingArea[2][getWidth() - 15] = '|';

        for (int i = getWidth() - 14; i < getWidth() - 1; i++) {
            movingArea[2][i] = '_';
        }

        /**
         * Assign HouseArea (because the house is on the ground) Player cannot
         * move in this area, just access through the door
         */
        for (int i = 7; i <= 14; i++) {
            for (int j = 17; j < 35; j++) {
                if (i == 7) //House's door
                {
                    if (j <= 17 + 6 || j >= 17 + 12) {
                        movingArea[i][j] = '_';
                    } else if (j == (17 + 7) || j == (17 + 11)) {
                        movingArea[i][j] = '|';
                    } else {
                        movingArea[i][j] = '/';
                    }
                } else if (i == 14) {
                    if (j == 17 || j == 34) {
                        movingArea[i][j] = '|';
                    } else {
                        movingArea[i][j] = '_';
                    }
                } else //House's area
                {
                    if (j == 17 || j == 34) //House's left and right wall (barrier)
                    {
                        movingArea[i][j] = '|';
                    } else //Empty area inside the house
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

        //One hint inside the dog's house
        movingArea[0][getWidth() - 3] = 'X';

        //The butler is in the ground
        relativePosition();
    }

    /**
     * create random relative position
     */
    protected void relativePosition() {
        //Randomly assign some hint positions in the ground 
        Random rand = new Random();

        int firstHei = rand.nextInt(getHeight() - 2);
        int firstWid = rand.nextInt(getWidth() - 2);
        int secondHei = rand.nextInt(getHeight() - 2);
        int secondWid = rand.nextInt(getWidth() - 2);

        //Check if those two positions are in the same position
        //Position replace space only
        boolean checkedFirst = false;
        boolean checkedSecond = false;
        while (!(checkedFirst && checkedSecond)) {
            if (firstHei != secondHei || firstWid != secondWid) {
                if (movingArea[firstHei][firstWid] == ' ' && movingArea[secondHei][secondWid] == ' ') {
                    if (firstHei >= 7 && firstHei <= 14 && firstWid >= 17 && firstWid <= 35) {
                        firstHei = rand.nextInt(getHeight() - 2);
                        firstWid = rand.nextInt(getWidth() - 2);
                    } else {
                        checkedFirst = true;
                    }

                    if (secondHei >= 7 && secondHei <= 14 && secondWid >= 17 && secondWid <= 35) {
                        secondHei = rand.nextInt(getHeight() - 2);
                        secondWid = rand.nextInt(getWidth() - 2);
                    } else {
                        checkedSecond = true;
                    }
                }
            }
        }

        //Assign position 
        movingArea[firstHei][firstWid] = 'B';
        movingArea[secondHei][secondWid] = 'W';
    }

    /**
     * Printing the ground
     */
    @Override
    public void printRoom(String door) 
    {
        super.printRoom(door);

        printWall();
    }
}
