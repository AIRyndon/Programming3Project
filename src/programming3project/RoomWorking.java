package programming3project;

public class RoomWorking extends Room 
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public RoomWorking(String name, Room previous)
    {
        super(previous);
        this.setLock(new Password());
        setName(name);
        setHeight(12);
        setWidth(72);
        
        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected void getRandomPositionNPC(char person)
    {
        super.getRandomPositionNPC(person);
        boolean stop = false;
        
        //Make sure NPC will not placed in invalid areas
        while(!stop)
        {
            for (int i = 0; i <= getHeight() - 2; i++)
            {
                for (int j = 0; j <= getWidth() - 2; j++)
                {
                    if(movingArea[i][j] == person)
                    {
                        if(j < getWidth() / 4 + 3 || j > getWidth() / 2 + 12)
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
        
        //Assign Locked Area
        for (int height = 0; height < getHeight() - 1; height++)
        {
            for (int width = 0; width < getWidth(); width++)
            {
                if(width == getWidth() / 4)
                {
                    movingArea[height][width] = '|';
                }
                
                //Wall of Book room
                if(width == getWidth() / 2 + 15 && height != 9)
                {
                    movingArea[height][width] = '|';
                }
                
                if(width == getWidth() - 1)
                {
                    movingArea[height][width] = ' ';
                }
                
                //Book Room's wall
                if(width == getWidth() / 2 + 15 + height * 2)
                {
                    movingArea[height][width] = '\\';
                    
                    if(height < getHeight() - 2)
                    {
                        movingArea[height][width + 1] = '.';
                    }
                }
            }
        }
        
        //Hints in Book room
        movingArea[9][getWidth() / 2 - 30] = '@';
        
        //Locked area
        movingArea[getHeight() / 2 - 1][getWidth() / 4] = '#';
        
        //Drawing table
        for(int row = getWidth() / 2 - 2; row < getWidth() / 2 + 3; row++)
        {
            movingArea[getHeight() - 4][row] = '_';
        }
        
        movingArea[getHeight() - 3][getWidth() / 2 - 3] = '|';
        movingArea[getHeight() - 3][getWidth() / 2 - 2] = 'C';
        movingArea[getHeight() - 3][getWidth() / 2 - 1] = 'h';
        movingArea[getHeight() - 3][getWidth() / 2] = 'a';
        movingArea[getHeight() - 3][getWidth() / 2 + 1] = 'i';
        movingArea[getHeight() - 3][getWidth() / 2 + 2] = 'r';
        movingArea[getHeight() - 3][getWidth() / 2 + 3] = '|';
        
        getRandomPositionNPC('V');
    }
    
    @Override
    protected void printRightEntrance(String roomName)
    {
        for (int pos = this.getWidth() / 2 + (roomName.length() / 2 + 2); pos < this.getWidth() - 20; pos++)
        {
            System.out.print("_");
        }
        
        System.out.println();
    }
    
    @Override
    protected void printWall()
    {
        for (int wid = 0; wid < this.getWidth(); wid++)
        {
            if (wid == 0 || wid == getWidth() - 1 || wid == 18 || wid == getWidth() / 2 + 15
                    || wid == getWidth() / 2 - 3 || wid == getWidth() / 2 + 3)
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
}
