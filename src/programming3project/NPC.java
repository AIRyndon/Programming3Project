/**
 *
 * @author pc
 */
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
public class NPC extends Person implements Story
{

    private int hintXCoord;
    private int hintYCoord;
    private String role;
    private String unlockNPC;
    private Conversation talk;

    public NPC(String name, char gender, int age, String role, String unlockNPC) throws IOException
    {
        super(name, gender, age);
        this.talk = new Conversation(role.charAt(0), unlockNPC);
        this.setUnlockNPC(unlockNPC);
        this.role = role;
    }

    public void unlockConversation()
    {
        System.out.println("Congratulations! You can now get potentially important information from the " + this.getRole() + "!\n");
        System.out.println("You might want to speak with " + (getGender() == 'M' ? "him" : "her") + " again.");
      
        talk.unlock();
    }

    public String getRole()
    {
        return role;
    }

    @Override
    public String toString()
    {
        String output = "";

        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";

        return output;
    }

    public void tryToPlaceHint(Room room, String name, String description,
            int xCoord, int yCoord)
    {
        if (getTalk().isUnlocked())
        {
            hintXCoord = xCoord;
            hintYCoord = yCoord;
            room.hints.add(new Hint(name, description, xCoord, yCoord));
            room.movingArea[hintXCoord][hintYCoord] = 'X';
        }
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * @return the talk
     */
    public Conversation getTalk()
    {
        return talk;
    }

    /**
     * @param talk the talk to set
     */
    public void setTalk(Conversation talk)
    {
        this.talk = talk;
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
