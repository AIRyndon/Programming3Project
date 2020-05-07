package programming3project;

import java.util.function.Consumer;

public class Detective extends Person
{
    // <editor-fold defaultstate="collapsed" desc="Detective attributes">
    private int grabbedHints = 0;
    private int xCoord;
    private int yCoord;
    private String background;
    private char[][] playArea;
    private Room currentRoom;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Detective(String name, char gender, int age, Room room)
    {
        super(name, gender, age);

        xCoord = room.getxInitial();
        yCoord = room.getyInitial();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public void setBackground(String background)
    {
        this.background = background;
    }

    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public int getGrabbedHints()
    {
        return grabbedHints;
    }

    public void setPlayArea(char[][] playArea)
    {
        this.playArea = null;
        this.playArea = playArea;
    }

    public int getxCoord()
    {
        return xCoord;
    }

    public int getyCoord()
    {
        return yCoord;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public void incrementGrabbedHints()
    {
        ++grabbedHints;
    }

    public char move(String keyPress)
    {
        char item = '\0';

        switch (keyPress)
        {
            case "a":
            {
                if (inCorner(yCoord, 0))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[xCoord][yCoord - 1], value -> yCoord -= value);
                break;
            }
            case "d":
            {
                if (inCorner(yCoord, playArea[0].length - 1))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[xCoord][yCoord + 1], value -> yCoord += value);
                break;
            }
            case "w":
            {
                if (inCorner(xCoord, 0))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[xCoord - 1][yCoord], value -> xCoord -= value);
                break;
            }
            case "s":
            {
                if (inCorner(xCoord, playArea.length - 1))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[xCoord + 1][yCoord], value -> xCoord += value);
                break;
            }
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
        setCurrentRoom(newRoom);
        getCurrentRoom().printRoom(getCurrentRoom().getName());
    }

    public void setLocationToNewRoom()
    {
        xCoord = getCurrentRoom().getxInitial();
        yCoord = getCurrentRoom().getyInitial();
    }

    public void setLocationToPreviousRoom()
    {
        xCoord = getCurrentRoom().xCurrent;
        yCoord = getCurrentRoom().yCurrent;
    }

    @Override
    public String toString()
    {
        String output = "";

        output += "\nDetective information\n";
        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' || this.getGender() == 'm'
                ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";
        output += "Background: " + background + "\n";

        return output;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private char changePlayerLocation(char item, Consumer<Integer> action)
    {
        if (item == ' ')
        {
            if (startingInHouse())
            {
                updatePlayArea('*', 'P', action);
            }
            else
            {
                updatePlayArea(' ', 'P', action);
            }
        }
        else if (item == 'X' || item == '*')
        {
            updatePlayArea(' ', 'P', action);
        }

        return item;
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
    // </editor-fold>
}