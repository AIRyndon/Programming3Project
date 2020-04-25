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
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author airyn
 */
public class Game 
{
    //Required game fields
    public final static Scanner SYSTEMINPUT = new Scanner(System.in);
    public final static Random RANDOM = new Random();
    private final Story storyIntro;
    private final Ground ground = new Ground("Ground", null);
    private final House house = new House("House", ground);
    private final RoomMaid roomMaid = new RoomMaid("Maid's Room", house);
    private final RoomButler roomButler = new RoomButler("Butler's Room", house);
    private final RoomWife roomWife = new RoomWife("Wife's Room", house);
    private final RoomWorking roomWorking = new RoomWorking("Work Room", house);
    private final Victim victim = new Victim("Bosh", "President of KPI Cooperation", 
            55, 'M', "His working room");
    private int conversationLevel = 1;
    private String unlockNPC = "";
    private Detective detective;
    private NPC daughter, wife, maid, butler, assistant;
    private KeyPassword headLockedArea, tailLockedArea, headDogHouse, tailDogHouse;

    public Game() throws IOException
    {
        this.storyIntro = new Story();
        
        //Setup writable game files
        String workingDir = System.getProperty("user.dir");
        new File(workingDir + "/FileDB/").mkdir();
        clearWritableFiles();
    }

    public static String getCompletePath(String fileName)
    {
        return System.getProperty("user.dir") + "/FileDB/" + fileName;
    }

    public void setupNPC() throws IOException
    {
        daughter = new NPC("Sandy", 'F', 25, "Daughter", "");
        maid = new NPC("Chelsea", 'F', 20, "Maid", daughter.getRole());
        butler = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        wife = new NPC("Belinda", 'F', 50, "Wife", "");
        assistant = new NPC("Ashton", 'M', 34, "Assistant", wife.getRole());
    }

    public void setupPasswordCodes() throws IOException
    {
        headLockedArea = new KeyPassword(KeyPasswordType.KEYHEAD, roomWorking.getLock(), 1, "Alprazolam");
        tailLockedArea = new KeyPassword(KeyPasswordType.KEYTAIL, roomWorking.getLock(), 2, "11:03");
        headDogHouse = new KeyPassword(KeyPasswordType.KEYHEAD, ground.getLock(), 3, "2 years");
        tailDogHouse = new KeyPassword(KeyPasswordType.KEYTAIL, ground.getLock(), 4, "Chelsea");
    }

    public void startGame() throws IOException
    {
        setupNPC();
        setupPasswordCodes();
        setupPlayerInfo();
        //introduceStory(detective);
        startGameLoop();
    }

    private void startGameLoop() throws IOException
    {
        boolean playing = true;

        while (playing)
        {
            char keyPress = '\0';

            //access ground => print ground
            while (!(keyPress == 'a' || keyPress == 'd' || keyPress == 's'
                    || keyPress == 'w' || keyPress == 'q'))
            {
                System.out.print("\nPress a, s, d, w then enter to move. "
                        + "Press q then enter to quit: ");
                keyPress = SYSTEMINPUT.next().charAt(0);
            }

            handlePlayerInteraction(keyPress);
            updateConversationLevel();

            if (detective.getGrabbedHints() == 5)
            {
                playing = guessKiller();
            } 
            else if (keyPress == 'q')
            {
                SYSTEMINPUT.nextLine();
                playing = quitGame();
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
        } while (!(userGender == 'M'
                || userGender == 'm'
                || userGender == 'F'
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
            } 
            else
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

    private void introduceStory(Detective detective) throws IOException
    {
        System.out.println(detective);

        pauseScreen(2000);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(new Date()));

        //Story begins
        pauseScreen(1000);
        System.out.println(storyIntro.getStory().get(0));
        pauseScreen(2000);
        System.out.println((detective.getGender() == 'M' ? "\"Sir " : "\"Madam ") 
                + detective.getName() + storyIntro.getStory().get(1));
        pauseScreen(2000);
        System.out.println(storyIntro.getStory().get(2));
        pauseScreen(2000);
        
        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y'
                || enterPremises == 'N' || enterPremises == 'n'))
        {
            System.out.print("\nDo you want to enter the compound (Y/N)? ");
            enterPremises = SYSTEMINPUT.next().charAt(0);
        }
        
        pauseScreen(1000);            
        //If player does not want to enter the compound
        if (enterPremises != 'Y' && enterPremises != 'y')
        {
            System.out.println("\n" + storyIntro.getStory().get(4));
            
            pauseScreen(2000);
            System.out.println(storyIntro.getStory().get(5)+ victim.getName() + ".");
            
            pauseScreen(2000);
            System.out.println(storyIntro.getStory().get(6));
            
            pauseScreen(2000);
            System.out.println(storyIntro.getStory().get(7));
        }
        else
        {
            System.out.println("\n" + storyIntro.getStory().get(9));
            
            pauseScreen(2000);
            System.out.println(storyIntro.getStory().get(10));
            
            pauseScreen(2000);
            System.out.println(storyIntro.getStory().get(11));
        }
        
        pauseScreen(2000);
        System.out.println("\n" + victim.toString());
        
        pauseScreen(2000);
        System.out.println(storyIntro.getStory().get(13));
        
        pauseScreen(2000);
        ground.printRoom(ground.getName());
        
        
    }

