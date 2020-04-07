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
    private final static Scanner SYSTEMINPUT = new Scanner(System.in);
    private final Ground ground = new Ground("Ground", null);
    private final House house = new House("House", ground);
    private final RoomMaid roomMaid = new RoomMaid("Maid's Room", house);
    private final RoomButler roomButler = new RoomButler("Butler's Room", house);
    private final RoomWife roomWife = new RoomWife("Wife's Room", house);
    private final RoomWorking roomWorking = new RoomWorking("Work Room", house);
    private final Victim victim = new Victim("Bosh", "President of KPI Cooperation", 55, 'M');
    private int conversationLevel = 1;
    private String unlockNPC = "";
    private Detective detective;
    private NPC daughter, wife, maid, butler, assistant;
    private PasswordHint headLockedArea, tailLockedArea, headDogHouse, tailDogHouse;

    public Game()
    {
        //Setup writable game files
        String workingDir = System.getProperty("user.dir");
        new File(workingDir + "/FileDB/").mkdir();
        clearWritableFiles();
    }

    public static String getCompletePath(String fileName)
    {
        return System.getProperty("user.dir") + "/FileDB/" + fileName;
    }

    public void setupNPC()
    {
        wife = new NPC("Belinda", 'F', 50, "Wife", "");
        maid = new NPC("Chelsea", 'F', 20, "Maid", wife.getRole());
        butler = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        daughter = new NPC("Sandy", 'F', 25, "Daughter", butler.getRole());
        assistant = new NPC("Ashton", 'M', 34, "Assistant", daughter.getRole());
    }

    public void setupPasswordCodes()
    {
        headLockedArea = new PasswordHint(PasswordHintType.HINTHEAD, roomWorking.getLock(), 1, "Alprazolam");
        tailLockedArea = new PasswordHint(PasswordHintType.HINTTAIL, roomWorking.getLock(), 2, "9:50");
        headDogHouse = new PasswordHint(PasswordHintType.HINTHEAD, ground.getLock(), 3, "Bosh");
        tailDogHouse = new PasswordHint(PasswordHintType.HINTTAIL, ground.getLock(), 4, "Work Room");
    }

    public void startGame()
    {
        setupNPC();
        setupPasswordCodes();
        setupPlayerInfo();
        introduceStory(detective);
        startGameLoop();
    }

    private void startGameLoop()
    {
        boolean stillPlaying = true;

        while (stillPlaying)
        {
            char keyPress = '\0';

            //access ground => print ground
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's'
                    || keyPress == 'w' || keyPress == 'q'))
            {
                System.out.println("Press a, s, d, w then enter to move. "
                        + "Press q then enter to quit.");
                keyPress = SYSTEMINPUT.next().charAt(0);
            }

            clearScreen();

            handlePlayerInteraction(keyPress);
            updateConversationLevel();

            if (detective.getGrabbedHints() >= 4)
            {
                stillPlaying = guessKiller();
            } else if (keyPress == 'q')
            {
                SYSTEMINPUT.nextLine();
                stillPlaying = quitGame();
            }
        }
    }

    private void setupPlayerInfo()
    {
        //Promt user input
        System.out.print("Please enter a name: ");
        String userName = SYSTEMINPUT.nextLine();

        char userGender = '\0';
        do
        {
            System.out.print("Please enter a gender(M/F): ");
            userGender = SYSTEMINPUT.next().charAt(0);
        } while (!(userGender == 'M' || userGender == 'm' || userGender == 'F'
                || userGender == 'f'));

        //Clear buffer
        SYSTEMINPUT.nextLine();

        int userAge = 0;
        do
        {
            System.out.print("Please enter your age: ");

            if (SYSTEMINPUT.hasNextInt())
            {
                userAge = SYSTEMINPUT.nextInt();
            } else
            {
                SYSTEMINPUT.nextLine();
            }
        } while (userAge <= 0);

        detective = new Detective(userName, userGender, userAge, ground);

        detective.setBackground("Mysterious fellow");
        detective.setCurrentRoom(ground);
        detective.setPreviousRoom(null);
        detective.setPlayArea(ground.movingArea);

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
            enterPremises = SYSTEMINPUT.next().charAt(0);
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

    private void updateConversationLevel()
    {
        if (conversationLevel == 1 && butler.getLine().hasTalkedWithPlayer()
                && wife.getLine().hasTalkedWithPlayer() && daughter.getLine().hasTalkedWithPlayer()
                && assistant.getLine().hasTalkedWithPlayer() && maid.getLine().hasTalkedWithPlayer())
        {
            assistant.unlockNPCLine();
            conversationLevel = 2;
        } else if (conversationLevel == 2)
        {
            if (butler.getRole().equals(unlockNPC))
            {
                butler.unlockNPCLine();
            } else if (wife.getRole().equals(unlockNPC))
            {
                wife.unlockNPCLine();
            } else if (maid.getRole().equals(unlockNPC))
            {
                maid.unlockNPCLine();
            } else if (daughter.getRole().equals(unlockNPC))
            {
                daughter.unlockNPCLine();
            }
        }
    }

    private void handlePlayerInteraction
        (char keyPress)
    {
        char currentSquare = detective.move(keyPress);

        switch (currentSquare)
        {
            //todo - handling of NPC character cases should be in NPC class
            case 'B':
            {
                System.out.println(butler.toString());
                butler.getLine().talk();
                unlockNPC = butler.getUnlockNPC();
                
                butler.tryToPlaceHint(roomWorking, "Old Picture", "A picture of a young girl - "
                        + "\nthe girl has a resemblance with the maid", 0, 5);

                break;
            }
            case 'W':
            {
                System.out.println(wife.toString());
                wife.getLine().talk();
                unlockNPC = wife.getUnlockNPC();

                wife.tryToPlaceHint(roomWorking, "Gloves", "A worn-out pair of gloves, there is a name on it - "
                        + "\nit is illegible, you only recognize the letters ATO,", 0, 5);

                break;
            }
            case 'A':
            {
                System.out.println(assistant.toString());
                assistant.getLine().talk();
                unlockNPC = assistant.getUnlockNPC();

                assistant.tryToPlaceHint(ground, "Knife", "The blade is bloody. . .",
                        0, ground.getWidth() - 7);

                break;
            }
            case 'M':
            {
                System.out.println(maid.toString());
                maid.getLine().talk();
                unlockNPC = maid.getUnlockNPC();

                maid.tryToPlaceHint(ground, "Cheescake", "An innocuous-looking cheesecake",
                        0, ground.getWidth() - 3);

                break;
            }
            case 'D':
            {
                System.out.println(daughter.toString());
                daughter.getLine().talk();
                unlockNPC = daughter.getUnlockNPC();

                daughter.tryToPlaceHint(house, "Alprazolam", "A powerful sedative -"
                        + "\ncan have side-effects when taken regularly", 0, house.getWidth() - 10);

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
            case 'X':
            {
                detective.getCurrentRoom().hints.forEach(hint ->
                {
                    if (hint.getxLocation() == detective.getxCoord()
                            && hint.getyLocation() == detective.getyCoord())
                    {
                        System.out.println(hint.toString());
                        detective.incrementGrabbedHints();
                    }
                });

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
        int guess = SYSTEMINPUT.nextInt();

        while (guess < 1 && guess > 5)
        {
            System.out.println("Invalid input! Please enter 1 to 5 only: ");
            guess = SYSTEMINPUT.nextInt();
        }

        return confirmKiller(guess);
    }

    private boolean confirmKiller(int guess)
    {
        if (guess == 3)
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

        return false;
    }

    public boolean playAgain()
    {
        SYSTEMINPUT.nextLine();

        System.out.println("Do you want to play again? (Press 'Y')");

        return "y".equalsIgnoreCase(SYSTEMINPUT.nextLine());
    }

    private boolean quitGame()
    {
        System.out.println("Are you sure you want to quit? (Y/N)");
        char quitGame = SYSTEMINPUT.next().charAt(0);

        //Check if it is invalid input
        while (!(quitGame == 'Y' || quitGame == 'y' || quitGame == 'N' || quitGame == 'n'))
        {
            System.out.println("Invalid input!");
            System.out.println("Are you sure you want to quit? (Y/N)");
            quitGame = SYSTEMINPUT.next().charAt(0);
        }

        if (quitGame == 'Y' || quitGame == 'y')
        {
            clearWritableFiles();
        }

        return !(quitGame == 'Y' || quitGame == 'y');
    }

    private void clearWritableFiles()
    {
        try
        {
            new FileWriter(getCompletePath("SecretTalk.txt")).close();
            new FileWriter(getCompletePath("Hints.txt")).close();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static void clearScreen()
    {
        System.out.println(new String(new char[100]).replace('\0', '\n'));
    }
}
