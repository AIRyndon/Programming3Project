package gui_project.View;

import gui_project.ModelController.Hint;
import java.awt.Graphics2D;


public class HintView extends ItemBlockView
{
    private final Hint hint;

    public HintView(Hint hint)
    {
        super(hint);
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
