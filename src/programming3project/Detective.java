package programming3project;

import java.util.function.Consumer;

public class Detective extends Person
{
    // <editor-fold defaultstate="collapsed" desc="Detective attributes">
    private int grabbedHints = 0;
    private int rowCoord;
    private int colCoord;
    private String background;
    private char[][] playArea;
    private Room currentRoom;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Detective(String name, char gender, int age, Room room)
    {
        super(name, gender, age);

        rowCoord = room.getRowInitialCoord();
        colCoord = room.getColInitialCoord();
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

    public int getRowCoord()
    {
        return rowCoord;
    }

    public int getColCoord()
    {
        return colCoord;
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
                if (inCorner(colCoord, 0))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[rowCoord][colCoord - 1], value -> colCoord -= value);
                break;
            }
            case "d":
            {
                if (inCorner(colCoord, playArea[0].length - 1))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[rowCoord][colCoord + 1], value -> colCoord += value);
                break;
            }
            case "w":
            {
                if (inCorner(rowCoord, 0))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[rowCoord - 1][colCoord], value -> rowCoord -= value);
                break;
            }
            case "s":
            {
                if (inCorner(rowCoord, playArea.length - 1))
                {
                    return '\0';
                }

                item = changePlayerLocation(playArea[rowCoord + 1][colCoord], value -> rowCoord += value);
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
        getCurrentRoom().rowCurrentCoord = getRowCoord();
        getCurrentRoom().colCurrentCoord = getColCoord();

        setPlayArea(newRoom.movingArea);
        setCurrentRoom(newRoom);
        getCurrentRoom().printRoom(getCurrentRoom().getName());
    }

    public void setLocationToNewRoom()
    {
        rowCoord = getCurrentRoom().getRowInitialCoord();
        colCoord = getCurrentRoom().getColInitialCoord();
    }

    public void setLocationToPreviousRoom()
    {
        rowCoord = getCurrentRoom().rowCurrentCoord;
        colCoord = getCurrentRoom().colCurrentCoord;
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
        return (rowCoord == getCurrentRoom().getRowInitialCoord()
                && colCoord == getCurrentRoom().getColInitialCoord()
                && !getCurrentRoom().getName().equals("Ground"));
    }

    private void updatePlayArea(Character oldValue, Character newValue,
                                Consumer<Integer> action)
    {
        playArea[rowCoord][colCoord] = oldValue;
        action.accept(1);
        playArea[rowCoord][colCoord] = newValue;
    }
    // </editor-fold>
}