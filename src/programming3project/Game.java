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

public class Game
{
    // <editor-fold defaultstate="collapsed" desc="Required game fields">
    public final static Scanner SYSTEMINPUT = new Scanner(System.in);
    public final static Random RANDOM = new Random();
    private final Story story = new Story();
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Game() throws IOException
    {
        //Setup writable game files
        String workingDir = System.getProperty("user.dir");
        new File(workingDir + "/FileDB/").mkdir();
        clearWritableFiles();
        resetStaticVariables();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public static String getCompletePath(String fileName)
    {
        return System.getProperty("user.dir") + "/FileDB/" + fileName;
    }
    
    public static void pauseScreen(int time)
    {
        try
        {
            Thread.sleep(time);
        } 
        catch (InterruptedException ex)
        {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void printSavedHints(int pausedTime)
    {
        LinkedList<Hint> savedHints = Hint.getSavedHints();
        
        if (savedHints.size() > 0)
        {
            System.out.println("\nOpening your saved hints...");
            pauseScreen(savedHints.size() * pausedTime);
            
            for (int index = 0; index < savedHints.size(); index++)
            {
                System.out.println(savedHints.get(index));
            }
        }
        else
        {
            System.out.println("\nYou haven't saved any hints yet!");
        }
        
        System.out.println();
    }
    
    public static void printSavedKeyPasswords(int pausedTime)
    {
        LinkedList<String> savedCodes = KeyPassword.getSavedCodes();
        
        if (savedCodes.size() > 0)
        {
            System.out.println("\nOpening your saved password codes...\n");
            pauseScreen(savedCodes.size() * pausedTime);
            
            for (int index = 0; index < savedCodes.size(); index++)
            {
                System.out.println(savedCodes.get(index));
            }
        }
        else
        {
            System.out.println("\nYou don't have any code saved!");
        }
        
        System.out.println();
    }
    
    public static void printSavedNPCLines(int pausedTime)
    {
        LinkedList<String> savedLines = NPCLine.getSavedLines();
        
        if (savedLines.size() > 0)
        {
            System.out.println("\nOpening your saved conversations...\n");
            pauseScreen(savedLines.size() * pausedTime);
            
            for (int index = 0; index < savedLines.size(); index++)
            {
                System.out.println(savedLines.get(index));
            }
        }
        else
        {
            System.out.println("\nYour saved talk file is empty!\n");
        }
    }

    public boolean playAgain()
    {
        SYSTEMINPUT.nextLine();
        System.out.println(".");
        System.out.println(".");
        System.out.println(".\n");
        System.out.println("Do you want to play again (press 'y')? ");

        return "y".equalsIgnoreCase(SYSTEMINPUT.nextLine());
    }

    public void startGame() throws IOException
    {
        setupNPC();
        setupPasswordCodes();
        setupPlayerInfo();
        introduceStory(detective);
        startGameLoop();
    }
    // </editor-fold>

    // <editor-fold desc="Private methods">
    private static void openLogBook()
    {
        int page = 0;
        
        do
        {
            System.out.print("\nWhat page do you want to open? (1-3) ");
            if (SYSTEMINPUT.hasNextInt())
            {
                page = SYSTEMINPUT.nextInt();
            }
            
            Game.SYSTEMINPUT.nextLine();
        } while (page < 1 || page > 3);
        
        switch (page)
        {
            case 1:
                printSavedNPCLines(1000);
                break;
            case 2:
                printSavedHints(1000);
                break;
            case 3:
                printSavedKeyPasswords(1000);
                break;
            default:
                break;
        }
        
        char input = '\0';
        do
        {
            System.out.print("Press h again to close your logbook. ");
            input = SYSTEMINPUT.next().charAt(0);
        } while (!(input == 'h' || input == 'H'));
    }
    
    private void clearWritableFiles()
    {
        try
        {
            new FileWriter(getCompletePath("Conversations.txt"), false).close();
            new FileWriter(getCompletePath("PasswordHints.txt"), false).close();
            new FileWriter(getCompletePath("Hints.txt"), false).close();
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    private boolean confirmKiller(int guess) throws IOException
    {
        if (guess == 3)
        {
            System.out.println(story.getEnding());
            System.out.println("\nYOU WIN THE GAME!\n");
        }
        else
        {
            System.out.println("\nThe killer is a different person. \nYOU LOST THE GAME!\n");
        }
        
        return false;
    }
    
    private boolean guessKiller() throws IOException
    {
        System.out.println("\nCongratulations! You have unlocked all the hints in the game!\n");
        System.out.println("................Time to guess who is the murderer................\n");
        System.out.println("Here're your saved notes to help you decide.\n");
        
        printSavedNPCLines(1000);
        printSavedHints(1000);
        
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
    
    private void handlePlayerInteraction(String keyPress) throws IOException
    {
        char currentSquare = detective.move(keyPress);
        
        switch (currentSquare)
        {
            //todo - handling of NPC character cases should be in NPC class
            case 'B':
            {
                System.out.println(butler.toString());
                boolean playerOpenedTalk = butler.getNPCLine().talk();
                
                if (playerOpenedTalk)
                {
                    unlockNPC = butler.getUnlockNPC();
                    butler.tryToPlaceHint(roomMaid, "Gloves", "A worn-out pair of gloves, there is a name on it - "
                            + "\nit is illegible, you only recognize the letters ATO", 3, 39);
                }
                
                break;
            }
            case 'W':
            {
                System.out.println(wife.toString());
                boolean playerOpenedTalk = wife.getNPCLine().talk();
                
                if (playerOpenedTalk)
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
                
                if (playerOpenedTalk)
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
                
                if (playerOpenedTalk)
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
                
                if (playerOpenedTalk)
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
                        Hint.saveHint(hint);
                        detective.incrementGrabbedHints();
                    }
                    
                    //When user hits Old Picture, Butler talk unlock.
                    if (hint.getName().equals("Old Picture"))
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
                KeyPassword.giveLockAdvice();
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
    
    private void introduceStory(Detective detective) throws IOException
    {
        System.out.println(detective);
        
        pauseScreen(2000);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(new Date()));
        
        //Story begins
        pauseScreen(1000);
        System.out.println(story.getStory().get(0));
        pauseScreen(2000);
        System.out.println(((detective.getGender() == 'M' || detective.getGender() == 'm')
                ? "\"Sir " : "\"Madam ") + detective.getName() + story.getStory().get(1));
        pauseScreen(2000);
        System.out.println(story.getStory().get(2));
        pauseScreen(2000);
        
        char enterPremises = '\0';
        while (!(enterPremises == 'Y' || enterPremises == 'y'
                || enterPremises == 'N' || enterPremises == 'n'))
        {
            System.out.print("\nDo you want to enter the compound (y/n)? ");
            enterPremises = SYSTEMINPUT.next().charAt(0);
        }
        
        pauseScreen(1000);
        if (enterPremises != 'Y' && enterPremises != 'y')
        {
            System.out.println("\n" + story.getStory().get(4));
            
            pauseScreen(2000);
            System.out.println(story.getStory().get(5) + victim.getName() + ".");
            
            pauseScreen(2000);
            System.out.println(story.getStory().get(6));
            
            pauseScreen(2000);
            System.out.println(story.getStory().get(7));
        }
        else
        {
            System.out.println("\n" + story.getStory().get(9));
            
            pauseScreen(2000);
            System.out.println(story.getStory().get(10));
            
            pauseScreen(2000);
            System.out.println(story.getStory().get(11));
        }
        
        pauseScreen(2000);
        System.out.println("\n" + victim.toString());
        
        pauseScreen(2000);
        System.out.println("\n" + story.getStory().get(13));
        
        pauseScreen(2500);
        ground.printRoom(ground.getName());
    }
    
    private boolean quitGame()
    {
        System.out.print("Are you sure you want to quit (y/n)? ");
        char stillPlaying = SYSTEMINPUT.next().charAt(0);
        
        //Check if it is invalid input
        while (!(stillPlaying == 'Y' || stillPlaying == 'y' || stillPlaying == 'N' || stillPlaying == 'n'))
        {
            System.out.println("Invalid input!");
            System.out.println("Are you sure you want to quit (y/n)? ");
            stillPlaying = SYSTEMINPUT.next().charAt(0);
        }
        
        return (stillPlaying == 'N' || stillPlaying == 'n');
    }
    
    private void resetStaticVariables()
    {
        KeyPassword.SAVEDCODES.clear();
        Hint.SAVEDHINTS.clear();
        NPCLine.SAVEDLINES.clear();
    }
    
    private void setupNPC() throws IOException
    {
        daughter = new NPC("Sandy", 'F', 25, "Daughter", "");
        maid = new NPC("Chelsea", 'F', 20, "Maid", daughter.getRole());
        butler = new NPC("Marcello", 'M', 63, "Butler", maid.getRole());
        wife = new NPC("Belinda", 'F', 50, "Wife", "");
        assistant = new NPC("Ashton", 'M', 34, "Assistant", wife.getRole());
    }
    
    private void setupPasswordCodes() throws IOException
    {
        headLockedArea = new KeyPassword(KeyPasswordType.KEYHEAD, roomWorking.getLock(), 1, "Alprazolam");
        tailLockedArea = new KeyPassword(KeyPasswordType.KEYTAIL, roomWorking.getLock(), 2, "11:03");
        headDogHouse = new KeyPassword(KeyPasswordType.KEYHEAD, ground.getLock(), 3, "30 months");
        tailDogHouse = new KeyPassword(KeyPasswordType.KEYTAIL, ground.getLock(), 4, "Chelsea");
    }
    
    private void setupPlayerInfo()
    {
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
    
    private void startGameLoop() throws IOException
    {
        //clear console
        SYSTEMINPUT.nextLine();
        boolean playing = true;
        
        while (playing)
        {
            String keyPress = " ";
            
            do
            {
                System.out.println("\nPress h then enter to access your logbook.");
                System.out.println("Press q then enter to quit.");
                System.out.println("Press a, s, d, w then enter to move:");
                
                keyPress = SYSTEMINPUT.nextLine();
            } while (!(keyPress.equals("a") || keyPress.equals("d")
                    || keyPress.equals("s") || keyPress.equals("w")
                    || keyPress.equals("h") || keyPress.equals("q")));
            
            if (keyPress.equals("h"))
            {
                openLogBook();
                SYSTEMINPUT.nextLine();
            }
            
            handlePlayerInteraction(keyPress);
            updateConversationLevel();
            
            if (detective.getGrabbedHints() == 5)
            {
                pauseScreen(1500);
                playing = guessKiller();
            }
            else if (keyPress.equals("q"))
            {
                playing = quitGame();
                
                if (playing == true)
                {
                    SYSTEMINPUT.nextLine();
                }
            }
        }
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
        else if (conversationLevel == 3 && butler.getNPCLine().isUnlocked())
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
    // </editor-fold>
}