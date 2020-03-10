/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private boolean hasTalk;
    private String unlockNPC;
    private String firstTalk = "";
    private String secondTalk = "";
    
    public Conversation(char NPC, String unlockNPC) throws FileNotFoundException, IOException
    {
        readTalkFile(NPC);
        unlocked = false;
        hasTalk = false;
    }

    public void talk()
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
        }
        else if(listen == 'Y' || listen == 'y')
        {
            System.out.println(getSecondTalk());
        }
        
        quit();        
    }
    
    public void quit()
    {
        //Ask if user wants to quit or reaf firstTalk
        char userChoice = ' ';
        
        while(userChoice != 'q' && userChoice != 'Q' && userChoice != 'a' && userChoice != 'Q')
        {
            System.out.println("Press 'q' to close the talk.");
        
            if(isUnlocked())
            {
                System.out.println("Press 'a' to read the first talk again.");
            }

            userChoice = systemInput.next().charAt(0);
        }

        //Clear the buffer;
        systemInput.nextLine();
        
        if((userChoice == 'a' || userChoice == 'A'))
        {
            System.out.println(getFirstTalk());
        }
    }
    
    public void unlock()
    {
        setUnlocked(true);
    }
    
    public void readTalkFile(char NPC) throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(NPC + ".txt"));
        
        String line = " ";

        while((line = br.readLine()) != null)
        {
            if(!line.isEmpty())
            {
                firstTalk += line + "\n";
            }
            else
            {
                secondTalk += line;

                while((line = br.readLine()) != null)
                {
                    secondTalk += line + "\n";
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
    public boolean isHasTalk() {
        return hasTalk;
    }

    /**
     * @param hasTalk the hasTalk to set
     */
    public void setHasTalk(boolean hasTalk) {
        this.hasTalk = hasTalk;
    }
}
