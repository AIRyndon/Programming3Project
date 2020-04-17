/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class NPCLine
{
    private Scanner systemInput = new Scanner(System.in);
    private boolean unlocked;
    private boolean talkedWithPlayer;
    private String NPC;
    private String unlockNPC;
    private String firstLine = "";
    private String secondLine = "";

    public NPCLine(String NPC, String unlockNPC)
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
        System.out.println("Press y to get some information, any character to leave.");
        System.out.print("> ");
        char listen = systemInput.next().charAt(0);
        systemInput.nextLine();
        
        if ((listen == 'Y' || listen == 'y'))
        {
            if(!isUnlocked())
            {
                talkedWithPlayer = true;
                System.out.println("\n" + getFirstLine());
            }
            else
            {
                System.out.println("\n" + getSecondLine());
                
                //After printing task 2, task 2 = task 1
                    //Player is unable to see task 2 again
                this.setSecondLine(getFirstLine());
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

    public void saveNPCLines(String conversation)
    {
        //Clear buffer
        systemInput.nextLine();

        System.out.println("Press 'y' to save this conversation, any character to ignore.");
        System.out.print("> ");
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

                bw.append('\n');
                System.out.println("The conversation has been saved!");
            } 
            catch (IOException ex)
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
    public String getFirstLine()
    {
        return firstLine;
    }

    /**
     * @param line the firstConversation to set
     */
    public void setFirstLine(String line)
    {
        firstLine = line;
    }

    /**
     * @return the secondConversation
     */
    public String getSecondLine()
    {
        return secondLine;
    }

    /**
     * @param line the secondConversation to set
     */
    public void setSecondLine(String line)
    {
        secondLine = line;
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
