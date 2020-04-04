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
    private boolean unlocked;
    private boolean talkedWithPlayer;
    private String NPC;
    private String unlockNPC;
    private String firstConversation = "";
    private String secondConversation = "";

    public Conversation(String NPC, String unlockNPC)
    {
        setUnlockNPC(unlockNPC);
        setNPC(NPC);
        unlocked = false;
        talkedWithPlayer = false;
        ReadNPCLines();
    }

    public void talk()
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
                saveConversation(getFirstConversation() + getSecondConversation());
                this.setSecondConversation("");
            } else
            {
                saveConversation(getFirstConversation());
            }
           
            quit();
        }
    }
 
    public void quit()
    {
        System.out.println("Press any character to close this conversation.");

        //Clear the buffer;
        systemInput.nextLine();
    }

    public void saveConversation(String conversation)
    {
        //Clear buffer
        systemInput.nextLine();

        System.out.print("=> Press 'y' save this conversation, any character to ignore: ");
        boolean save = "y".equalsIgnoreCase(systemInput.nextLine());

        if (save)
        {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(
                    Game.getCompletePath("SecretTalk.txt"), true)))
            {
                for (int i = 0; i < conversation.length(); i++)
                {
                    bw.append(conversation.charAt(i));
                }

                bw.append("\n");
                System.out.println("The conversation has been saved!");
            } catch (IOException ex)
            {
                System.out.println(ex.getMessage());
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
                Game.getCompletePath(getNPC() + ".txt"))))
        {
            String line;

            while ((line = br.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    setFirstConversation(getFirstConversation() + line + '\n');
                } else
                {
                    secondConversation += line;

                    while ((line = br.readLine()) != null)
                    {
                        setSecondConversation(getSecondConversation() + line + '\n');
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
    public String getNPC()
    {
        return NPC;
    }

    /**
     * @param NPC the NPC to set
     */
    public void setNPC(String NPC)
    {
        this.NPC = NPC;
    }
}
