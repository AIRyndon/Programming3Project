/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.function.Consumer;

/**
 *
 * @author airyn
 */
public class Detective extends Person
{
    //player position in the grid
    private int xPrevious;
    private int yPrevious;
    private int xCoord;
    private int yCoord;
    private int grabbedHints = 0;
    private String background;
    private char[][] playArea;
    private Room currentRoom;
    private Room previousRoom;

    public Detective(String name, char gender, int age, Room room)
    {
        super(name, gender, age);

        //set initial position
        xCoord = room.getxInitial();
        yCoord = room.getyInitial();
    }

    public char move(char keyPress)
    {
        char item = '\0';

        switch (keyPress)
        {
            case 'a':

                if (inCorner(yCoord, 0))
                {
                    return '\0';
                }

                item = playArea[xCoord][yCoord - 1];
                if (item == ' ')
                {
                    if (startingInHouse())
                    {
                        updatePlayArea('*', 'P', value -> yCoord -= value);
                    } else
                    {
                        updatePlayArea(' ', 'P', value -> yCoord -= value);
                    }
                } else if (item == 'X' || item == '*')
                {
                    //todo - how to know what hint should be spit out.
                    updatePlayArea(' ', 'P', value -> yCoord -= value);
                }

                break;

            case 'd':

                if (inCorner(yCoord, playArea[0].length - 1))
                {
                    return '\0';
                }

                item = playArea[xCoord][yCoord + 1];
                if (item == ' ')
                {
                    //if at starting location, print the item used to get back
                    if (startingInHouse())
                    {
                        updatePlayArea('*', 'P', value -> yCoord += value);

                    } else
                    {
                        updatePlayArea(' ', 'P', value -> yCoord += value);
                    }
                } else if (item == 'X' || item == '*')
                {
                    updatePlayArea(' ', 'P', value -> yCoord += value);
                }

                break;

            case 'w':

                if (inCorner(xCoord, 0))
                {
                    return '\0';
                }

                item = playArea[xCoord - 1][yCoord];
                if (item == ' ')
                {
                    if (startingInHouse())
                    {
                        updatePlayArea('*', 'P', value -> xCoord -= value);

                    } 
                    else
                    {
                        updatePlayArea(' ', 'P', value -> xCoord -= value);
                    }
                } 
                else if (item == 'X' || item == '*')
                {
                    updatePlayArea(' ', 'P', value -> xCoord -= value);
                }

                break;

            case 's':

                if (inCorner(xCoord, playArea.length - 1))
                {
                    return '\0';
                }

                item = playArea[xCoord + 1][yCoord];

                if (item == ' ')
                {
                    if (startingInHouse())
                    {
                        updatePlayArea('*', 'P', value -> xCoord += value);

                    } 
                    else
                    {
                        updatePlayArea(' ', 'P', value -> xCoord += value);
                    }
                } 
                else if (item == 'X' || item == '*')
                {
                    updatePlayArea(' ', 'P', value -> xCoord += value);
                }

                break;

            default:
                break;
        }

        currentRoom.printRoom(currentRoom.getName());
        return item;
    }

    public void moveToAnotherRoom(Room newRoom)
    {
        getCurrentRoom().xCurrent = getxCoord();
        getCurrentRoom().yCurrent = getyCoord();
        setPlayArea(newRoom.movingArea);

        if (newRoom.getPreviousRoom() != null)
        {
            setPreviousRoom(newRoom.getPreviousRoom());
        }

        setCurrentRoom(newRoom);
        getCurrentRoom().printRoom(getCurrentRoom().getName());
    }

    public void setLocationToPreviousRoom()
    {
        xCoord = getCurrentRoom().xCurrent;
        yCoord = getCurrentRoom().yCurrent;
        xPrevious = getPreviousRoom().xCurrent;
        yPrevious = getPreviousRoom().yCurrent;
    }

    public void setLocationToNewRoom()
    {
        xPrevious = getPreviousRoom().xCurrent;
        yPrevious = getPreviousRoom().yCurrent;
        xCoord = getCurrentRoom().getxInitial();
        yCoord = getCurrentRoom().getyInitial();
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
        this.playArea = null;
        this.playArea = playArea;
    }

    /**
     * @return the previousRoom
     */
    public Room getPreviousRoom()
    {
        return previousRoom;
    }

    /**
     * @param previousRoom the previousRoom to set
     */
    public void setPreviousRoom(Room previousRoom)
    {
        this.previousRoom = previousRoom;
    }

    /**
     * @return the xPrevious
     */
    public int getxPrevious()
    {
        return xPrevious;
    }

    /**
     * @return the yPrevious
     */
    public int getyPrevious()
    {
        return yPrevious;
    }

    /**
     * @return the xCoord
     */
    public int getxCoord()
    {
        return xCoord;
    }

    /**
     * @return the yPrevious
     */
    public int getyCoord()
    {
        return yCoord;
    }

    /**
     * @return the grabbedHints
     */
    public int getGrabbedHints()
    {
        return grabbedHints;
    }

    public void incrementGrabbedHints()
    {
        ++grabbedHints;
    }

    private boolean inCorner(int coordinate, int worldEdge)
    {
        if (coordinate == worldEdge)
        {
            currentRoom.printRoom(currentRoom.getName());
            return true;
        }

        return false;
    }

    private boolean startingInHouse()
    {
        return (xCoord == getCurrentRoom().getxInitial()
                && yCoord == getCurrentRoom().getyInitial()
                && !getCurrentRoom().getName().equals("Ground"));
    }

    private void updatePlayArea(Character oldValue, Character newValue,
                            Consumer<Integer> action)
    {
        playArea[xCoord][yCoord] = oldValue;
        action.accept(1);
        playArea[xCoord][yCoord] = newValue;
    }

    @Override
    public String toString()
    {
        String output = "";
        
        output += "\nDetective information\n";
        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";
        output += "Background: " + this.getBackground() + "\n";

        return output;
    }
}