package cui_project;

public class RoomButler extends Room
{
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * @param name        the room's name
     * @param previous    the previous room this room is connected to
     * @param rowBoundary the row boundary of NPCs in this room
     * @param colBoundary the column boundary of NPCs in this room
     */
    public RoomButler(String name, Room previous, NPCSpawnBoundary rowBoundary, NPCSpawnBoundary colBoundary)
    {
        super(previous, rowBoundary, colBoundary);
        setName(name);
        setHeight(7);
        setWidth(48);

        initializeMovingArea();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Protected Methods">
    @Override
    protected void initializeMovingArea()
    {
        super.initializeMovingArea();
        addBed();
        movingArea[2][1] = '0';

        positionNPC('A');
    }
    // </editor-fold>
}