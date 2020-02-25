/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
        
        //Scanner
        Scanner scan = new Scanner(System.in);
        
        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.println("Welcome to the game\n");
        System.out.print("Please enter a name: ");
        String userName = scan.nextLine();
        System.out.print("Please enter a gender(M/F): ");
        char userGender = scan.next().charAt(0);
        
        //Detective Taylor = new Detective(userName);
        Detective detective = new Detective(userName, userGender);
        Relatives wife = new Relatives(userName, "Wife", "Female", "not yet");
        Relatives daughter = new Relatives(userName, "Daughter", "Female", "not yet");
        Relatives butler = new Relatives(userName, "Butler", "Male", "not yet");
        Relatives assistant = new Relatives(userName, "Assistant", "Male", "not yet");
        
        
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
        
        //System.out.println("Outside the house...");
        //Intro the detective
        System.out.println("\nThe main character's information...");
        System.out.println(detective);
        
        //Tell the story
        System.out.println("\n17/6/2031");//Do we need to set date?
        System.out.println(detective.getName() + " is working in his office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Mr. " : "Mrs. ") + "\" " 
                + detective.getName() + "!");
        System.out.println("A police officer runs to him:");
        System.out.println("\"There was a murder at Royal Street! Please come there now!\"");
        
        //May ask if the player wanna go 
            //If he goes => Then continue
            //Else => Make some impacts to persue him to go
        //Make some changes from his office to the scene

        //if (Taylor.playerActions.MOVE) 
        {
            
        }

        //Asking four questions
        //Create a class for room and its subclasses
        //Create class for relatives (we may create an interface name Person to 
            //handle basic info - name, gender, age, role)
        
        
        
              
    }
    
}
