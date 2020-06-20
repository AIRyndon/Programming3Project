package gui_project.ModelController;

import gui_project.View.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController
{

    private final MainView view;
    private final Detective detective;
    private DetectiveController detectiveCtrl;
    private RoomGroundController groundCtrl;
    private RoomHouseController houseCtrl;
    private RoomMaidController maidRoomCtrl;
    private RoomButlerController butlerRoomCtrl;
    private RoomWifeController wifeRoomCtrl;
    private RoomWorkingController workingRoomCtrl;
    private NPCController butler, maid, assistant, wife, daughter;
    private NPCVictimController victim;
    private HintController knife, gloves, cake, vial, picture;
    private KeyPasswordController headOfficeLock, tailOfficeLock, headDogHouse, tailDogHouse;
    private ItemBlockController houseArea, maidRoomWall, wifeRoomWall, butlerRoomWall,
            workingRoomWall, bed;
    private LockedAreaController dogHouseLock, officeLock;
    private GuessKillerController guessKillerCtrl;
    private PlayerInfoController playerInforCtrl;
    private WelcomePanel welcomePanel;

    public MainController(Detective detective) throws SQLException
    {
        this.detective = detective;
        view = new MainView();

        setUpNPCController();
        setUpLockedAreaController();
        setUpKeyPasswordController();
        setUpHint();
        setUpItemBlockController();
        setUpPlayerInforController();
        addItemBlockToRoom();
        welcomePanel = new WelcomePanel(this);

        addAllPanels();
        showPanel("Welcome");
        view.renderView();
    }

    private void addItemBlockToRoom()
    {
        //add item blocks to ground area
        ArrayList<ItemBlockController> groundItemBlocks = new ArrayList<>();       
        groundItemBlocks.add(butler);
        groundItemBlocks.add(cake);
        groundItemBlocks.add(houseArea);
        groundItemBlocks.add(tailOfficeLock);
        groundItemBlocks.add(dogHouseLock);
             
        groundCtrl = new RoomGroundController(this, detectiveCtrl, 
                    tailOfficeLock, dogHouseLock,groundItemBlocks);
              
        //add item blocks to house area
        ArrayList<ItemBlockController> houseItemBlocks = new ArrayList<>();
        houseItemBlocks.add(workingRoomWall);
        houseItemBlocks.add(maidRoomWall);
        houseItemBlocks.add(wifeRoomWall);
        houseItemBlocks.add(butlerRoomWall);
        houseItemBlocks.add(vial);
        houseItemBlocks.add(headOfficeLock);
        
        houseCtrl = new RoomHouseController(this, detectiveCtrl, 
                    headOfficeLock,houseItemBlocks);

        //add item blocks to butler's room
        ArrayList<ItemBlockController> butlerRoomItemBlocks = new ArrayList<>();
        butlerRoomItemBlocks.add(assistant);
        butlerRoomItemBlocks.add(bed);
        butlerRoomItemBlocks.add(tailDogHouse);
        
        butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, 
                         tailDogHouse,butlerRoomItemBlocks);
        
        //add item blocks to maid's room
        ArrayList<ItemBlockController> maidRoomItemBlocks = new ArrayList<>();
        maidRoomItemBlocks.add(maid);
        maidRoomItemBlocks.add(bed);
        maidRoomItemBlocks.add(gloves);
        
        maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maidRoomItemBlocks);
        
        //add item blocks to wife's room
        ArrayList<ItemBlockController> wifeRoomItemBlocks = new ArrayList<>();
        wifeRoomItemBlocks.add(wife);
        wifeRoomItemBlocks.add(daughter);
        wifeRoomItemBlocks.add(bed);
        
        wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl,wifeRoomItemBlocks);
        
        //add item blocks to working room
        ArrayList<ItemBlockController> workRoomItemBlocks = new ArrayList<>();
        workRoomItemBlocks.add(victim);
        workRoomItemBlocks.add(headDogHouse);
        workRoomItemBlocks.add(knife);
        workRoomItemBlocks.add(picture);
        workRoomItemBlocks.add(officeLock);
        
        workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, 
                          headDogHouse, officeLock,workRoomItemBlocks);
    }

    private void addAllPanels()
    {
        view.addPanel(houseCtrl.getView(), houseCtrl.getView().getName());
        view.addPanel(maidRoomCtrl.getView(), maidRoomCtrl.getView().getName());
        view.addPanel(butlerRoomCtrl.getView(), butlerRoomCtrl.getView().getName());
        view.addPanel(wifeRoomCtrl.getView(), wifeRoomCtrl.getView().getName());
        view.addPanel(workingRoomCtrl.getView(), workingRoomCtrl.getView().getName());
        view.addPanel(groundCtrl.getView(), groundCtrl.getView().getName());
        view.addPanel(dogHouseLock.getPasswordInputPanel(), dogHouseLock.getLockedArea().getName());
        view.addPanel(officeLock.getPasswordInputPanel(), officeLock.getLockedArea().getName());
        view.addPanel(headDogHouse.getQuestionPanel(), headDogHouse.getKeyPassword().getName());
        view.addPanel(tailDogHouse.getQuestionPanel(), tailDogHouse.getKeyPassword().getName());
        view.addPanel(headOfficeLock.getQuestionPanel(), headOfficeLock.getKeyPassword().getName());
        view.addPanel(tailOfficeLock.getQuestionPanel(), tailOfficeLock.getKeyPassword().getName());
        view.addPanel(guessKillerCtrl.getView(), guessKillerCtrl.getView().getName());
        view.addPanel(playerInforCtrl.getView(), playerInforCtrl.getView().getName());
        view.addPanel(welcomePanel, welcomePanel.getName());
        view.addPanel(playerInforCtrl.getStoryPanel(), playerInforCtrl.getStoryPanel().getName());
        view.addPanel(victim.getVictimView(), victim.getVictimView().getName());
    }

    private void assignHintToNPC()
    {
        butler.setOwnedHint(gloves);
        assistant.setOwnedHint(vial);
        maid.setOwnedHint(cake);
        wife.setOwnedHint(picture);
        daughter.setOwnedHint(knife);
    }

    private void setUpHint()
    {
        vial = new HintController(detectiveCtrl, new Hint(650, 30, 10, 10));
        cake = new HintController(detectiveCtrl, new Hint(750, 30, 10, 10));
        gloves = new HintController(detectiveCtrl, new Hint(250, 250, 10, 10));
        knife = new HintController(detectiveCtrl, new Hint(500, 260, 10, 10));
        picture = new HintController(detectiveCtrl, new Hint(30, 30, 10, 10));

        assignHintToNPC();
    }

    private void setUpItemBlockController()
    {
        houseArea = new ItemBlockController(new ItemBlock(276, 83, 200, 200));
        maidRoomWall = new ItemBlockController(new ItemBlock(10, 15, 200, 110));
        wifeRoomWall = new ItemBlockController(new ItemBlock(10, 125, 200, 110));
        butlerRoomWall = new ItemBlockController(new ItemBlock(10, 235, 200, 110));
        workingRoomWall = new ItemBlockController(new ItemBlock(10, 345, 450, 140));
        bed = new ItemBlockController(new ItemBlock(10, 15, 100, 100));
    }

    private void setUpLockedAreaController()
    {
        dogHouseLock = new LockedAreaController(this, new LockedArea(630, 15, 150, 100, "DogHouseLock"));
        officeLock = new LockedAreaController(this, new LockedArea(10, 15, 200, 469, "OfficeLock"));
        guessKillerCtrl = new GuessKillerController(this);
    }

    private void setUpNPCController()
    {
        detectiveCtrl = new DetectiveController(detective);
        butler = new NPCController(this, new NPC("Butler", "B", 100, 100, 20, 20));
        maid = new NPCController(this, new NPC("Maid", "M", 200, 200, 20, 20));
        assistant = new NPCController(this, new NPC("Assistant", "A", 240, 240, 20, 20));
        wife = new NPCController(this, new NPC("Wife", "W", 230, 230, 20, 20));
        daughter = new NPCController(this, new NPC("Daughter", "D", 150, 150, 20, 20));
        victim = new NPCVictimController(this, new NPCVictim("Victim", "V", 300, 300, 20, 20,
                "his working room", "Bosh", 53));
    }

    private void setUpKeyPasswordController()
    {
        //House
        headOfficeLock = new KeyPasswordController(this, new KeyPassword(700, 150, 20, 20,
                officeLock.getLockedArea(), "!", "HeadOffice", KeyPasswordType.KEYHEAD));

        //Ground
        tailOfficeLock = new KeyPasswordController(this, new KeyPassword(650, 300, 20, 20,
                officeLock.getLockedArea(), "@", "TailOffice", KeyPasswordType.KEYTAIL));

        //WorkingRoom
        headDogHouse = new KeyPasswordController(this, new KeyPassword(30, 65, 20, 20,
                dogHouseLock.getLockedArea(), "#", "HeadDogHouse", KeyPasswordType.KEYHEAD));

        //Butler
        tailDogHouse = new KeyPasswordController(this, new KeyPassword(150, 50, 20, 20,
                dogHouseLock.getLockedArea(), "%", "TailDogHouse", KeyPasswordType.KEYTAIL));
    }

    private void setUpPlayerInforController()
    {
        playerInforCtrl = new PlayerInfoController(this, new PlayerInfo());
    }

    public void updateConversationLevel()
    {
        switch (detective.getConversationLevel())
        {
            //talk with every NPC to unlock the assistant's 2nd line
            case 1:
                if (assistant.getNPC().isDiscovered()
                        && butler.getNPC().isDiscovered()
                        && wife.getNPC().isDiscovered()
                        && daughter.getNPC().isDiscovered()
                        && maid.getNPC().isDiscovered())
                {
                    //reset the NPC's so the detective needs to talk 
                    //with them again
                    assistant.resetIsDiscovered();
                    wife.resetIsDiscovered();

                    assistant.unlockLines();
                    detective.setConversationLevel(2);
                }
                break;
            case 2:
                //unlock wife's 2nd conversation 
                //after talking with the assistant again
                if (assistant.getNPC().isDiscovered())
                {
                    wife.unlockLines();
                    if (wife.getNPC().isDiscovered())
                    {
                        butler.resetIsDiscovered();
                        maid.resetIsDiscovered();
                        butler.unlockLines();
                        detective.setConversationLevel(3);
                    }
                }
                break;
            case 3:
                if (butler.getNPC().isDiscovered())
                {
                    maid.unlockLines();
                    if (maid.getNPC().isDiscovered())
                    {
                        daughter.unlockLines();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void checkDetectiveHintCount()
    {
        if (detective.getPickedUpHints() == 5)
        {
            guessKiller();
        }
    }

    public MainView getView()
    {
        return view;
    }

    /**
     * This is what's ultimately called in the other panels to change what panel
     * to show - that is why we pass the MainController to the other controllers
     *
     * @param panelName
     */
    public void showPanel(String panelName)
    {
        view.showPanel(panelName);
    }

    private void guessKiller()
    {
        try
        {
            Thread.sleep(2000);
            view.showPanel(guessKillerCtrl.getView().getName());
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
