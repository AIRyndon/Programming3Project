package gui_project.View;

import gui_project.ModelController.KeyPassword;
import java.awt.Graphics2D;

public class KeyPasswordView extends ItemBlockView
{
    private final KeyPassword keyPassword;
    private final String symbol = "$$";

    public KeyPasswordView(KeyPassword keyPassword)
    {
        super(keyPassword);
        this.keyPassword = keyPassword;
    }

    @Override
    public void draw(Graphics2D graphics2D) 
    {
        if(!keyPassword.isPickedUp())
        {
            graphics2D.drawString(symbol, keyPassword.getLocationX(), keyPassword.getLocationY());           
        }
    }
}
