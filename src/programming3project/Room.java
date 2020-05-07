package programming3project;

import java.util.ArrayList;

public abstract class Room
{
    // <editor-fold defaultstate="collapsed" desc="Room attributes">
    public ArrayList<Hint> hints = new ArrayList<>();
    protected int width;
    protected int height;
    protected String name;
    protected char[][] movingArea;
    protected Room previousRoom;
    protected Password lock;
    protected int xInitial;
    protected int yInitial;
    protected int xCurrent;
    protected int yCurrent;
    private NPCSpawnBoundary rowBoundary;
    private NPCSpawnBoundary colBoundary;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * @param previous    the previous room this room is connected to
     * @param rowBoundary the row boundary of NPCs in this room
     * @param colBoundary the column boundary of NPCs in this room
     */
    public Room(Room previous, NPCSpawnBoundary rowBoundary, NPCSpawnBoundary colBoundary)
    {
        previousRoom = previous;
        this.rowBoundary = rowBoundary;
        this.colBoundary = colBoundary;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public Password getLock()
    {
        return lock;
    }

    public void setLock(Password lock)
    {
        this.lock = lock;
    }

    public Room getPreviousRoom()
    {
        return previousRoom;
    }

    public void setPreviousRoom(Room previousRoom)
    {
        this.previousRoom = previousRoom;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getWidth()
    {
        return width;
    }

    public int getxInitial()
    {
        return xInitial;
    }

    public void setxInitial(int xInitial)
    {
        this.xInitial = xInitial;
    }

    public int getyInitial()
    {
        return yInitial;
    }

    public void setyInitial(int yInitial)
    {
        this.yInitial = yInitial;
    }

    public int getxCurrent()
    {
        return xCurrent;
    }

    public void setxCurrent(int xCurrent)
    {
        this.xCurrent = xCurrent;
    }

    public int getyCurrent()
    {
        return yCurrent;
    }

    public void setyCurrent(int yCurrent)
    {
        this.yCurrent = yCurrent;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public void checkPassword()
    {
        this.getLock().promtPassword();

        //Remove '#' when user inputs a correct password
        if (this.getLock().isUnlock())
        {
            removeCharacter('#');
            printRoom(this.getName());
            System.out.println("Congratulations! Your password is correct!");
            System.out.println("DOOR UNLOCKED!");
        }
    }

    public void removeCharacter(char character)
    {
        for (int i = 0; i < this.getHeight() - 1; i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if (movingArea[i][j] == character)
                {
                    movingArea[i][j] = ' ';

                    break;
                }
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    protected void printLeftEntranceAndRoomName(String roomName)
    {
        //Print left entrance
        for (int pos = 0; pos < this.getWidth() / 2 - (roomName.length() / 2 + 1); pos++)
        {
            System.out.print("_");
        }

        //Print room name
        System.out.print(String.format("|%s|", roomName));
    }

    protected void printRightEntrance(String roomName)
    {
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth(); pos++)
        {
            System.out.print("_");
        }

        System.out.println();
    }

    protected void printWall()
    {
        for (int wid = 0; wid < this.getWidth(); wid++)
        {
            if (wid == 0 || wid == getWidth() - 1)
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

    protected static void clearScreen()
    {
        System.out.println(new String(new char[30]).replace('\0', '\n'));
    }

    protected void positionNPC(char person)
    {
        int rowMin = rowBoundary.getMinBoundary();
        int rowMax = rowBoundary.getMaxBoundary();
        int colMin = colBoundary.getMinBoundary();
        int colMax = colBoundary.getMaxBoundary();

        int row = Game.RANDOM.nextInt(rowMax - rowMin + 1) + rowMin;
        int col = Game.RANDOM.nextInt(colMax - colMin + 1) + colMin;

        //Make sure NPCs just replace space(' ')
        while (movingArea[row][col] != ' ')
        {
            row = Game.RANDOM.nextInt(rowMax - rowMin + 1) + rowMin;
            col = Game.RANDOM.nextInt(colMax - colMin + 1) + colMin;
        }

        movingArea[row][col] = person;
    }

    //Create a 2D array for printing Room
    protected void initializeMovingArea()
    {
        this.movingArea = new char[getHeight() - 1][getWidth()];
        setxInitial(0);
        setyInitial(movingArea[0].length / 2);

        //Make sure NPCs are not placed in invalid area (e.g. player cannot touch)
        for (int i = 0; i < this.getHeight() - 2; i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if (j == 0 || j == getWidth() - 1)
                {
                    movingArea[i][j] = '|';
                }
                else if (i == 0 && j == getWidth() / 2)
                {
                    movingArea[i][j] = 'P';
                }
                else
                {
                    movingArea[i][j] = ' ';
                }
            }
        }

        printBed();
    }

    protected void printRoom(String door)
    {
        clearScreen();

        //Print first line
        printLeftEntranceAndRoomName(door);
        printRightEntrance(name);

        //Print middle lines
        for (int i = 0; i < this.getHeight() - 2; i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                System.out.print(movingArea[i][j]);
            }

            System.out.println();
        }

        //print last line
        printWall();
    }
    // </editor-fold>

    private void printBed()
    {
        if (name == "Maid's Room" || name == "Butler's Room")
        {
            for (int row = 0; row < getHeight(); row++)
            {
                for (int col = 0; col < getWidth(); col++)
                {
                    if (row == 0)
                    {
                        movingArea[row][5] = 'B';
                        movingArea[row][6] = 'E';
                        movingArea[row][7] = 'D';
                    }
                    if (row == getHeight() / 2 && col == 0)
                    {
                        movingArea[row][col] = '=';
                    }

                    if (row == 1 && col > 0 && col <= 10)
                    {
                        movingArea[row][col] = '_';
                    }
                    if (row <= 1 && col == 11)
                    {
                        movingArea[row][col] = '|';
                    }
                }
            }
        }
    }
}
