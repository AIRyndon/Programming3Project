/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author airyn
 */
public class Programming3Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //FILE 
        String workingDir = System.getProperty("user.dir");
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        new File(workingDir + "/FileDB/").mkdir();
        
        File file = new File(workingDir + "/FileDB/" + "game-state.txt");
        
        try{
            file.createNewFile();
        }catch (IOException e){
        
        }                
        /*
            The detective is working in his room.
            A police officer runs to him and announces that there was a death of a milionare.
            
            The detective come with the police officer
        
            First four questions:
                - Do you want to walk around the house?
                - Do you want to come in the house?
                - Do you want to talk with the relatives (suspicious)?
                - Do you want to see the body immediately?
        
            => Save in file and change the location
        
            Create some evidences or some hints in each rooms
        */
        
        //Scanner and Random
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        
        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.println("Welcome to the game\n");
        System.out.print("Please enter a name: ");
        String userName = scan.nextLine();
        System.out.print("Please enter a gender(M/F): ");
        char userGender = scan.next().charAt(0);
        
        //Detective Taylor = new Detective(userName);
        //Declare all characters
        Detective detective = new Detective(userName, userGender);
        Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);
        Relatives[] people = {
            new Relatives("Belinda", "Wife", 50, "not yet"),
            new Relatives("Calista", "Daughter", 25, "not yet"),
            new Relatives("Marcello", "Butler", 63, "not yet"),
            new Relatives("Ashton", "Assistant", 34, "not yet"),
        };
        
        //Set killer to random
        Relatives killer = people[rand.nextInt(4)];
        
        //Declare rooms
        Room[] rooms = {
            new Ground(10, 10),
            new House(5, 5),
            new LockedArea(1, 2), //The area where the victim died
        };
        
        //System.out.println("Outside the house...");
        //Intro the detective
        System.out.println("\nThe main character's information...");
        System.out.println(detective);
        
        //Tell the story
        System.out.println("17/6/2031");//Do we need to set date?
        System.out.println("You - " + detective.getName() + " is working in your office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Mr. " : "Mrs. ")
                + detective.getName() + "!\"");
        System.out.println("A police officer runs to you:");
        System.out.println("\"There was a murder at Royal Street! Please come there now!\"");
        
        //May ask if the player wanna go 
            //If he goes => Then continue
            //Else => Make some impacts to persue him to go
        
        scan.nextLine();
        System.out.println("Do you want to go now? (Y/N)");
        boolean wannaGo = "Y".equalsIgnoreCase(scan.nextLine());
        
        if(wannaGo)
        {
            //Make some changes from his office to the scene
            //Asking four questions
            //Create a class for room and its subclasses
            //Create class for relatives (we may create an interface name Person to 
            //handle basic info - name, gender, age, role)
            
            //Using loop to reaccess those rooms            
            boolean getOut = true;
            
            //while(getOut)
            {
                //Display four questions
                System.out.println("\nWhich action do you want do next? (1 - 4)");
                System.out.println(printQuestions());
                int action = scan.nextInt(); 
                
                if(action == 1)
                {
                    //access ground => print ground
                    rooms[0].printRoom();
                }

                if(action == 2)
                {
                    //access house => print house
                     rooms[1].printRoom();
                }

                if(action == 3)
                {
                    //Display conersations
                }

                if(action == 4)
                {
                    //print lockedArea and victim's body
                    rooms[2].printRoom();
                }
            }
        }
        else
        {
             //Else => Make some impacts to persue him to go
        }
        
        
        //if (Taylor.playerActions.MOVE) 
        {
            
        }
    }
    
    /**
     * @return the questions
     */
    public static String printQuestions()
    {
        String s = "";
        
        s += "1. Do you want to walk around the house?\n";
        s += "2. Do you want to come inside the house?\n";
        s += "3. Do you want to talk with the relatives (suspicious)?\n";
        s += "4. Do you want to see the victim immediately? (go to locked area)";
        
        return s;
    }
}
