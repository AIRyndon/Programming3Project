package programming3project;

public class Ground extends Room
{
    // <editor-fold desc="Constructor">
    public Ground(String name, Room previous)
    {
        super(previous);
        this.setLock(new Password());
        setName(name);
        setHeight(24);
        setWidth(52);
        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold desc="Protected methods">
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
        
        getRandomPositionNPC('B');
    }
    
    @Override
    protected void getRandomPositionNPC(char person)
    {
        super.getRandomPositionNPC(person);
        boolean stop = false;
        
        //Make sure NPC will not placed in invalid areas (e.g. player cannot touch)
        while (!stop)
        {
            for (int height = 0; height <= getHeight() - 2; height++)
            {
                for (int width = 0; width <= getWidth() - 2; width++)
                {
                    if (movingArea[height][width] == person)
                    {
                        if ((height < 3 && width > getWidth() - 12) || (height >= 7 && height <= 14 && width >= 17 && width <= 34))
                        {
                            movingArea[height][width] = ' ';
                            super.getRandomPositionNPC(person);
                        }
                        else
                        {
                            stop = true;
                        }
                    }
                }
            }
        }
    }
    // </editor-fold>
}
