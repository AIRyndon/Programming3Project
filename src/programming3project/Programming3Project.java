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

        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.print("Please enter a name: ");
        String userName = systemInput.nextLine();

        char userGender = '\0';
        while (!(userGender == 'M' || userGender == 'm' || userGender == 'F' || userGender == 'f'))
        {
            System.out.print("Please enter a gender(M/F): ");
            userGender = systemInput.next().charAt(0);
        }

        //Clear buffer
        systemInput.nextLine();

        System.out.print("Please enter an age: ");
        int userAge = systemInput.nextInt();

        //Declare rooms
        Ground ground = new Ground("Ground", null);
        House house = new House("House", ground);
        RoomMaid roomMaid = new RoomMaid("Maid's Room", house);
        RoomButler roomButler = new RoomButler("Butler's Room", house);
        RoomWife roomWife = new RoomWife("Wife's Room", house);
        RoomWorking roomWorking = new RoomWorking("Work Room", house);

        //Declare all characters
        Detective detective = new Detective(userName, userGender, userAge,
                ground);
        
        detective.setCurrentRoom(ground);
        detective.setPreviousRoom(null);
        detective.setPlayArea(ground.movingArea);
        detective.setBackground("Mysterious fellow");

        Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);

        NPC daughter = new NPC("Calista", 'F', 25, "Daughter", "");
        NPC wife = new NPC("Belinda", 'F', 50, "Wife", "");
        NPC maid = new NPC("Cindel", 'F', 20, "Maid", daughter.getRole());
        NPC bulter = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        NPC assistant = new NPC("Ashton", 'M', 34, "Assistant", wife.getRole());

        //Intro the detective
        System.out.println("\nThe main character's information...");
        System.out.println(detective);

        //Tell the story
        //Printing current day
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(new Date()));

        //Story begins
        System.out.println(detective.getName() + " - you are working in your office and reading some news.");
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
        if (enterPremises == 'N' && enterPremises == 'n')
        {
            System.out.println("\n\"Sorry I do not have time at the moment...\"");
            System.out.println("Right then, you came to one of your customers' house "
                    + "- a bilionaire names " + victim.getName() + ".");
            System.out.println("Coincidentally, your customer is the victim who was mentioned by the police");
            System.out.println("Now, you are accessing the the victim's house througth gate...");
        }

        ground.printRoom(ground.getName());
        int countUnlock = 0;
        //Check if user want to continue the game
        boolean stayInside = true;
        while (stayInside)
        {
            char keyPress = '\0';

            //Moving
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's' || keyPress == 'w' ||  keyPress == 'q'))
            {
                System.out.println("Press a, s, d, w then enter to move. Press 'q' then enter to quit.");
                keyPress = systemInput.next().charAt(0);
            }
            
            char landedSquare = detective.move(keyPress);
            String unlockNPC = "";
            switch (landedSquare) 
            {
                case 'B':
                {
                    System.out.println(bulter.toString());                    
                    bulter.getTalk().talk();
                    unlockNPC = bulter.getUnlockNPC();
                    
                    break;
                }
                case 'W': 
                {
                    System.out.println(wife.toString());                    
                    wife.getTalk().talk();
                    unlockNPC = wife.getUnlockNPC();

                    break;
                }
                case 'A': 
                {
                    System.out.println(assistant.toString());                    
                    assistant.getTalk().talk();
                    unlockNPC = assistant.getUnlockNPC();

                    break;
                }
                case 'M':
                {
                    System.out.println(maid.toString());                    
                    maid.getTalk().talk();
                    unlockNPC = maid.getUnlockNPC();

                    break;
                }
                case 'D': 
                {
                    System.out.println(daughter.toString());                    
                    daughter.getTalk().talk();
                    unlockNPC = daughter.getUnlockNPC();

                    break; 
                }
                case '*':
                {
                    if (detective.getCurrentRoom().previousRoom != null)
                    {
                        detective.moveToAnotherRoom(detective.getCurrentRoom().previousRoom);
                        detective.setLocationToPreviousRoom();  
                    }
                    
                    break;
                }
                case '/':
                {
                    //check if at ground area
                    if (detective.getCurrentRoom().getClass() == ground.getClass())
                    {
                        //check if in front of the house
                        if ((detective.getyCoord() >= 25 && detective.getyCoord() <= 27)
                                && detective.getxCoord() == 6)
                        {
                            detective.moveToAnotherRoom(house);
                            detective.setLocationToNewRoom();
                        }

                        //check if inside the House
                    } 
                    else if (detective.getCurrentRoom().getClass() == house.getClass())
                    {
                        //in front of maid's room
                        if (detective.getxCoord() == 2 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomMaid);
                            detective.setLocationToNewRoom();

                            //in front of butler's room
                        } 
                        else if (detective.getxCoord() == 5 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomButler);
                            detective.setLocationToNewRoom();

                            //in front of wife's room
                        } 
                        else if (detective.getxCoord() == 9 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomWife);
                            detective.setLocationToNewRoom();

                            //in front of working area
                        } 
                        else if (detective.getxCoord() == 11 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomWorking);
                            detective.setLocationToNewRoom();
                        }
                    }

                    break;
                }
                default:
                    break;
            }
            
            if(bulter.getTalk().isHasTalk() && wife.getTalk().isHasTalk() && 
                    daughter.getTalk().isHasTalk() && assistant.getTalk().isHasTalk() && 
                    maid.getTalk().isHasTalk() && countUnlock == 0)
            {
                assistant.unlockTalk();
                countUnlock++;
            }

            else if(countUnlock == 1)
            {
                if(bulter.getRole() == unlockNPC)
                {
                    bulter.unlockTalk();
                }
                else if(wife.getRole() == unlockNPC)
                {
                    wife.unlockTalk();
                }
                else if(maid.getRole() == unlockNPC)
                {
                    maid.unlockTalk();
                }
                else if(daughter.getRole() == unlockNPC)
                {
                    daughter.unlockTalk();
                }
            }

            if (keyPress == 'q')
            {
                systemInput.nextLine();
                stayInside = continueGame();
            }
        }

        System.out.println("Thank you for playing!\n");

        systemInput.close();
    }

    /**
     * @return boolean (Y/N)
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