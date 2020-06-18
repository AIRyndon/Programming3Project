/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
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
    private NPCController butler, maid, assistant, wife, daughter, victim;
    private HintController knife, gloves, cake, vial, picture;
    private KeyPasswordController headOfficeLock, tailOfficeLock, headDogHouse, tailDogHouse;
    private ItemBlockController houseArea, maidRoomWall, wifeRoomWall, butlerRoomWall,
            officeRoomWall, bed;
    private LockedAreaController dogHouseLock, officeLock;
    private GuessKillerController guessKillerCtrl;

    public MainController(Detective detective) throws IOException
    {
        this.detective = detective;
        view = new MainView();

        setUpNPCController();
        setUpHint();
        setUpRoomController();
        setUpItemBlockController();
        setUpLockedAreaController();
        setUpKeyPasswordController();
        addItemBlockToRoom();

        addAllPanels();
        showPanel("Ground");
        view.renderView();
    }

    public void addItemBlockToRoom()
    {
        groundCtrl.addItemBlock(dogHouseLock);
        groundCtrl.addItemBlock(butler);
        groundCtrl.addItemBlock(cake);
        groundCtrl.addItemBlock(houseArea);
        groundCtrl.addItemBlock(tailOfficeLock);

        houseCtrl.addItemBlock(maidRoomWall);
        houseCtrl.addItemBlock(wifeRoomWall);
        houseCtrl.addItemBlock(butlerRoomWall);
        houseCtrl.addItemBlock(officeRoomWall);
        houseCtrl.addItemBlock(vial);
        houseCtrl.addItemBlock(headOfficeLock);

        butlerRoomCtrl.addItemBlock(assistant);
        butlerRoomCtrl.addItemBlock(bed);
        butlerRoomCtrl.addItemBlock(tailDogHouse);

        maidRoomCtrl.addItemBlock(maid);
        maidRoomCtrl.addItemBlock(bed);
        maidRoomCtrl.addItemBlock(gloves);

        wifeRoomCtrl.addItemBlock(wife);
        wifeRoomCtrl.addItemBlock(daughter);
        wifeRoomCtrl.addItemBlock(bed);

        workingRoomCtrl.addItemBlock(victim);
        workingRoomCtrl.addItemBlock(officeLock);
        workingRoomCtrl.addItemBlock(knife);
        workingRoomCtrl.addItemBlock(picture);
        workingRoomCtrl.addItemBlock(headDogHouse);
    }

    public void addAllPanels()
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
    }

    public void assignHintToNPC()
    {
        butler.setOwnedHint(gloves);
        assistant.setOwnedHint(vial);
        maid.setOwnedHint(cake);
        wife.setOwnedHint(picture);
        daughter.setOwnedHint(knife);
    }

    public void setUpHint()
    {
        knife = new HintController(this, detectiveCtrl,
                new Hint("Knife", "The blade is bloody. . .", 500, 260, 10, 10));
        gloves = new HintController(this, detectiveCtrl,
                new Hint("Gloves", "A worn-out pair of gloves, there is a name on it - "
                        + "\nit is illegible, you only recognize the letters ATO", 250, 250, 10, 10));
        cake = new HintController(this, detectiveCtrl,
                new Hint("Cheescake", "An innocuous-looking cheesecake", 750, 30, 10, 10));
        picture = new HintController(this, detectiveCtrl,
                new Hint("Old Picture", "A picture of a young girl - "
                        + "\nthe girl has a resemblance with the maid", 30, 30, 10, 10));
        vial = new HintController(this, detectiveCtrl,
                new Hint("Alprazolam", "A powerful sedative -"
                        + "\ncan have side-effects when taken regularly", 650, 30, 10, 10));

        assignHintToNPC();
    }

    public void setUpItemBlockController()
    {
        houseArea = new ItemBlockController(new ItemBlock(276, 83, 200, 200));
        maidRoomWall = new ItemBlockController(new ItemBlock(10, 15, 200, 110));
        wifeRoomWall = new ItemBlockController(new ItemBlock(10, 125, 200, 110));
        butlerRoomWall = new ItemBlockController(new ItemBlock(10, 235, 200, 110));
        officeRoomWall = new ItemBlockController(new ItemBlock(10, 345, 450, 140));
        bed = new ItemBlockController(new ItemBlock(10, 15, 100, 100));
    }

    public void setUpLockedAreaController()
    {
        dogHouseLock = new LockedAreaController(this, new LockedArea(630, 15, 150, 100, "DogHouseLock"));
        officeLock = new LockedAreaController(this, new LockedArea(10, 15, 200, 469, "OfficeLock"));
        guessKillerCtrl = new GuessKillerController(this);
    }

    public void setUpNPCController()
    {
        detectiveCtrl = new DetectiveController(detective);
        butler = new NPCController(this, new NPC("Butler", "B", 100, 100, 20, 20));
        maid = new NPCController(this, new NPC("Maid", "M", 200, 200, 20, 20));
        assistant = new NPCController(this, new NPC("Assistant", "A", 240, 240, 20, 20));
        wife = new NPCController(this, new NPC("Wife", "W", 230, 230, 20, 20));
        daughter = new NPCController(this, new NPC("Daughter", "D", 150, 150, 20, 20));
        victim = new NPCController(this, new NPC("Victim", "V", 300, 300, 20, 20));
    }

    public void setUpKeyPasswordController() throws IOException
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

    public void setUpRoomController()
    {
        groundCtrl = new RoomGroundController(this, detectiveCtrl, butler);
        houseCtrl = new RoomHouseController(this, detectiveCtrl, wife);
        maidRoomCtrl = new RoomMaidController(this, detectiveCtrl, maid);
        butlerRoomCtrl = new RoomButlerController(this, detectiveCtrl, assistant);
        wifeRoomCtrl = new RoomWifeController(this, detectiveCtrl, wife, daughter);
        workingRoomCtrl = new RoomWorkingController(this, detectiveCtrl, victim);
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
            Thread.sleep(1000);
            view.showPanel(guessKillerCtrl.getView().getName());
        } catch (InterruptedException ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
