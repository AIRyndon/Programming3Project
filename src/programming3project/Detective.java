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
public class Detective extends Person {

    //player position in the grid
    private int xInitial;
    private int yInitial;
    private int xPrevious;
    private int yPrevious;
    private int xCoord;
    private int yCoord;
    private String background;
    private Relative currentTarget;
    private char[][] playArea;
    private Room currentRoom;
    private Room previousRoom;

    public Detective(String name, char gender, int age, char[][] playArea) {
        super(name, gender, age);

        //set initial position
        xInitial = 0;
        yInitial = playArea[0].length / 2;
        xCoord = xInitial;
        yCoord = yInitial;
        xPrevious = xInitial;
        yPrevious = yInitial;
    }

    public char move(char keyPress) {

        char item = '\0';

        switch (keyPress) {
            case 'a':

                if (yCoord == 0) {
                    return ' ';
                }

                item = playArea[xCoord][yCoord - 1];
                if (item == ' ') {

                    if (xCoord == getxInitial() && yCoord == getyInitial()) {
                        playArea[xCoord][yCoord] = '*';
                        yCoord -= 1;
                        playArea[xCoord][yCoord] = 'P';
                    } else {
                        playArea[xCoord][yCoord] = ' ';
                        yCoord -= 1;
                        playArea[xCoord][yCoord] = 'P';
                    }
                } else if (item == '*') {
                    playArea[xCoord][yCoord] = ' ';
                    yCoord -= 1;
                    playArea[xCoord][yCoord] = 'P';
                }
                break;

            case 'd':

                if (yCoord == playArea[0].length - 1) {
                    return ' ';
                }

                item = playArea[xCoord][yCoord + 1];
                if (item == ' ') {

                    //if at starting location, print the item used to get back
                    if (xCoord == getxInitial() && yCoord == getyInitial()) {
                        playArea[xCoord][yCoord] = '*';
                        yCoord += 1;
                        playArea[xCoord][yCoord] = 'P';
                    } else {
                        playArea[xCoord][yCoord] = ' ';
                        yCoord += 1;
                        playArea[xCoord][yCoord] = 'P';
                    }
                } else if (item == '*') {
                    playArea[xCoord][yCoord] = ' ';
                    yCoord += 1;
                    playArea[xCoord][yCoord] = 'P';
                }
                break;

            case 'w':

                if (xCoord == 0) {
                    return ' ';
                }

                item = playArea[xCoord - 1][yCoord];
                if (item == ' ') {
                    if (xCoord == getxInitial() && yCoord == getyInitial()) {
                        playArea[xCoord][yCoord] = '*';
                        xCoord -= 1;
                        playArea[xCoord][yCoord] = 'P';
                    } else {
                        playArea[xCoord][yCoord] = ' ';
                        xCoord -= 1;
                        playArea[xCoord][yCoord] = 'P';
                    }

                } else if (item == '*') {
                    playArea[xCoord][yCoord] = ' ';
                    xCoord -= 1;
                    playArea[xCoord][yCoord] = 'P';
                }
                break;

            case 's':

                if (xCoord == playArea.length - 1) {
                    return ' ';
                }

                item = playArea[xCoord + 1][yCoord];
                if (item == ' ') {
                    if (xCoord == getxInitial() && yCoord == getyInitial()) {
                        playArea[xCoord][yCoord] = '*';
                        xCoord += 1;
                        playArea[xCoord][yCoord] = 'P';
                    } else {
                        playArea[xCoord][yCoord] = ' ';
                        xCoord += 1;
                        playArea[xCoord][yCoord] = 'P';
                    }
                } else if (item == '*') {
                    playArea[xCoord][yCoord] = ' ';
                    xCoord += 1;
                    playArea[xCoord][yCoord] = 'P';
                }
                break;

            default:
                break;
        }

        currentRoom.printRoom(currentRoom.getName());

        return item;
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return the currentRoom
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * @param currentRoom the currentRoom to set
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public char[][] getPlayArea() {
        return playArea;
    }

    public void setPlayArea(char[][] playArea) {
        this.playArea = playArea;
    }

    @Override
    public String toString() {
        String output = "";

        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";//Do we need to create another attribute for age? => Then ask user
        output += "Background: " + this.getBackground() + "\n\n";

        return output;
    }

    /**
     * @return the currentTarget
     */
    public Relative getCurrentTarget() {
        return currentTarget;
    }

    /**
     * @param currentTarget the currentTarget to set
     */
    public void setCurrentTarget(Relative currentTarget) {
        this.currentTarget = currentTarget;
    }

    /**
     * @return the previousRoom
     */
    public Room getPreviousRoom() {
        return previousRoom;
    }

    /**
     * @param previousRoom the previousRoom to set
     */
    public void setPreviousRoom(Room previousRoom) {
        this.previousRoom = previousRoom;
    }

    /**
     * @return the xPrevious
     */
    public int getxPrevious() {
        return xPrevious;
    }

    /**
     * @param xPrevious the xPrevious to set
     */
    public void setxPrevious(int xPrevious) {
        this.xPrevious = xPrevious;
    }

    /**
     * @return the yPrevious
     */
    public int getyPrevious() {
        return yPrevious;
    }

    /**
     * @param yPrevious the yPrevious to set
     */
    public void setyPrevious(int yPrevious) {
        this.yPrevious = yPrevious;
    }

    /**
     * @return the xCoord
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * @param xCoord the xCoord to set
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * @return the yPrevious
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * @param yCoord the yCoord to set
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
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
}
