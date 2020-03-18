/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Conversation
{

    private Scanner systemInput = new Scanner(System.in);
    BufferedWriter bw = new BufferedWriter(new FileWriter("SecretTalk.txt", false));
    private boolean unlocked;
    private boolean talkedWithPlayer;
    private char NPC;
    private String unlockNPC;
    private String firstConversation = "";
    private String secondConversation = "";

    public Conversation(char NPC, String unlockNPC) throws FileNotFoundException, IOException
    {
        setUnlockNPC(unlockNPC);
        setNPC(NPC);
        unlocked = false;
        talkedWithPlayer = false;
        ReadTalk();
    }

    public void talk() throws FileNotFoundException, IOException
    {
        //Ask if player wants to talk
        System.out.print("Do you want to get some information? (Y/N)");
        char listen = systemInput.next().charAt(0);
        systemInput.nextLine();

        while (listen != 'Y' && listen != 'y' && listen != 'N' && listen != 'n')
        {
            System.out.print("Invalid input! Please enter again: ");
            listen = systemInput.next().charAt(0);
        }

        if ((listen == 'Y' || listen == 'y'))
        {
            talkedWithPlayer = true;
            System.out.println(getFirstConversation());

            if (isUnlocked())
            {
                System.out.println(getSecondConversation());
                this.setSecondConversation("");
            }

            saveConversation();
            quit();
        }
    }

//todo - saving a conversation should be possible for any conversations - e.g. first or second conversation
//I commented setting unlocked to false here because we wont be able to see the hints, when we set it to false after
//talking with an NPC
// I think the cleanest way to go about this is clear the second talk, and have an option to save a conversation after pressing a key - like K for example
//that way the player can save any conversation, and without the hint that some conversations only appear once :D
//this.setUnlocked(true);
    public void quit() throws FileNotFoundException
    {
        System.out.println("Press any character to close the talk.");
        //systemInput.next().charAt(0);

        //Clear the buffer;
        systemInput.nextLine();
    }

    public void saveConversation() throws FileNotFoundException, IOException
    {
        System.out.print("=> Press 'y' save this talk, any character to ignore: ");
        boolean save = "y".equalsIgnoreCase(systemInput.nextLine());

        if (save)
        {
            bw = new BufferedWriter(new FileWriter("SecretTalk.txt", true));

            String word = "";

            for (int i = 0; i < getSecondConversation().length(); i++)
            {
                bw.append(this.getSecondConversation().charAt(i));

                if (getSecondConversation().charAt(i) == '.')
                {
                    bw.newLine();
                }
            }

            bw.append("\n");
            bw.close();
            System.out.println("The talk has been saved!");
        }
    }

    public void unlock()
    {
        setUnlocked(true);
    }

    public void ReadTalk() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(getNPC() + ".txt"));

        String line = "b";

        while ((line = br.readLine()) != null)
        {
            if (!line.isEmpty())
            {
                setFirstConversation(getFirstConversation() + line + "\n");
            } else
            {
                secondConversation += line;

                while ((line = br.readLine()) != null)
                {
                    setSecondConversation(getSecondConversation() + line + "\n");
                }

                break;
            }
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
     * @return the firstConversation
     */
    public String getFirstConversation()
    {
        return firstConversation;
    }

    /**
     * @param conversation the firstConversation to set
     */
    public void setFirstConversation(String conversation)
    {
        this.firstConversation = conversation;
    }

    /**
     * @return the secondConversation
     */
    public String getSecondConversation()
    {
        return secondConversation;
    }

    /**
     * @param conversation the secondConversation to set
     */
    public void setSecondConversation(String conversation)
    {
        this.secondConversation = conversation;
    }

    /**
     * @return the talkedWithPlayerOnce
     */
    public boolean hasTalkedWithPlayer()
    {
        return talkedWithPlayer;
    }

    /**
     * @return the NPC
     */
    public char getNPC()
    {
        return NPC;
    }

    /**
     * @param NPC the NPC to set
     */
    public void setNPC(char NPC)
    {
        this.NPC = NPC;
    }
}
