package programming3project;

public class RoomMaid extends Room
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public RoomMaid(String name, Room previous, NPCSpawnBoundary rowBoundary, NPCSpawnBoundary colBoundary)
    {
        super(previous, rowBoundary, colBoundary);
        setName(name);
        setHeight(7);
        setWidth(48);

        initializeMovingArea();
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();
        positionNPC('M');
    }
//</editor-fold>
}
