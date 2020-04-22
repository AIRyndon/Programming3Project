/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public final class NPCLine
{

    private static LinkedList<String> SAVEDLINES = new LinkedList<>();
    private boolean unlocked;
    private NPC npc;
    private String firstLine = "";
    private String secondLine = "";

    public NPCLine(NPC npc)
    {
        setNPC(npc);
        ReadNPCLines();
        unlocked = false;
    }

    public static LinkedList<String> getSavedLines()
    {

        LinkedList<String> copy = new LinkedList<>();
        Iterator<String> iterator = SAVEDLINES.iterator();

        while (iterator.hasNext())
        {
            copy.add(iterator.next());
        }

        return copy;
    }

    public void talk()
    {
        //Ask if player wants to talk
        System.out.print("Press y then enter to get some information, any character to ignore.> ");

        //clear buffer first
        Game.SYSTEMINPUT.nextLine();
        boolean talk = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (talk)
        {
            if (!isUnlocked())
            {
                getNPC().talkedWithPlayer();
                System.out.println("\n" + getFirstLine());

                saveNPCLines(getFirstLine());
            } else
            {
                System.out.println("\n" + getSecondLine());
                saveNPCLines(getSecondLine());

                //After printing task 2, task 2 = task 1
                //Player is unable to see task 2 again
                this.setSecondLine(getFirstLine());
            }
        }
    }

    public void unlock()
    {
        setUnlocked(true);
    }

    public void ReadNPCLines()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath(getNPC().getRole() + ".txt"))))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    setFirstLine(getFirstLine() + line + '\n');
                } else
                {
                    secondLine += line;

                    while ((line = br.readLine()) != null)
                    {
                        setSecondLine(getSecondLine() + line + '\n');
                    }

                    break;
                }
            }
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @return the unlocked
     */
    public boolean isUnlocked()
    {
        return unlocked;
    }

    /**
     * @param unlocked the unlocked to set
     */
    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
    }

    /**
     * @return the firstConversation
     */
    public String getFirstLine()
    {
        return firstLine;
    }

    /**
     * @param line the firstConversation to set
     */
    public void setFirstLine(String line)
    {
        firstLine = line;
    }

    /**
     * @return the secondConversation
     */
    public String getSecondLine()
    {
        return secondLine;
    }

    /**
     * @param line the secondConversation to set
     */
    public void setSecondLine(String line)
    {
        secondLine = line;
    }

    /**
     * @return the npc
     */
    public NPC getNPC()
    {
        return npc;
    }

    /**
     * @param npc the npc to set
     */
    public void setNPC(NPC npc)
    {
        this.npc = npc;
    }

    private void saveNPCLines(String conversation)
    {
        System.out.print("\nPress 'y' then enter to save this conversation, any character to ignore.> ");

        boolean save = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (save)
        {
            if (SAVEDLINES.size() == 3)
            {
                System.out.println("You can only save 3 conversations.");

                for (int index = 0; index < SAVEDLINES.size(); index++)
                {
                    System.out.println((index + 1) + ". " + SAVEDLINES.get(index));
                }

                int removedItem = 0;
                do
                {
                    System.out.print("Choose something to remove(1-3). ");

                    if (Game.SYSTEMINPUT.hasNextInt())
                    {
                        removedItem = Game.SYSTEMINPUT.nextInt();
                    }

                } while (removedItem < 0 && removedItem > 3);

                SAVEDLINES.remove(--removedItem);
            }

            SAVEDLINES.add(npc.getName() + ":\n" + conversation);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                    Game.getCompletePath("SecretTalk.txt"))))
            {
                for (int index = 0; index < SAVEDLINES.size(); index++)
                {
                    bw.append(SAVEDLINES.get(index) + '\n');
                }

                System.out.println("The conversation has been saved!");

            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
