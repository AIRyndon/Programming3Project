/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Angelo
 */
public class NPC extends ItemBlock
{

    private boolean discovered = false;
    private boolean linesUnlocked = false;
    private boolean speaking;
    private final String symbol;
    private String role;
    private String firstLine = "";
    private String secondLine = "";
    private String spokenLine = "";

    public NPC(String role, String symbol, int locationX, int locationY,
            int width, int height)
    {
        super(locationX, locationY, width, height);

        this.role = role;
        this.symbol = symbol;
        setLocationX(locationX);
        setLocationY(locationY);
        readNPCLines();
    }

    public String getSymbol()
    {
        return symbol;
    }

    /**
     * @return the speaking
     */
    public boolean isSpeaking()
    {
        return speaking;
    }

    /**
     * @param speaking the speaking to set
     */
    void setSpeaking(boolean speaking)
    {
        //discovered will be set when the detective has spoken with
        //an NPC
        this.speaking = speaking;

        if (speaking)
        {
            discovered = true;
            if (linesUnlocked)
            {
                spokenLine = secondLine + '\n' + firstLine;
            } else
            {
                spokenLine = firstLine;
            }
        } else
        {
            spokenLine = "";
        }

        notifyObservers();
    }

    private void readNPCLines()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(
                    System.getProperty("user.dir") + "/FileDB/" + (getRole() + ".txt")));

            String line;

            while ((line = br.readLine()) != null)
            {
                if (!line.isEmpty())
                {
                    setFirstLine(firstLine + line + '\n');
                } else
                {
                    setSecondLine(secondLine + line);

                    while ((line = br.readLine()) != null)
                    {
                        setSecondLine(secondLine + line + '\n');
                    }

                    break;
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * @param firstLine the firstLine to set
     */
    void setFirstLine(String firstLine)
    {
        this.firstLine = firstLine;
    }

    /**
     * @param secondLine the secondLine to set
     */
    void setSecondLine(String secondLine)
    {
        this.secondLine = secondLine;
    }

    /**
     * @return the role
     */
    public String getRole()
    {
        return role;
    }

    /**
     * @param role the role to set
     */
    void setRole(String role)
    {
        this.role = role;
    }

    /**
     * @return the spokenLine
     */
    public String getSpokenLine()
    {
        return spokenLine;
    }

    /**
     * @return if the detective has spoken with the NPC for the first time
     */
    public boolean isDiscovered()
    {
        return discovered;
    }

    /**
     * reset an NPC to no first conversation
     */
    void resetDiscovered()
    {
        discovered = false;
    }

    /**
     * @return if the NPC's secondary lines are unlocked
     */
    public boolean isLinesUnlocked()
    {
        return linesUnlocked;
    }

    /**
     * @param linesUnlocked unlocks an NPC's secondary line
     */
    void unlockLines(boolean linesUnlocked)
    {
        this.linesUnlocked = linesUnlocked;
    }
}
