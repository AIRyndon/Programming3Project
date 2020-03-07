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
public class Programming3Project 
{

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
        Room[] rooms = new Room[]
        {
            new Ground("Ground"),
            new House("House"),
            new RoomMaid("Maid's Room"),
            new RoomButler("Butler's Room"),
            new RoomWife("Wife's Room"),
            new RoomWorking("Work Room")
        };
             
        //Declare all characters
        //char[][] playArea = new char[24][52];
        Detective detective = new Detective(userName, userGender, userAge,
                    rooms[0].movingArea,rooms[0]);
        detective.setBackground("Mysterious fellow");

        Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);

        Relative[] people = {
            new Relative("Marcello", 'M', 63, "Butler"),
            new Relative("Belinda", 'F', 50, "Wife"),
            new Relative("Calista", 'F', 25, "Daughter"),
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
        System.out.println(detective.getName() + " is working in" + (detective.getGender() == 'M' ? "his " : "her ") +  "office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Sir " : "Madam ")
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
        if (enterPremises != 'Y' && enterPremises != 'y') 
        {
            System.out.println("\n\"Sorry I do not have time at the moment...\"");
            System.out.println("Right then, you came to one of your customers' house "
                    + "- a bilionaire names " + victim.getName() + ".");
            System.out.println("Coincidentally, your customer is the victim who was mentioned by the police");
        }
         
        System.out.println("\nNow, press \'1\' to enter the gate...");
        int action = systemInput.nextInt();
        //Check if user want to continue the game
        
        boolean stayInside = true;
        char keyPress = '\0';
        while (stayInside) 
        {
            //Enter the ground
            if (action == 1)
            {
                //access ground => print ground
                detective.setCurrentRoom(rooms[0]);
                detective.setPlayArea(rooms[0].movingArea);
                //rooms[0].printRoom(rooms[0].getName());

                //Moving
                while(!(keyPress == 'a' || keyPress == 'd' || keyPress == 's' || keyPress == 'w')){
                    
                    System.out.println("Press a, s, d, w then enter to move. Press q then enter to quit.");
                    keyPress = systemInput.next().charAt(0);
                }
                
                char landedArea = detective.move(keyPress);
                
                if (landedArea == 'B') {
                    System.out.println(people[0].toString());
                }else if (landedArea == 'W'){
                    System.out.println(people[1].toString());
                }
                    
                        
                keyPress = '\0';              
            }
            //Enter the house
            if (action == 2) 
            {
                //access house => print house
                detective.setCurrentRoom(rooms[1]);
                detective.setPlayArea(rooms[1].movingArea);
                rooms[1].printRoom("House");

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                keyPress = systemInput.next().charAt(0);

                while (action == 2 && keyPress != 'q')
                {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    action = detective.getCurrentRoom().moving(keyPress);
                    detective.getCurrentRoom().printRoom("House");

                    if(action == 2)
                    {
                        System.out.println("Moving by a, s, d, w, quit by q");
                        keyPress = systemInput.next().charAt(0);
                    }
                    else
                    {
                        System.out.println("\n");
                    }
                }
                //rooms[1].resetPlayerPosition();
            }
            //Access Maid's room
            if (action == 3) 
            {
                detective.setCurrentRoom(rooms[2]);
                detective.setPlayArea(rooms[2].movingArea);
                rooms[2].printRoom("Maid");

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                keyPress = systemInput.next().charAt(0);

                while (action == 3 && keyPress != 'q')
                {
                    action = detective.getCurrentRoom().moving(keyPress);
                    detective.getCurrentRoom().printRoom("Maid");

                    if(action == 3)
                    {
                        System.out.println("Moving by a, s, d, w, quit by q");
                        keyPress = systemInput.next().charAt(0);
                    }
                    else
                    {
                        System.out.println("\n");
                    }
                }
            }
            //Access Butler's room
            if (action == 4)
            {
                detective.setCurrentRoom(rooms[3]);
                detective.setPlayArea(rooms[3].movingArea);
                rooms[3].printRoom("Butler");

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                keyPress = systemInput.next().charAt(0);

                while (action == 4 && keyPress != 'q')
                {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    action = detective.getCurrentRoom().moving(keyPress);
                    detective.getCurrentRoom().printRoom("Butler");

                    if(action == 4)
                    {
                        System.out.println("Moving by a, s, d, w, quit by q");
                        keyPress = systemInput.next().charAt(0);
                    }
                    else
                    {
                        System.out.println("\n");
                    }
                }
            }
            //Access Wife's room
            if (action == 5) 
            {
                //access house => print house
                detective.setCurrentRoom(rooms[4]);
                detective.setPlayArea(rooms[4].movingArea);
                rooms[4].printRoom("Wife");

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                keyPress = systemInput.next().charAt(0);

                while (action == 5 && keyPress != 'q')
                {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    action = detective.getCurrentRoom().moving(keyPress);
                    detective.getCurrentRoom().printRoom("Wife");

                    if(action == 5)
                    {
                        System.out.println("Moving by a, s, d, w, quit by q");
                        keyPress = systemInput.next().charAt(0);
                    }
                    else
                    {
                        System.out.println("\n");
                    }
                }
            }
            //Access working room
            if (action == 6)
            {
                //access house => print house
                detective.setCurrentRoom(rooms[5]);
                detective.setPlayArea(rooms[5].movingArea);
                rooms[5].printRoom("Working");

                //Moving
                System.out.println("Moving by a, s, d, w, quit by q");
                keyPress = systemInput.next().charAt(0);

                while (action == 6 && keyPress != 'q')
                {
                    //Todo: refactor moving and resetPlayerPosition method inside Room class
                    action = detective.getCurrentRoom().moving(keyPress);
                    detective.getCurrentRoom().printRoom("Working");

                    if(action == 6)
                    {
                        System.out.println("Moving by a, s, d, w, quit by q");
                        keyPress = systemInput.next().charAt(0);
                    }
                    else
                    {
                        System.out.println("\n");
                    }
                }
            }
            //Talk with the Butler
            if(action == 7)
            {
                System.out.println(people[0].toString());
                System.out.println("Now, press \'1\' to return the ground...");
                action = systemInput.nextInt();
            }
                
            if(keyPress == 'q')
            {
                systemInput.nextLine();
                stayInside = continueGame();
            }  
        }

        System.out.println("Thank you for playing!\n");
        
        systemInput.close();
    }

    /**
     * @return char (Y/N)
     */
    public static boolean continueGame()
    {
        System.out.println("Do you want to continue the game? (Y/N)");
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
