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
    protected int rowInitialCoord;
    protected int colInitialCoord;
    protected int rowCurrentCoord;
    protected int colCurrentCoord;
    private NPCSpawnBoundary rowBoundary;
    private NPCSpawnBoundary colBoundary;
    // </editor-fold>

    // <editor-fold desc="Constructor">

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
    public int getColInitialCoord()
    {
        return colInitialCoord;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Room getPreviousRoom()
    {
        return previousRoom;
    }

    public int getRowInitialCoord()
    {
        return rowInitialCoord;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
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
    protected void addBed()
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

    //Create a 2D array for printing Room
    protected void initializeMovingArea()
    {
        this.movingArea = new char[getHeight() - 1][getWidth()];
        rowInitialCoord = 0;
        colInitialCoord = movingArea[0].length / 2;

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

    protected void printRoom(String door)
    {
        clearScreen();

        //Print first line
        printTopWallLeftSide(door);
        printTopWallRightSide(name);

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
        printBottomWall();
    }

    protected void printTopWallLeftSide(String roomName)
    {
        //Print left entrance
        for (int pos = 0; pos < this.getWidth() / 2 - (roomName.length() / 2 + 1); pos++)
        {
            System.out.print("_");
        }

        //Print room name
        System.out.print(String.format("|%s|", roomName));
    }

    protected void printTopWallRightSide(String roomName)
    {
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth(); pos++)
        {
            System.out.print("_");
        }

        System.out.println();
    }

    protected void printBottomWall()
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
    // </editor-fold>

    private void clearScreen()
    {
        System.out.println(new String(new char[30]).replace('\0', '\n'));
    }
}
