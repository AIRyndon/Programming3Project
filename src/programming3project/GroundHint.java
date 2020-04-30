/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author airyn
 */
public class GroundHint implements Cloneable
{
    public static LinkedList<GroundHint> SAVEDHINTS = new LinkedList<>();
    private String name;
    private String description;
    private int xLocation;
    private int yLocation;

    public GroundHint(String name, String description, int xLocation, int yLocation)
    {
        this.name = name;
        this.description = description;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public static LinkedList<GroundHint> getSavedHints()
    {
        LinkedList<GroundHint> copy = new LinkedList<>();
        Iterator<GroundHint> iterator = SAVEDHINTS.iterator();

        while (iterator.hasNext())
        {
            try
            {
                copy.add((GroundHint) iterator.next().clone());
            } 
            catch (CloneNotSupportedException ex)
            {
                ex.printStackTrace(System.out);
            }
        }

        return copy;
    }

    public static void saveGroundHint(GroundHint hint)
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

                System.out.println("");
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
                    Game.getCompletePath("GroundHints.txt"))))
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

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the xLocation
     */
    public int getxLocation()
    {
        return xLocation;
    }

    /**
     * @return the yLocation
     */
    public int getyLocation()
    {
        return yLocation;
    }

    @Override
    public String toString()
    {
        return "\nItem name: " + getName()
                + "\nItem description: " + getDescription();
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return (GroundHint) super.clone();
    }
}