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
public abstract class Room {

    protected int width;
    protected int height;
    protected String name;
    public char[][] movingArea;
    protected int xInitial;
    protected int yInitial;

    public Room() {
   
    }

    protected void initializeMovingArea() {
        this.movingArea = new char[getHeight() - 1][getWidth()];
        xInitial = 0;
        yInitial = movingArea[0].length/2;

        //Loops for empty movingArea (ground with wall and gate only)
        for (int i = 0; i < this.getHeight() - 2; i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (j == 0 || j == getWidth() - 1) {
                    movingArea[i][j] = '|';
                } else if (i == 0 && j == getWidth() / 2) {
                    movingArea[i][j] = 'P';
                } else {
                    movingArea[i][j] = ' ';
                }
            }
        }
    }

    protected void printRoom(String door) {
        //Print first line
        printEntrance(door);

        //Loops for movingArea  
        for (int i = 0; i < this.getHeight() - 2; i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                System.out.print(movingArea[i][j]);
            }

            System.out.println("");
        }
    }

    public void printEntrance(String roomName) {
        //Print left side
        //Divide the width and roomName length in half to print in the middle
        //The +1 is for the | character
        for (int pos = 0; pos < this.getWidth() / 2 - (roomName.length() / 2 + 1); pos++) {
            System.out.print("_");
        }

        //Print room name in the middle
        System.out.print(String.format("|%s|", roomName));

        //Print right side
        //The +2 is for the | character and the next position
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth(); pos++) {
            System.out.print("_");
        }

        System.out.println("");
    }

    protected void printWall() {
        //Print gate and wall (first row)
        for (int wid = 0; wid < this.getWidth(); wid++) {
            if (wid == 0 || wid == getWidth() - 1) {
                System.out.print("|");
            } else {
                System.out.print("_");
            }
        }

        System.out.println();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
