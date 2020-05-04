package programming3project;

public class RoomWife extends Room
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public RoomWife(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(14);
        setWidth(48);
        
        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected void getRandomPositionNPC(char person)
    {
        super.getRandomPositionNPC(person);
        boolean stop = false;
        
        //Make sure NPC is just placed in space (' ')
        while(!stop)
        {
            for (int i = 0; i <= getHeight() - 2; i++)
            {
                for (int j = 0; j <= getWidth() - 2; j++)
                {
                    //Identify 'D' of "BED" vs 'D' of "Daughter"
                    if(movingArea[i][j] == person && (i != 3 || j != 7))
                    {
                        if(i < 5 && i > 1 && j < 13)
                        {
                            movingArea[i][j] = ' ';
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
        
        getRandomPositionNPC('W');
        getRandomPositionNPC('D');
    }
    // </editor-fold>
}
