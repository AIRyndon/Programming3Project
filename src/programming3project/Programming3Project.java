/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author airyn
 */
public class Programming3Project
{

    private static Scanner systemInput = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        //todo - gonna refactor these guys later
        //creating a FILE for game state later 
        String workingDir = System.getProperty("user.dir");
        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        new File(workingDir + "/FileDB/").mkdir();

        File file = new File(workingDir + "/FileDB/" + "game-state.txt");

        System.out.println("Welcome to the game\n");

        Game game = new Game();
        
        game.startGame();
        
        System.out.println("Thank you for playing!\n");

        systemInput.close();
    }

    /*
    public static void printTalk(char people) throws IOException 
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(people + ".txt"));
            
            //Just the first talk
            String line = " ";
            String outPut1 = "";//First talk
            String outPut2 = "";//Second talk, after unlocked something
           
            while((line = br.readLine()) != null)
            {
                if(!line.isEmpty())
                {
                    outPut1 += line + "\n";
                }
                else
                {
                    outPut2 += line;
                    
                    while((line = br.readLine()) != null)
                    {
                        outPut2 += line + "\n";
                    }
                    
                    break;
                }
            }
            
            //System.out.println(outPut1);
            System.out.println(outPut2);
            
            System.out.println("Press 'q' to quit the talk.");
            boolean quitTalk = "q".equalsIgnoreCase(systemInput.nextLine());
            
            while(!quitTalk)
            {
                System.out.println("Invalid input! Press 'q' to quit the talk.");
                quitTalk = "q".equalsIgnoreCase(systemInput.nextLine());
            }
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Programming3Project.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}