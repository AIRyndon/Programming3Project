package programming3project;

public class RoomWife extends Room
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * @param name        the room's name
     * @param previous    the previous room this room is connected to
     * @param rowBoundary the row boundary of NPCs in this room
     * @param colBoundary the column boundary of NPCs in this room
     */
    public RoomWife(String name, Room previous, NPCSpawnBoundary rowBoundary, NPCSpawnBoundary colBoundary)
    {
        super(previous, rowBoundary, colBoundary);
        setName(name);
        setHeight(14);
        setWidth(48);

        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        //Print Wife's room
        for (int row = 0; row < getHeight(); row++)
        {
            for (int col = 0; col < getWidth(); col++)
            {
                if (row == 1 || row == 4)
                {
                    if (col > 0 && col <= 12)
                    {
                        movingArea[row][col] = '_';
                    }
                    if (row != 1 && col == 13)
                    {
                        movingArea[row][col] = '|';
                    }
                }
                if (row == 2)
                {
                    movingArea[row][13] = '|';
                }
                if (row == 3)
                {
                    movingArea[row][5] = 'B';
                    movingArea[row][6] = 'E';
                    movingArea[row][7] = 'D';
                    movingArea[row][13] = '|';
                }

                if (row == getHeight() / 2 && col == 0)
                {
                    movingArea[row][col] = '=';
                }
            }
        }

        positionNPC('W');
        positionNPC('D');
    }
    // </editor-fold>
}
