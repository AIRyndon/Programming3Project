package programming3project;

public class Ground extends Room
{
    // <editor-fold desc="Constructor">
    /**
     * @param name        the room's name
     * @param previous    the previous room this room is connected to
     * @param rowBoundary the row boundary of NPCs in this room
     * @param colBoundary the column boundary of NPCs in this room
     */
    public Ground(String name, Room previous, NPCSpawnBoundary rowBoundary, NPCSpawnBoundary colBoundary)
    {
        super(previous, rowBoundary, colBoundary);
        this.setLock(new Password());
        setName(name);
        setHeight(24);
        setWidth(52);
        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected methods">
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();

        //Dog's place
        movingArea[0][getWidth() - 15] = '|';
        movingArea[1][getWidth() - 15] = '#';
        movingArea[1][getWidth() - 14] = ' ';
        movingArea[1][getWidth() - 9] = 'd';
        movingArea[1][getWidth() - 8] = 'o';
        movingArea[1][getWidth() - 7] = 'g';
        movingArea[2][getWidth() - 15] = '|';

        for (int i = getWidth() - 14; i < getWidth() - 1; i++)
        {
            movingArea[2][i] = '_';
        }

        //House area
        for (int height = 7; height <= 14; height++)
        {
            for (int width = 17; width < 35; width++)
            {
                if (height == 7) //House's door
                {
                    if (width <= 17 + 6 || width >= 17 + 12)
                    {
                        movingArea[height][width] = '_';
                    }
                    else if (width == (17 + 7) || width == (17 + 11))
                    {
                        movingArea[height][width] = '|';
                    }
                    else
                    {
                        movingArea[height][width] = '/';
                    }
                }
                else if (height == 14) //House's vertical walls
                {
                    if (width == 17 || width == 34)
                    {
                        movingArea[height][width] = '|';
                    }
                    else
                    {
                        movingArea[height][width] = '_';
                    }
                }
                else
                {
                    if (width == 17 || width == 34) //House's horizontal walls
                    {
                        movingArea[height][width] = '|';
                    }
                    else //Empty area inside the house
                    {
                        movingArea[height][width] = ' ';
                    }
                }
            }
        }

        //Assign "HOUSE"
        movingArea[11][24] = 'H';
        movingArea[11][25] = 'O';
        movingArea[11][26] = 'U';
        movingArea[11][27] = 'S';
        movingArea[11][28] = 'E';

        //KeyPassword position
        movingArea[18][9] = '!';

        positionNPC('B');
    }
    // </editor-fold>
}