    private void updateConversationLevel()
    {
        if (conversationLevel == 1
                && butler.hasTalkedWithPlayer()
                && wife.hasTalkedWithPlayer()
                && daughter.hasTalkedWithPlayer()
                && maid.hasTalkedWithPlayer())
        {
            assistant.unlockLines();
            conversationLevel = 2;
        }
        else if (conversationLevel == 2 && wife.getRole().equals(unlockNPC))
        {
            wife.unlockLines();
            conversationLevel = 3;
        }
        else if(conversationLevel == 3 && butler.getNPCLine().isUnlocked())
        {
            if (maid.getRole().equals(unlockNPC))
            {
                maid.unlockLines();
            } 
            else if (daughter.getRole().equals(unlockNPC))
            {
                daughter.unlockLines();
            }
        }
    }

    private void handlePlayerInteraction(char keyPress) throws IOException
    {
        char currentSquare = detective.move(keyPress);

        switch (currentSquare)
        {
            //todo - handling of NPC character cases should be in NPC class
            case 'B':
            {
                System.out.println(butler.toString());
                boolean playerOpenedTalk = butler.getNPCLine().talk();

                if(playerOpenedTalk)
                {
                    unlockNPC = butler.getUnlockNPC();
                    butler.tryToPlaceHint(roomMaid, "Gloves", "A worn-out pair of gloves, there is a name on it - "
                            + "\nit is illegible, you only recognize the letters ATO", 3, 39);
                //Hey, what does ATO mean by the way? =))
                }
                
                break;
            }
            case 'W':
            {
                System.out.println(wife.toString());
                boolean playerOpenedTalk = wife.getNPCLine().talk();

                if(playerOpenedTalk)
                {
                    unlockNPC = wife.getUnlockNPC();
                    wife.tryToPlaceHint(roomWorking, "Old Picture", "A picture of a young girl - "
                            + "\nthe girl has a resemblance with the maid", 0, 5);
                }
                
                break;
            }
            case 'A':
            {
                System.out.println(assistant.toString());
                boolean playerOpenedTalk = assistant.getNPCLine().talk();

                if(playerOpenedTalk)
                {
                    unlockNPC = assistant.getUnlockNPC();
                    assistant.tryToPlaceHint(house, "Alprazolam", "A powerful sedative -"
                            + "\ncan have side-effects when taken regularly", 0, house.getWidth() - 10);
                }
                break;
            }
            case 'M':
            {
                System.out.println(maid.toString());
                boolean playerOpenedTalk = maid.getNPCLine().talk();

                if(playerOpenedTalk)
                {
                    unlockNPC = maid.getUnlockNPC();
                    maid.tryToPlaceHint(ground, "Cheescake", "An innocuous-looking cheesecake",
                            0, ground.getWidth() - 3);
                }
                break;
            }
            case 'D':
            {
                System.out.println(daughter.toString());
                boolean playerOpenedTalk = daughter.getNPCLine().talk();

                if(playerOpenedTalk)
                {
                    unlockNPC = daughter.getUnlockNPC();
                    daughter.tryToPlaceHint(roomWorking, "Knife", "The blade is bloody. . .",
                            9, ground.getWidth() + 10);
                }
                
                break;
            }
            case 'V':
            {
                System.out.println('\n' + victim.toString());

                break;
            }
            case 'd':
            {
                System.out.println("\nOh wait! The dog is dead!");

                break;
            }
            case 'o':
            {
                System.out.println("\nOh wait! The dog is dead!");

                break;
            }
            case 'g':
            {
                System.out.println("\nOh wait! The dog is dead!");

                break;
            }
            case '*':
            {
                if (detective.getCurrentRoom().getPreviousRoom() != null)
                {
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
                        System.out.println("\nYou found something. . ." + hint.toString());
                        GroundHint.saveGroundHint(hint);
                        detective.incrementGrabbedHints();
                    }
                    
                    //When user hits Old Picture, Butler talk unlock.
                    if(hint.getName().equals("Old Picture"))
                    {
                        butler.unlockLines();
                    }
                });

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
            case '#':
            {
                System.out.println("\nThis door is locked!");
                headDogHouse.printSavedKeyPasswords();
                detective.getCurrentRoom().checkPassword();
                break;
            }
            case '$':
            {
                if (headLockedArea.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    headLockedArea.saveKeyPassword();
                    detective.getCurrentRoom().printRoom(detective.getCurrentRoom().getName());
                }

                break;
            }
            case '!':
            {
                if (tailLockedArea.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    tailLockedArea.saveKeyPassword();
                    detective.getCurrentRoom().printRoom(detective.getCurrentRoom().getName());
                }

                break;
            }
            case '0':
            {
                if (headDogHouse.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    headDogHouse.saveKeyPassword();
                    detective.getCurrentRoom().printRoom(detective.getCurrentRoom().getName());
                }

                break;
            }
            case '@':
            {
                if (tailDogHouse.promptAnswer())
                {
                    detective.getCurrentRoom().removeCharacter(currentSquare);
                    tailDogHouse.saveKeyPassword();
                    detective.getCurrentRoom().printRoom(detective.getCurrentRoom().getName());
                }

                break;
            }
            default:
                break;
        }
    }

    private boolean guessKiller()
    {
        System.out.println("\nCongratulations! You have unlocked all the hints in the game!\n");
        System.out.println("................Time to guess who is the murderer................");
        System.out.println("Below are your saved notes to help you decide.");
      
        printSavedNPCLines();
        printSavedHints();        

        System.out.println("Press 1 to choose Assistant.");
        System.out.println("Press 2 to choose Butler.");
        System.out.println("Press 3 to choose Daughter.");
        System.out.println("Press 4 to choose Maid.");
        System.out.println("Press 5 to choose Wife.");

        int guess = SYSTEMINPUT.nextInt();
        while (guess < 1 && guess > 5)
        {
            System.out.println("Invalid input! Please enter 1 to 5 only: ");
            guess = SYSTEMINPUT.nextInt();
        }

        return confirmKiller(guess);
    }

    public static void printSavedNPCLines()
    {
        LinkedList<String> savedLines = NPCLine.getSavedLines();
        
        if(savedLines.size() > 0)
        {    
            System.out.println("Opening your saved talks:");

            for (int index = 0; index < savedLines.size(); index++)
            {
                System.out.println(savedLines.get(index));
            }
        }
        else
        {
            System.out.println("Your saved talk file is empty!");
        }
    }
    
    public static void printSavedHints()
    {
        LinkedList<GroundHint> savedHints = GroundHint.getSavedHints();
        
        if(savedHints.size() > 0)
        {
            System.out.println("Opening your saved hints:");
            
            for (int index = 0; index < savedHints.size(); index++)
            {
                System.out.println(savedHints.get(index));
            }
        }
        else
        {
            System.out.println("Your saved hint file is empty!");
        }
    }
    
    //Todo: add printSaveKeyPassword method here
    //public static void printSaveKeyPassword()
    
    private boolean confirmKiller(int guess)
    {
        if (guess == 3)
        {
            System.out.println("Fantastic! The daughter is the killer!");
            System.out.println("She left without the cheesecake and the victim "
                    + "\nate a piece of this - killing him - before he got stabbled by the Maid.");
            System.out.println("After stabbing the victim, the Maid cleaned the Working room"
                    + "\nand threw the cheesecake in the dog's house.");
            System.out.println("YOU WIN THE GAME!\n");
        } 
        else
        {
            System.out.println("The killer is a different person. YOU LOST THE GAME!\n");
        }

        return false;
    }

    public boolean playAgain()
    {
        SYSTEMINPUT.nextLine();
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
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
            new FileWriter(getCompletePath("Conversations.txt")).close();
            new FileWriter(getCompletePath("PasswordHints.txt")).close();
            new FileWriter(getCompletePath("GroundHints.txt")).close();
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void pauseScreen(int time)
    {
//        ThreadPauseScreen t = new ThreadPauseScreen();
//        t.run();
//        
//        try
//        {
//            Thread.sleep(time);
//        }
//        catch (InterruptedException ex)
//        {
//            ex.printStackTrace(System.out);
//        }
//            
//        t.stop();
        
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace(System.out);
        }
    }
}