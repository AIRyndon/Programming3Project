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
public abstract class Room //Room could be interface for now
{

    abstract protected void initializeMovingArea();

    abstract protected void printRoom();

    protected final int width;
    protected final int height;
    protected char[][] movingArea;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void printEntrance(String roomName) {

        //Print left side
        //Divide the width and roomName length in half to print in the middle
        //The +1 is for the | character
        for (int pos = 0; pos < this.width / 2 - (roomName.length() / 2 + 1); pos++) {
            System.out.print("_");
        }

        //Print room name in the middle
        System.out.print(String.format("|%s|", roomName));

        //Print right side
        //The +2 is for the | character and the next position
        for (int pos = this.width / 2 + (roomName.length() / 2 + 2); pos < this.width; pos++) {
            System.out.print("_");
        }

        System.out.println("");
    }

    public void printWall() {
        //Print gate and wall (first row)
        for (int wid = 0; wid < this.width; wid++) {
            if (wid == 0 || wid == width - 1) {
                System.out.print("|");
            } else {
                System.out.print("_");
            }
        }
    }
}
