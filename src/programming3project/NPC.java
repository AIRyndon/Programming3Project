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
public class NPC extends Person
{ 
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
    
    public void unlockTalk()
    {
        System.out.println("Congratulation! You have unlocked " + this.getRole() + " secret talk!\n");
        
        talk.unlock();
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
