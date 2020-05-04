package programming3project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Hint implements Cloneable
{
    // <editor-fold desc="Hint Attributes">
    public static LinkedList<Hint> SAVEDHINTS = new LinkedList<>();
    private String name;
    private String description;
    private int xLocation;
    private int yLocation;
    // </editor-fold>

    // <editor-fold desc="Constructor">
    public Hint(String name, String description, int xLocation, int yLocation)
    {
        this.name = name;
        this.description = description;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
    // </editor-fold>

    // <editor-fold desc="Getting Methods">
    public String getDescription()
    {
        return description;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getxLocation()
    {
        return xLocation;
    }
    
    public int getyLocation()
    {
        return yLocation;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public static LinkedList<Hint> getSavedHints()
    {
        LinkedList<Hint> copy = new LinkedList<>();
        Iterator<Hint> iterator = SAVEDHINTS.iterator();
        
        while (iterator.hasNext())
        {
            try
            {
                copy.add((Hint) iterator.next().clone());
            } 
            catch (CloneNotSupportedException ex)
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return copy;
    }
    
    public static void saveHint(Hint hint)
    {
        System.out.print("\nDo you want to save this hint (y)? ");
        
        boolean save = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());
        
        if (save)
        {
            if (SAVEDHINTS.size() == 2)
            {
                System.out.println("\nYou can only save 2 hints.\n");
                
                for (int index = 0; index < SAVEDHINTS.size(); index++)
                {
                    System.out.println((index + 1) + ". " + SAVEDHINTS.get(index));
                }
                
                System.out.println();
                int removeIndex = 0;
                do
                {
                    System.out.print("Choose something to remove(1-2): ");
                    
                    if (Game.SYSTEMINPUT.hasNextInt())
                    {
                        removeIndex = Game.SYSTEMINPUT.nextInt();
                    }
                    Game.SYSTEMINPUT.nextLine();
                } while (removeIndex < 0 || removeIndex > 2);
                
                SAVEDHINTS.remove(--removeIndex);
            }
            
            SAVEDHINTS.add(hint);
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                    Game.getCompletePath("Hints.txt"))))
            {
                for (int index = 0; index < SAVEDHINTS.size(); index++)
                {
                    bw.append(SAVEDHINTS.get(index).toString());
                }
                
                System.out.println("The hint has been saved!");
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
    @Override
    public String toString()
    {
        return "\nItem name: " + getName()
                + "\nItem description: " + getDescription();
    }
    // </editor-fold>
}