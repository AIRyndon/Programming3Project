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
    private int hintXCoord;
    private int hintYCoord;
    private String role;
    private String unlockNPC;
    private Conversation conversation;

    public NPC(String name, char gender, int age, String role, String unlockNPC)
    {
        super(name, gender, age);
        this.conversation = new Conversation(role, unlockNPC);
        this.setUnlockNPC(unlockNPC);
        this.role = role;
    }

    public void unlockConversation()
    {
        System.out.println("Congratulations! You can now get potentially important information from the " + this.getRole() + "!\n");
        System.out.println("You might want to speak with " + (getGender() == 'M' ? "him" : "her") + " again.");
      
        conversation.unlock();
    }
    
    public void tryToPlaceHint(Room room, String name, String description,
            int xCoord, int yCoord)
    {
        if (getConversation().isUnlocked())
        {
            hintXCoord = xCoord;
            hintYCoord = yCoord;
            room.hints.add(new Hint(name, description, xCoord, yCoord));
            room.movingArea[hintXCoord][hintYCoord] = 'X';
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
     * @return the conversation
     */
    public Conversation getConversation()
    {
        return conversation;
    }

    /**
     * @param conversation the conversation to set
     */
    public void setConversation(Conversation conversation)
    {
        this.conversation = conversation;
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
