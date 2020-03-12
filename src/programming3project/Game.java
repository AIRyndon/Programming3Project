/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.IOException;
import java.nio.Buffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
    Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55, 'M');
    private NPC daughter, wife, maid, butler, assistant;
  
    public void setupNPC() throws IOException
    {
        daughter = new NPC("Calista", 'F', 25, "Daughter", "");
        wife = new NPC("Belinda", 'F', 50, "Wife", "");
        maid = new NPC("Cindel", 'F', 20, "Maid", daughter.getRole());
        butler = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        assistant = new NPC("Ashton", 'M', 34, "Assistant", wife.getRole());
    }
    
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
        } while (!(userGender == 'M' || userGender == 'm' || userGender == 'F' || 
                userGender == 'f'));

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

        Detective detective = new Detective(userName, userGender, userAge, ground);

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
        System.out.println(detective.getName() + " is working in" + 
                (detective.getGender() == 'M' ? "his " : "her ") + 
                "office and reading some news.");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Sir " : "Madam ")
                + detective.getName() + "!\"");
        System.out.println("A police officer runs to you:");
        System.out.println("\"There was a murder at Royal Street! "
                + "Please come there now!\"");

        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y' || 
                enterPremises == 'N' || enterPremises == 'n'))
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
            System.out.println("Coincidentally, your customer is the victim "
                    + "who was mentioned by the police");
        }

        ground.printRoom(ground.getName());
    }

    public void startGame() throws IOException
    {
        setupNPC();
        detective = setupPlayerInfo();
        introduceStory(detective);
        startGameLoop();
    }

    private void startGameLoop() throws IOException
    {
        boolean stoppedPlaying = false;
        int countUnlock = 0;

        while (!stoppedPlaying)
        {
            char keyPress = '\0';

            //access ground => print ground
            //Moving
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's' || 
                    keyPress == 'w' || keyPress == 'q'))
            {
                System.out.println("Press a, s, d, w then enter to move. "
                        + "Press q then enter to quit.");
                keyPress = systemInput.next().charAt(0);
            }
            
            clearScreen();
            
            playerHits(countUnlock, keyPress);
            
            if (keyPress == 'q')
            {
                systemInput.nextLine();
                stoppedPlaying = quitGame();
            }
        }
    }
    
    private void playerHits(int countUnlock, char keyPress)
    {
        char landedSquare = detective.move(keyPress);
        String unlockNPC = "";

        switch (landedSquare) 
        {
            case 'B':
            {
                System.out.println(butler.toString());                    
                butler.getTalk().talk();
                unlockNPC = butler.getUnlockNPC();

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
            case 'V': 
            {
                System.out.println(victim.toString());                    
                daughter.getTalk().talk();
                unlockNPC = daughter.getUnlockNPC();

                break; 
            }
            case 'd':
            {
                System.out.println("Oh wait! The dog was dead!");

                break;
            }
            case 'o':
            {
                System.out.println("Oh wait! The dog was dead!");

                break;
            }
            case 'g':
            {
                System.out.println("Oh wait! The dog was dead!");

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

        if(countUnlock == 0 && butler.getTalk().isHasTalk() && 
                wife.getTalk().isHasTalk() && daughter.getTalk().isHasTalk() && 
                assistant.getTalk().isHasTalk() && maid.getTalk().isHasTalk())
        {
            assistant.unlockTalk();
            countUnlock = 1;
        }

        else if(countUnlock == 1)
        {
            if(butler.getRole() == unlockNPC)
            {
                butler.unlockTalk();
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
    
    private void clearScreen() throws IOException
    {
        System.out.println(new String(new char[100]).replace('\0','\n'));    
    }
}