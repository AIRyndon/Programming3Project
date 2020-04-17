/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author pc
 */
public class NPC extends Person
{

    private boolean placedHint = false;
    private String role;
    private String unlockNPC;
    private NPCLine NPCLine;

    public NPC(String name, char gender, int age, String role, String unlockNPC)
    {
        super(name, gender, age);
        this.NPCLine = new NPCLine(role, unlockNPC);
        this.setUnlockNPC(unlockNPC);
        this.role = role;
    }

    public void unlockNPCLine()
    {     
        NPCLine.unlock();
    }

    public void tryToPlaceHint(Room room, String name, String description,
            int xCoord, int yCoord)
    {
        if (getLine().isUnlocked() && !placedHint)
        {
            placedHint = true;
            room.hints.add(new Hint(name, description, xCoord, yCoord));
            
            if(room.movingArea[xCoord][yCoord] == ' ')
            {
                room.movingArea[xCoord][yCoord] = 'X';
            }
            else
            {
                room.movingArea[xCoord][yCoord + 1] = 'X';
            }
            
            System.out.println("Congratulations! You can now get potentially important information from the " + getRole() + "!");
            System.out.println("You might want to speak with " + (getGender() == 'M' ? "him" : "her") + " again.");
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
     * @return the NPCLine
     */
    public NPCLine getLine()
    {
        return NPCLine;
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
