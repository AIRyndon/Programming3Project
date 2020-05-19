package cui_project;

/**
 * This class makes sure an NPC won't randomly spawn in the wrong place
 */
public class NPCSpawnBoundary
{
    private final int minBoundary;
    private final int maxBoundary;

    public NPCSpawnBoundary(int minBoundary, int maxBoundary)
    {
        this.minBoundary = minBoundary;
        this.maxBoundary = maxBoundary;
    }

    public int getMaxBoundary()
    {
        return maxBoundary;
    }

    public int getMinBoundary()
    {
        return minBoundary;
    }
}
