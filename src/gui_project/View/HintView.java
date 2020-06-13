/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.View;

import gui_project.ModelController.Hint;
import gui_project.ModelController.HintController;
import java.awt.Graphics2D;

/**
 *
 * @author Angelo
 */
public class HintView extends ItemBlockView
{
    private final Hint hint;

    public HintView(Hint hint, HintController hintCtrl)
    {
        super(hint, hintCtrl);
        this.hint = hint;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        if (hint.isVisible())
        {
            super.draw(g2);
        }
    }
}
