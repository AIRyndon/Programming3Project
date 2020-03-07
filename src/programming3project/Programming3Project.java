/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Date;
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
    public static void main(String[] args) {
        //todo - gonna refactor these guys later
        //creating a FILE for game state later 
        String workingDir = System.getProperty("user.dir");
        System.out.println("Present Project Directory : " + System.getProperty("user.dir"));
        new File(workingDir + "/FileDB/").mkdir();

        File file = new File(workingDir + "/FileDB/" + "game-state.txt");

        System.out.println("Welcome to the game\n");

        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.print("Please enter a name: ");
        String userName = systemInput.nextLine();

        char userGender = '\0';
        while (!(userGender == 'M' || userGender == 'm' || userGender == 'F' || userGender == 'f')) {
            System.out.print("Please enter a gender(M/F): ");
            userGender = systemInput.next().charAt(0);
        }
        
        //Clear buffer
        systemInput.nextLine();

        System.out.print("Please enter an age: ");
        int userAge = systemInput.nextInt();
        
        //Declare rooms
        Room[] rooms = new Room[]{
            new Ground(),
            new House()
        };
             
        //Declare all characters
        //char[][] playArea = new char[24][52];
        Detective detective = new Detective(userName, userGender, userAge,
                    rooms[0].movingArea,rooms[0]);
        detective.setBackground("Mysterious fellow");

        Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);

        Relative[] people = {
            new Relative("Belinda", 'F', 50, "Wife"),
            new Relative("Calista", 'F', 25, "Daughter"),
            new Relative("Marcello", 'M', 63, "Butler"),
            new Relative("Ashton", 'M', 34, "Assistant"),
            new Relative("Cindel", 'F', 20, "Maid"),
        };

        //Intro the detective
        System.out.println("\nThe main character's information...");
        System.out.println(detective);

        //Tell the story
            //Printing current day
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(new Date()));
        
            //Story begins
        System.out.println("You - " + detective.getName() + " is working in your office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Mr. " : "Mrs. ")
                + detective.getName() + "!\"");
        System.out.println("A police officer runs to you:");
        System.out.println("\"There was a murder at Royal Street! Please come there now!\"");

        //Ask the player to enter the game area
        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y' || enterPremises == 'N' || enterPremises == 'n'))
        {
            System.out.println("Do you want to enter the compound?(Y/N)");
            enterPremises = systemInput.next().charAt(0);
        }
        
        //If player does not want to enter the compound
        if (enterPremises != 'Y' && enterPremises != 'y') {
            System.out.println("\n\"Sorry I do not have time at the moment...\"");
            System.out.println("Right then, you came to one of your customers' house "
                    + "- a bilionaire names " + victim.getName() + ".");
            System.out.println("Coincidentally, your customer is the victim who was mentioned by the police");
        }
         
        //Check if user want to continue the game
        boolean stayInside = true;
        while (stayInside) 
        {
            System.out.println("\nNow, press 1 to enter the gate...");
            int action = systemInput.nextInt();

            //Enter the ground
            if (action == 1)
            {
                //access ground => print ground
                detective.setCurrentRoom(rooms[0]);
                detective.setPlayArea(rooms[0].movingArea);
                rooms[0].printRoom();

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                char move = systemInput.next().charAt(0);

                while (move != 'q')
                {
                    //Check Coordination
                    //Ground has 4 items: Butler, Wife, Dog's house, house
                    
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    detective.move(move);
                    
                    System.out.println("Moving by a, s, d, w, quit by q");
                    move = systemInput.next().charAt(0);
                }

                //rooms[0].resetPlayerPosition();
            }
            //Enter the house
            if (action == 2) 
            {
                //access house => print house
                detective.setCurrentRoom(rooms[1]);
                detective.setPlayArea(rooms[1].movingArea);
                rooms[1].printRoom();

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                char move = systemInput.next().charAt(0);

                while (move != 'q') {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    detective.move(move);

                    System.out.println("Moving by a, s, d, w, quit by q");
                    move = systemInput.next().charAt(0);
                }
                rooms[1].resetPlayerPosition();
            }

            if (action == 3) {
                //Display conversations
            }

            if (action == 4) {
                //print lockedArea and victim's body
            }

            systemInput.nextLine();
            stayInside = continueGame();
        }

        System.out.println("Thank you for playing!\n");
        
        systemInput.close();
    }

    /**
     * @return char (Y/N)
     */
    public static boolean continueGame() {
        System.out.println("\nDo you want to continue the game? (Y/N)");
        char continueGame = systemInput.next().charAt(0);

        //Check if it is invalid input
        while (!(continueGame == 'Y' || continueGame == 'y' || continueGame == 'N' || continueGame == 'n')) {
            System.out.println("Invalid input!");
            System.out.println("Do you want to continue the game? (Y/N)");
            continueGame = systemInput.next().charAt(0);
        }

        return (continueGame == 'Y' || continueGame == 'y') ? true : false;
    }
}
