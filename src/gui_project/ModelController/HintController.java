/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import gui_project.View.HintView;
import java.awt.Graphics2D;

/**
 *
 * @author Angelo
 */
public class HintController extends ItemBlockController
{
    private final Hint hint;
    private final HintView view;

    public HintController(Hint hint)
    {
        super(hint);
        this.hint = hint;
        view = new HintView(hint, this);
    }

    public void pickup()
    {
        hint.setPickedUp();
        hint.setVisible(false);
        hint.setMessage(hint.getMessage());
        hint.sendMessage();
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
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
}
