package programming3project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        
        setNPC(npc);
        ReadNPCLines();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public boolean isUnlocked()
    {
        return unlocked;
    }
    
    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
    }
    
    public String getFirstLine()
    {
        return firstLine;
    }
    
    public void setFirstLine(String line)
    {
        firstLine = line;
    }
    
    public String getSecondLine()
    {
        return secondLine;
    }
    
    public void setSecondLine(String line)
    {
        secondLine = line;
    }
    
    public NPC getNPC()
    {
        return npc;
    }
    
    public void setNPC(NPC npc)
    {
        this.npc = npc;
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
    
    public void ReadNPCLines() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath(getNPC().getRole() + ".txt")));

        String line;

        while ((line = br.readLine()) != null)
        {
            if (!line.isEmpty())
            {
                setFirstLine(getFirstLine() + line + '\n');
            } 
            else
            {
                secondLine += line;

                while ((line = br.readLine()) != null)
                {
                    setSecondLine(getSecondLine() + line + '\n');
                }

                break;
            }
        }
    }
    
    public boolean talk() throws IOException
    {
        //Ask if player wants to talk
        System.out.print("Press y then enter to get some information, any character to ignore: ");
        
        boolean talk = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());
        
        if (talk)
        {
            if (!isUnlocked())
            {
                getNPC().talkedWithPlayer();
                System.out.println("\n" + getFirstLine());
                
                saveNPCLines(getFirstLine());
            }
            else
            {
                System.out.println("\n" + getSecondLine());
                saveNPCLines(getSecondLine());
                
                //After printing talk 2, talk 2 = talk 1
                //Player is unable to see talk 2 again
                this.setSecondLine(getFirstLine());
            }
            
            return true;
        }
        
        return false;
    }
    
    public void unlock()
    {
        setUnlocked(true);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Methods">
    private void saveNPCLines(String conversation)
    {
        System.out.print("Do you want to save this conversations (y)? ");
        
        boolean save = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());
        
        if (save)
        {
            //If this conversation has been saved already
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
                
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(
                        Game.getCompletePath("Conversations.txt"))))
                {
                    for (int index = 0; index < SAVEDLINES.size(); index++)
                    {
                        bw.append(SAVEDLINES.get(index));
                    }
                    
                    System.out.println("The conversation has been saved!");
                }
                catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    // </editor-fold>
}
