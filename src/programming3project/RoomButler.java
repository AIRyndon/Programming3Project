package programming3project;

public class RoomButler extends Room
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public RoomButler(String name, Room previous)
    {
        super(previous);
        setName(name);
        setHeight(7);
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
        
        while(!stop)
        {
            for (int i = 0; i <= getHeight() - 2; i++)
            {
                for (int j = 0; j <= getWidth() - 2; j++)
                {
                    if(movingArea[i][j] == person)
                    {
                        if(i < 2 && j < 11)
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
        
        movingArea[2][1] = '0';
        
        getRandomPositionNPC('A');
    }
    // </editor-fold>
}