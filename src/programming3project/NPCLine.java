package programming3project;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public final class NPCLine
{
    // <editor-fold defaultstate="collapsed" desc="NPCLine Attributes">
    public static LinkedList<String> SAVEDLINES = new LinkedList<>();
    private boolean unlocked;
    private NPC npc;
    private String firstLine;
    private String secondLine;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public NPCLine(NPC npc) throws IOException
    {
        firstLine = "";
        secondLine = "";
        unlocked = false;

        this.npc = npc;
        readNPCLines();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods">
    public String getFirstLine()
    {
        return firstLine;
    }

    public String getSecondLine()
    {
        return secondLine;
    }

    public void setSecondLine(String secondLine)
    {
        this.secondLine = secondLine;
    }

    public boolean isUnlocked()
    {
        return unlocked;
    }

    public void unlock()
    {
        this.unlocked = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private void readNPCLines() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath(npc.getRole() + ".txt")));

        String line;

        while ((line = br.readLine()) != null)
        {
            if (!line.isEmpty())
            {
                firstLine += line + '\n';
            }
            else
            {
                secondLine += line;

                while ((line = br.readLine()) != null)
                {
                    secondLine += line + '\n';
                }

                break;
            }
        }
    }

    public void saveNPCLines(String conversation)
    {
        System.out.print("Do you want to save this conversations (y)? ");

        boolean save = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (save)
        {
            if (SAVEDLINES.contains(npc.getName() + ":\n" + conversation))
            {
                System.out.println("You already have this on file...");
            }
            else
            {
                if (SAVEDLINES.size() == 3)
                {
                    System.out.println("\nYou can only save 3 conversations.\n");

                    for (int index = 0; index < SAVEDLINES.size(); index++)
                    {
                        System.out.println((index + 1) + ". " + SAVEDLINES.get(index));
                    }

                    int removeIndex = 0;
                    do
                    {
                        System.out.print("Choose something to remove(1-3): ");

                        if (Game.SYSTEMINPUT.hasNextInt())
                        {
                            removeIndex = Game.SYSTEMINPUT.nextInt();
                        }
                        Game.SYSTEMINPUT.nextLine();
                    } while (removeIndex < 1 || removeIndex > 3);

                    SAVEDLINES.remove(--removeIndex);
                }

                SAVEDLINES.add(npc.getName() + ":\n" + conversation);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                        Game.getCompletePath("Conversations.txt"))))
                {
                    for (int index = 0; index < SAVEDLINES.size(); index++)
                    {
                        bw.append(SAVEDLINES.get(index));
                    }

                    System.out.println("The conversation has been saved!");
                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    // </editor-fold>
}
