package gui_project.ModelController;

import gui_database.HintDatabase;
import gui_project.View.HintView;
import java.awt.Graphics2D;

public class HintController extends ItemBlockController
{
    public static int HINTCOUNT = 1;
    private static HintDatabase hintDatabase = new HintDatabase();
    private final Hint hint;
    private final HintView view;
    private final MainController mainCtrl;
    private final DetectiveController detectiveCtrl;

    public HintController(MainController mainCtrl,
            DetectiveController detectiveCtrl, Hint hint)
    {
        super(hint);
        this.hint = hint;
        this.mainCtrl = mainCtrl;
        this.detectiveCtrl = detectiveCtrl;
        view = new HintView(hint, this);
        
        setupHintNameAndDescription();
    }

    public void pickup()
    {
        hint.setPickedUp();
    }

    public void reveal()
    {
        if (!hint.isPickedUp())
        {
            hint.setVisible(true);
        }
    }

    public void clearMessageArea()
    {
        hint.setMessage("");
    }

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
        mainCtrl.checkDetectiveHintCount();

        return true;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
    
    public void setupHintNameAndDescription()
    {
        String hintResult = hintDatabase.getDataRow(HINTCOUNT);
        HINTCOUNT++;
        
        String[] hintData = hintResult.split("/");
        hint.setNameAndDescription(hintData[0], hintData[1]);        
    }
}
