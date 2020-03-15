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
    private boolean hasTalk;
    private char NPC;
    private String unlockNPC;
    private String firstTalk = "";
    private String secondTalk = "";
    
    public Conversation(char NPC, String unlockNPC) throws FileNotFoundException, IOException
    {
        setUnlockNPC(unlockNPC);
        setNPC(NPC);
        unlocked = false;
        hasTalk = false;
        ReadTalk();
    }

    public void talk() throws FileNotFoundException, IOException
    {
        //Ask if player wants to talk
        System.out.print("Do you want to get some information? (Y/N)");
        char listen = systemInput.next().charAt(0);
        systemInput.nextLine();
        
        while(listen != 'Y' && listen != 'y' && listen != 'N' && listen != 'n')
        {
            System.out.print("Invalid input! Please enter again: ");
            listen = systemInput.next().charAt(0);
        }

        if((listen == 'Y' || listen == 'y') && !isUnlocked())
        {
            System.out.println(getFirstTalk());
            hasTalk = true;
            quit();
        }
        else if(listen == 'Y' || listen == 'y')
        {
            System.out.println(getSecondTalk());
            saveSecretTalk();
            this.setSecondTalk("");
            this.setUnlocked(false);
            quit();
        }
    }
    
    public void quit() throws FileNotFoundException
    {
        System.out.println("Press any character to close the talk.");
        char userChoice = systemInput.next().charAt(0);
        
        //Clear the buffer;
        systemInput.nextLine();
    }
    
    public void saveSecretTalk() throws FileNotFoundException, IOException
    {
        System.out.println("Note: You cannot read this information again.");
        System.out.print("=> Press 'y' save this talk, any character to ignore: ");
        boolean save = "y".equalsIgnoreCase(systemInput.nextLine());
        
        if(save)
        {
            bw = new BufferedWriter(new FileWriter("SecretTalk.txt", true));

            String word = "";
            
            for(int i = 0; i < getSecondTalk().length(); i++)
            {
                bw.append(this.getSecondTalk().charAt(i));
                
                if(getSecondTalk().charAt(i) == '.')
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

        while((line = br.readLine()) != null)
        {
            if(!line.isEmpty())
            {
                setFirstTalk(getFirstTalk() + line + "\n");
            }
            else
            {
                secondTalk += line;

                while((line = br.readLine()) != null)
                {
                    setSecondTalk(getSecondTalk() + line + "\n");
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
     * @return the firstTalk
     */
    public String getFirstTalk() 
    {
        return firstTalk;
    }

    /**
     * @param firstTalk the firstTalk to set
     */
    public void setFirstTalk(String firstTalk) 
    {
        this.firstTalk = firstTalk;
    }

    /**
     * @return the secondTalk
     */
    public String getSecondTalk()
    {
        return secondTalk;
    }

    /**
     * @param secondTalk the secondTalk to set
     */
    public void setSecondTalk(String secondTalk) 
    {
        this.secondTalk = secondTalk;
    }

    /**
     * @return the hasTalk
     */
    public boolean isHasTalk() 
    {
        return hasTalk;
    }

    /**
     * @param hasTalk the hasTalk to set
     */
    public void setHasTalk(boolean hasTalk) 
    {
        this.hasTalk = hasTalk;
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
