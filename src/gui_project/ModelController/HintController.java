package gui_project.ModelController;

import gui_database.HintDatabase;
import gui_project.View.HintView;
import java.awt.Graphics2D;

public class HintController extends ItemBlockController
{
    public static int HINTCOUNT = 1;
    private final static HintDatabase HINTDATABASE = new HintDatabase();
    private final Hint hint;
    private final HintView view;
    private final DetectiveController detectiveCtrl;

    public HintController(DetectiveController detectiveCtrl, Hint hint)
    {
        super(hint);
        this.hint = hint;
        this.detectiveCtrl = detectiveCtrl;
        view = new HintView(hint);
        
        setupHintNameAndDescription();
    }

    /**
     * pickup a hint
     */
    public void pickup()
    {
        hint.setPickedUp();
    }

    /**
     * reveal a hint
     */
    public void reveal()
    {
        if (!hint.isPickedUp())
        {
            hint.setVisible(true);
        }
    }

    /**
     * clear the game message area
     */
    public void clearMessageArea()
    {
        hint.setMessage("");
    }

    /**
     *
     * @return true if the detective should collide with the hint
     */
    @Override
    public boolean collisionAction()
    {
        if (!hint.isVisible())
        {
            //a special case for hints because you don't want to bump into
            //them when they aren't visible
            return false;
        }

        pickup();
        detectiveCtrl.increasePickedUpHint();
        return true;
    }

    /**
     *
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
    
    public void setupHintNameAndDescription()
    {
        String hintResult = HINTDATABASE.getDataRow(HINTCOUNT);
        HINTCOUNT++;
        
        String[] hintData = hintResult.split("/");
        hint.setNameAndDescription(hintData[0], hintData[1]);        
    }
}
