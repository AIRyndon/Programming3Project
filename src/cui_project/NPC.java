package cui_project;

import java.io.IOException;

public class NPC extends Person
{
    // <editor-fold defaultstate="collapsed" desc="NPC Attributes">
    private boolean placedHint = false;
    private boolean talkedWithPlayer = false;
    private String role;
    private String unlockNPC;
    private NPCLine NPCLine;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public NPC(String name, char gender, int age, String role, String unlockNPC) throws IOException
    {
        super(name, gender, age);
        this.unlockNPC = unlockNPC;
        this.role = role;
        this.NPCLine = new NPCLine(this);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public boolean hasTalkedWithPlayer()
    {
        return talkedWithPlayer;
    }

    public NPCLine getNPCLine()
    {
        return NPCLine;
    }
    
    public String getUnlockNPC()
    {
        return unlockNPC;
    }

    public String getRole()
    {
        return role;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public boolean talk() throws IOException
    {
        //Ask if player wants to talk
        System.out.print("Press y then enter to get some information, any character to ignore: ");
        
        boolean talk = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());
        
        if (talk)
        {
            String firstLine = getNPCLine().getFirstLine();
            if (!getNPCLine().isUnlocked())
            {
                talkedWithPlayer = true;
                System.out.println("\n" + firstLine);
                getNPCLine().saveNPCLines(firstLine);
            }
            else
            {
                String secondLine = getNPCLine().getSecondLine();
                System.out.println("\n" + secondLine);

                getNPCLine().saveNPCLines(secondLine);
                getNPCLine().setSecondLine(firstLine);
            }

            return true;
        }
        
        return false;
    }
    
    public void tryToPlaceHint(Room room, String name, String description,
            int row, int col)
    {
        if (getNPCLine().isUnlocked() && !placedHint)
        {
            placedHint = true;
            room.hints.add(new Hint(name, description, row, col));
            room.movingArea[row][col] = 'X';
            
            System.out.println("\nHey! An item popped up somewhere..."
                    + " maybe it can help you with this case.");
        }
    }

    @Override
    public String toString()
    {
        String output = "";
        
        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Role: " + this.getRole() + "\n";
        output += "Age: " + this.getAge() + "\n";
        
        return output;
    }
    
    public void unlockLines()
    {
        if (!getNPCLine().isUnlocked())
        {
            System.out.println("\nNow you can now get potentially important information from the "
                    + getRole() + "!");
            System.out.println("You might want to speak with "
                    + (getGender() == 'M' ? "him" : "her") + " again.");
            NPCLine.unlock();
        }
    }
    // </editor-fold>
}
