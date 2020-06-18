/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_database.HintDatabase;
import gui_project.View.HintView;
import java.awt.Graphics2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
/**
 * I am not sure if we should put the Database in the Controller
 */
public class HintController extends ItemBlockController
{
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
        
        //todo: delete this line
        hint.setVisible(true);
    }

    public void pickup() throws SQLException
    {
        hint.setPickedUp();
        hint.sendMessage();

        hintDatabase.inputDataRow(hint.getName(), hint.getDescription());
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
        hint.sendMessage();
    }

    public Hint getHint()
    {
        return hint;
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
        
        try 
        {
            pickup();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(HintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        detectiveCtrl.increasePickedUpHint();
        mainCtrl.checkDetectiveHintCount();
        return true;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
}
