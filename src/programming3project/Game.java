/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import static programming3project.Programming3Project.printTalk;

/**
 *
 * @author airyn
 */
public class Game
{

    //Required game fields
    private static Scanner systemInput = new Scanner(System.in);
    private Ground ground = new Ground("Ground", null);
    private House house = new House("House", ground);
    private RoomMaid roomMaid = new RoomMaid("Maid's Room", house);
    private RoomButler roomButler = new RoomButler("Butler's Room", house);
    private RoomWife roomWife = new RoomWife("Wife's Room", house);
    private RoomWorking roomWorking = new RoomWorking("Work Room", house);
    private Detective detective;
    Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55);
    private Relative[] people =
    {
        new Relative("Marcello", 'M', 63, "Butler"),
        new Relative("Belinda", 'F', 50, "Wife"),
        new Relative("Calista", 'F', 25, "Daughter"),
        new Relative("Ashton", 'M', 34, "Assistant"),
        new Relative("Cindel", 'F', 20, "Maid"),
    };

    private Detective setupPlayerInfo()
    {
        //Promt user input
        //Need to check those inputs (InputException - try catch)
        System.out.print("Please enter a name: ");
        String userName = systemInput.nextLine();

        char userGender = '\0';
        do
        {
            System.out.print("Please enter a gender(M/F): ");
            userGender = systemInput.next().charAt(0);
        } while (!(userGender == 'M' || userGender == 'm' || userGender == 'F' || userGender == 'f'));

        //Clear buffer
        systemInput.nextLine();

        int userAge = 0;
        do
        {
            System.out.print("Please enter your age: ");

            if (systemInput.hasNextInt())
            {
                userAge = systemInput.nextInt();
            } else
            {
                systemInput.nextLine();
            }

        } while (userAge <= 0);

        Detective detective = new Detective(userName, userGender, userAge,
                ground);

        detective.setBackground("Mysterious fellow");
        detective.setCurrentRoom(ground);
        detective.setPreviousRoom(null);
        detective.setPlayArea(ground.movingArea);

        return detective;
    }

    private void introduceStory(Detective detective)
    {
        System.out.println(detective);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(new Date()));

        //Story begins
        System.out.println(detective.getName() + " is working in" + (detective.getGender() == 'M' ? "his " : "her ") + "office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Sir " : "Madam ")
                + detective.getName() + "!\"");
        System.out.println("A police officer runs to you:");
        System.out.println("\"There was a murder at Royal Street! Please come there now!\"");

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

        ground.printRoom(ground.getName());
    }

    public void startGame()
    {
        detective = setupPlayerInfo();
        introduceStory(detective);
        startGameLoop();

    }

    private void startGameLoop()
    {
        boolean stoppedPlaying = false;

        while (!stoppedPlaying)
        {

            char keyPress = '\0';

            //access ground => print ground
            //Moving
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's' || keyPress == 'w' || keyPress == 'q'))
            {
                System.out.println("Press a, s, d, w then enter to move. Press q then enter to quit.");
                keyPress = systemInput.next().charAt(0);
            }
            
            clearScreen();
            char landedSquare = detective.move(keyPress);
            boolean listen = false;

            switch (landedSquare)
            {
                case 'B':
                {
                    //Clear the buffer
                    systemInput.nextLine();
                    System.out.println(people[0].toString());
                    System.out.println("Do you want to talk to the Butler?");
                    listen = "Y".equalsIgnoreCase(systemInput.nextLine());

                    break;
                }
                case 'W':
                {
                    //Clear the buffer
                    systemInput.nextLine();
                    System.out.println(people[1].toString());
                    System.out.println("Do you want to talk to the Wife?");
                    listen = "Y".equalsIgnoreCase(systemInput.nextLine());

                    break;
                }
                case 'A':
                {
                    //Clear the buffer
                    systemInput.nextLine();
                    System.out.println(people[0].toString());
                    System.out.println("Do you want to talk to the Assistant?");
                    listen = "Y".equalsIgnoreCase(systemInput.nextLine());

                    break;
                }
                case 'M':
                {
                    //Clear the buffer
                    systemInput.nextLine();
                    System.out.println(people[1].toString());
                    System.out.println("Do you want to talk to the Maid?");
                    listen = "Y".equalsIgnoreCase(systemInput.nextLine());

                    break;
                }

                case 'D':
                {
                    //Clear the buffer
                    systemInput.nextLine();
                    System.out.println(people[0].toString());
                    System.out.println("Do you want to talk to the Daughter?");
                    listen = "Y".equalsIgnoreCase(systemInput.nextLine());

                    break;
                }
                case '*':
                {
                    if (detective.getCurrentRoom().previousRoom != null)
                    {
                        clearScreen();
                        detective.moveToAnotherRoom(detective.getCurrentRoom().previousRoom);
                        detective.setLocationToPreviousRoom();
                    }

                    break;
                }
                case '/':
                {
                    clearScreen();
                    //check if at ground area
                    //todo - move checking for location in the room - e.g. ground checks if
                    //the player is in the ground
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
                    } else if (detective.getCurrentRoom().getClass() == house.getClass())
                    {
                        //in front of maid's room
                        if (detective.getxCoord() == 2 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomMaid);
                            detective.setLocationToNewRoom();

                            //in front of butler's room
                        } else if (detective.getxCoord() == 5 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomButler);
                            detective.setLocationToNewRoom();

                            //in front of wife's room
                        } else if (detective.getxCoord() == 9 && detective.getyCoord() == 24)
                        {
                            detective.moveToAnotherRoom(roomWife);
                            detective.setLocationToNewRoom();

                            //in front of working area
                        } else if (detective.getxCoord() == 11 && detective.getyCoord() == 24)
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

            if (listen)
            {
                try
                {
                    printTalk(landedSquare);
                } catch (IOException ex)
                {
                    System.out.println(ex);
                }

            }

            if (keyPress == 'q')
            {
                systemInput.nextLine();
                stoppedPlaying = quitGame();
            }
        }
    }

    private boolean quitGame()
    {
        System.out.println("Are you sure you want to quit? (Y/N)");
        char quitGame = systemInput.next().charAt(0);

        //Check if it is invalid input
        while (!(quitGame == 'Y' || quitGame == 'y' || quitGame == 'N' || quitGame == 'n'))
        {
            System.out.println("Invalid input!");
            System.out.println("Do you want to continue the game? (Y/N)");
            quitGame = systemInput.next().charAt(0);
        }

        return (quitGame == 'Y' || quitGame == 'y') ? true : false;
    }
    
    private void clearScreen(){
        System.out.println(new String(new char[100]).replace('\0','\n'));
        
    }
}
