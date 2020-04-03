/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    private Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55, 'M');
    private Detective detective;
    private NPC daughter, wife, maid, butler, assistant;
    private PasswordHint headLockedArea, tailLockedArea, headDogHouse, tailDogHouse;

    public Game()
    {
        String workingDir = System.getProperty("user.dir");
        new File(workingDir + "/FileDB/").mkdir();

        try
        {
            new FileWriter(getCompletePath("SecretTalk.txt")).close();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static String getCompletePath(String fileName)
    {
        return System.getProperty("user.dir") + "/FileDB/" + fileName;
    }

    public void setupNPC()
    {
        wife = new NPC("Belinda", 'F', 50, "Wife", "");
        maid = new NPC("Maria", 'F', 20, "Maid", wife.getRole());
        butler = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        daughter = new NPC("Sandy", 'F', 25, "Daughter", butler.getRole());
        assistant = new NPC("Ashton", 'M', 34, "Assistant", daughter.getRole());
    }

    public void setupPasswordHints()
    {
        headLockedArea = new PasswordHint(PasswordHintType.HINTHEAD, roomWorking.getLock(), 1);
        tailLockedArea = new PasswordHint(PasswordHintType.HINTTAIL, roomWorking.getLock(), 2);
        headDogHouse = new PasswordHint(PasswordHintType.HINTHEAD, ground.getLock(), 3);
        tailDogHouse = new PasswordHint(PasswordHintType.HINTTAIL, ground.getLock(), 4);
    }

    public void startGame()
    {
        setupNPC();
        setupPasswordHints();
        detective = setupPlayerInfo();
        introduceStory(detective);
        ground.hints.clear();
        startGameLoop();
    }

    private void startGameLoop()
    {
        boolean stoppedPlaying = false;
        int conversationLevel = 1;

        while (!stoppedPlaying)
        {
            char keyPress = '\0';

            //access ground => print ground
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's'
                    || keyPress == 'w' || keyPress == 'q'))
            {
                System.out.println("Press a, s, d, w then enter to move. "
                        + "Press q then enter to quit.");
                keyPress = systemInput.next().charAt(0);
            }

            clearScreen();

            conversationLevel = playerHits(conversationLevel, keyPress);

            if (detective.getGrabbedHints() >= 4)
            {
                stoppedPlaying = guessKiller();
            } else if (keyPress == 'q')
            {
                systemInput.nextLine();
                stoppedPlaying = quitGame();
            }
        }
    }

    private Detective setupPlayerInfo()
    {
        //Promt user input
        System.out.print("Please enter a name: ");
        String userName = systemInput.nextLine();

        char userGender = '\0';
        do
        {
            System.out.print("Please enter a gender(M/F): ");
            userGender = systemInput.next().charAt(0);
        } while (!(userGender == 'M' || userGender == 'm' || userGender == 'F'
                || userGender == 'f'));

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
        System.out.println(detective.getName() + " is working in "
                + (detective.getGender() == 'M' ? "his " : "her ")
                + "office and reading some news.");
        System.out.println("A police officer runs to you:");
        System.out.println("\"" + (detective.getGender() == 'M' ? "Sir " : "Madam ")
                + detective.getName() + "!\"");
        System.out.println("\"There was a murder at Royal Street! "
                + "We need your help!\"");

        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y'
                || enterPremises == 'N' || enterPremises == 'n'))
        {
            System.out.println("Do you want to enter the compound?(Y/N)");
            enterPremises = systemInput.next().charAt(0);
        }

        //If player does not want to enter the compound
        if (enterPremises != 'Y' && enterPremises != 'y')
        {
            System.out.println("\n\"Sorry I do not have time at the moment...\"");
            System.out.println("Right then, you went to one of your customers' house "
                    + "- a bilionaire names " + victim.getName() + ".");
            System.out.println("Coincidentally, your customer is the victim "
                    + "who was mentioned by the police");
        }

        ground.printRoom(ground.getName());
    }

    private int playerHits(int conversationLevel, char keyPress)
    {
        char currentSquare = detective.move(keyPress);
        String unlockNPC = "";

        switch (currentSquare)
        {
            //todo - handling of NPC character cases should be in NPC class
            case 'B':
            {
                System.out.println(butler.toString());
                butler.getConversation().talk();
                unlockNPC = butler.getUnlockNPC();

                break;
            }
            case 'W':
            {
                System.out.println(wife.toString());
                wife.getConversation().talk();
                unlockNPC = wife.getUnlockNPC();

                wife.tryToPlaceHint(roomWorking, "Gloves", "A worn-out pair of gloves", 0, 5);

                break;
            }
            case 'A':
            {
                System.out.println(assistant.toString());
                assistant.getConversation().talk();
                unlockNPC = assistant.getUnlockNPC();

                assistant.tryToPlaceHint(ground, "Knife", "A bloody knife",
                        0, 10);

                break;
            }
            case 'M':
            {
                System.out.println(maid.toString());
                maid.getConversation().talk();
                unlockNPC = maid.getUnlockNPC();

                maid.tryToPlaceHint(ground, "Cheescake", "An innocuous-looking cheesecake",
                        0, ground.getWidth() - 3);

                break;
            }
            case 'D':
            {
                System.out.println(daughter.toString());
                daughter.getConversation().talk();
                unlockNPC = daughter.getUnlockNPC();

                daughter.tryToPlaceHint(house, "Sedative", "A powerful sedative", 0, house.getWidth() - 10);

                break;
            }
            case 'V':
            {
                System.out.println(victim.toString());

                break;
            }
            case 'd':
            {
                System.out.println("Oh wait! The dog is dead!");

                break;
            }
            case 'o':
            {
                System.out.println("Oh wait! The dog is dead!");

                break;
            }
            case 'g':
            {
                System.out.println("Oh wait! The dog is dead!");

                break;
            }
            //todo - handling of * and / cases should be in Detective class
            case '*':
            {
                if (detective.getCurrentRoom().getPreviousRoom() != null)
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
            case '#':
            {
                System.out.println("This door is locked!\n");
                headDogHouse.printHintFile();
                detective.getCurrentRoom().checkPassword();

                break;
            }
            case '$':
            {
                if (headLockedArea.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    headLockedArea.saveHint();
                }

                break;
            }
            case '@':
            {
                if (tailLockedArea.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    tailLockedArea.saveHint();
                }

                break;
            }
            case '!':
            {
                if (headDogHouse.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    headDogHouse.saveHint();
                }

                break;
            }
            case '0':
            {
                if (tailDogHouse.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    tailDogHouse.saveHint();
                }

                break;
            }

            default:
                break;
        }

        //todo - if clause should be in NPC class or Conversation class?
        if (conversationLevel == 1 && butler.getConversation().hasTalkedWithPlayer()
                && wife.getConversation().hasTalkedWithPlayer() && daughter.getConversation().hasTalkedWithPlayer()
                && assistant.getConversation().hasTalkedWithPlayer() && maid.getConversation().hasTalkedWithPlayer())
        {
            assistant.unlockConversation();
            conversationLevel = 2;
        } else if (conversationLevel == 2)
        {
            if (butler.getRole().equals(unlockNPC))
            {
                butler.unlockConversation();
            } else if (wife.getRole().equals(unlockNPC))
            {
                wife.unlockConversation();
            } else if (maid.getRole().equals(unlockNPC))
            {
                maid.unlockConversation();
            } else if (daughter.getRole().equals(unlockNPC))
            {
                daughter.unlockConversation();
            }
        }

        return conversationLevel;
    }

    private boolean guessKiller()
    {
        System.out.println("\nYou have unlocked all the hints in the game! Who do you think killed the victim?");
        System.out.println("Press 1 to choose Assistant.");
        System.out.println("Press 2 to choose Butler.");
        System.out.println("Press 3 to choose Daughter.");
        System.out.println("Press 4 to choose Maid.");
        System.out.println("Press 5 to choose Wife.");

        System.out.println("> ");
        int killerInput = systemInput.nextInt();

        while (killerInput < 1 && killerInput > 5)
        {
            System.out.println("Invalid input! Please enter 1 to 5 only: ");
            killerInput = systemInput.nextInt();
        }

        return killerConfirm(killerInput);
    }

    private boolean killerConfirm(int killerInput)
    {
        if (killerInput == 3)
        {
            System.out.println("Fantastic! The daughter is the killer!");
            System.out.println("She left without the cheesecake and the victim "
                    + "\nate a piece of this - killing him - before he got stabbled by the Maid.");
            System.out.println("After stabbing the victim, the Maid cleaned the Working room"
                    + "\nand threw the cheesecake in the dog's house.");
            System.out.println("YOU WIN THE GAME!");
        } else
        {
            System.out.println("The killer is a different person. You lost the game! Try again next time!");
        }

        return true;
    }

    public boolean playAgain()
    {
        systemInput.nextLine();

        System.out.println("Do you want to play again? (Press 'Y')");

        return "y".equalsIgnoreCase(systemInput.nextLine());
    }

    private boolean quitGame()
    {
        System.out.println("Are you sure you want to quit? (Y/N)");
        char quitGame = systemInput.next().charAt(0);

        //Check if it is invalid input
        while (!(quitGame == 'Y' || quitGame == 'y' || quitGame == 'N' || quitGame == 'n'))
        {
            System.out.println("Invalid input!");
            System.out.println("Are you sure you want to quit? (Y/N)");
            quitGame = systemInput.next().charAt(0);
        }

        return (quitGame == 'Y' || quitGame == 'y');
    }

    private static void clearScreen()
    {
        System.out.println(new String(new char[100]).replace('\0', '\n'));
    }
}
