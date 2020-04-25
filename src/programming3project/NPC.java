/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.IOException;

/**
 *
 * @author pc
 */
public class NPC extends Person
{
    private boolean placedHint = false;
    private boolean talkedWithPlayer = false;
    private String role;
    private String unlockNPC;
    private NPCLine NPCLine;

    public NPC(String name, char gender, int age, String role, String unlockNPC) throws IOException
    {
        super(name, gender, age);
        this.setUnlockNPC(unlockNPC);
        this.role = role;
        this.NPCLine = new NPCLine(this);
    }

    public void unlockLines()
    {
        if (!getNPCLine().isUnlocked())
        {
            System.out.println("\nNow you can now get potentially important information from the "
                    + getRole() + "!");
            System.out.println("You might want to speak with " + (getGender() == 'M' ? "him" : "her") + " again.");
            NPCLine.unlock();
        }
    }

    public void tryToPlaceHint(Room room, String name, String description,
            int xCoord, int yCoord)
    {
        if (getNPCLine().isUnlocked() && !placedHint)
        {
            placedHint = true;
            room.hints.add(new GroundHint(name, description, xCoord, yCoord));
            room.movingArea[xCoord][yCoord] = 'X';

            System.out.println("\nHey! An item popped up somewhere... maybe it can help you with this case.");
        }
    }

    public String getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * @return the unlockNPC
     */
    public String getUnlockNPC()
    {
        return unlockNPC;
    }

    /**
     * @param unlockNPC the unlockNPC to set
     */
    public void setUnlockNPC(String unlockNPC)
    {
        this.unlockNPC = unlockNPC;
    }

    /**
     * @return the talkedWithPlayerOnce
     */
    public boolean hasTalkedWithPlayer()
    {
        return talkedWithPlayer;
    }

    public void talkedWithPlayer()
    {
        talkedWithPlayer = true;
    }

    public NPCLine getNPCLine() 
    {
        return NPCLine;
    }

    public void setNPCLine(NPCLine NPCLine) 
    {
        this.NPCLine = NPCLine;
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
}
