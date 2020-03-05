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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author airyn
 */
public class Programming3Project {

    private static Scanner systemInput = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //todo - gonna refactor these guys later
        //creating a FILE for game state later 
        String workingDir = System.getProperty("user.dir");
        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        new File(workingDir + "/FileDB/").mkdir();

        File file = new File(workingDir + "/FileDB/" + "game-state.txt");

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
        Random rand = new Random();

        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.println("Welcome to the game\n");
        System.out.print("Please enter a name: ");
        String userName = systemInput.nextLine();
        systemInput = new Scanner(System.in);

        char userGender = '\0';
        while (!(userGender == 'M' || userGender == 'm' || userGender == 'F' || userGender == 'f'))     
        {
            System.out.print("Please enter a gender(M/F): ");
            userGender = systemInput.next().charAt(0);
        }

        //Declare all characters 
        Detective detective = new Detective(userName, userGender, 50);
        //What if we just declare "Mysterious fellow"?
        detective.setBackground("Mysterious fellow");

        Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);

        Relative[] people = {
            new Relative("Belinda", 'F', 50, "Wife"),
            new Relative("Calista", 'F', 25, "Daughter"),
            new Relative("Marcello", 'M', 63, "Butler"),
            new Relative("Ashton", 'M', 34, "Assistant")
        };

        //Set killer to random
        Relative killer = people[rand.nextInt(4)];
        
        /*char userDetails = '\0';
        while (!(userDetails == 'Y' || userDetails == 'y' || userDetails == 'N' || userDetails == 'n')) {
            System.out.println("Can we get more details about you?(Y/N)");
            userDetails = systemInput.next().charAt(0);
        }

        if (userDetails == 'Y') {
            //call method to get origin, background, etc.
        } else {
            detective.setBackground("Mysterious fellow");
        } */
        
        //Declare rooms
        Room[] rooms = new Room[]{
            new Ground(55, 30),
            new House(52, 24)
        };

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

        //Ask the player to enter the game area
        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y' || enterPremises == 'N' || enterPremises == 'n')) {
            System.out.println("Do you want to enter the compound?(Y/N)");
            enterPremises = systemInput.next().charAt(0);
        }

        if(enterPremises != 'Y' && enterPremises != 'y')
        {
            System.out.println("\n\"Sorry I do not have time at the moment...\"");
            System.out.println("Right then, you came to one of your customers' house "
                    + "- a bilionaire names " + victim.getName() + ".");
            System.out.println("Coincidentally, your customer is the victim who was mentioned by the police");
        }
        
        //Asking four questions
        //Create a class for room and its subclasses
        //Create class for relatives (we may create an interface name Person to 
        //handle basic info - name, gender, age, role)

        //todo - Using loop to reaccess those rooms            
        boolean stayInside = true;

        while (stayInside) 
        {
            //Display four questions
            System.out.println("\nWhich action do you want to do next? (1 - 4)");
            System.out.println(printQuestions());
            int action = systemInput.nextInt();

            if (action == 1) 
            {
                //access ground => print ground
                rooms[0].printRoom();

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                char move = systemInput.next().charAt(0);

                while(move != 'q')
                {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    rooms[0].moving(move);
                    rooms[0].printRoom();

                    System.out.println("Moving by a, s, d, w, quit by q");
                    move = systemInput.next().charAt(0);
                }
                
                rooms[0].resetPlayerPosition();
            }

            if (action == 2) 
            {       
                //access house => print house
                rooms[1].printRoom();

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                char move = systemInput.next().charAt(0);

                while(move != 'q')
                {
                    rooms[1].moving(move);
                    rooms[1].printRoom();

                    System.out.println("Moving by a, s, d, w, quit by q");
                    move = systemInput.next().charAt(0);
                }
            }

            if (action == 3) 
            {
                //Display conversations
            }

            if (action == 4) 
            {
                //print lockedArea and victim's body
            }

            systemInput.nextLine();
            stayInside = continueGame();
        }
        
        System.out.println("Thank you for playing!\n");
    }

    /**
     * @return the questions
     */
    public static String printQuestions() {
        String questions = "";

        questions += "1. Walk around the ground\n";
        questions += "2. Come inside the house\n";
        questions += "3. Talk with the relatives (suspicious)\n";
        questions += "4. See the victim immediately\n";

        return questions;
    }
    
    /**
     * @return char (Y/N) 
     */
    public static boolean continueGame()
    {
        System.out.println("\nDo you want to continue the game? (Y/N)");
        char continueGame = systemInput.next().charAt(0);

        //Check if it is invalid input
        while (!(continueGame == 'Y' || continueGame == 'y' || continueGame == 'N' || continueGame == 'n')) 
        {
            System.out.println("Invalid input!");
            System.out.println("Do you want to continue the game? (Y/N)");
            continueGame = systemInput.next().charAt(0);
        }
        
        return (continueGame == 'Y' || continueGame == 'y') ? true : false;
    }
}
