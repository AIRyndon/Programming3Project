package guimoving;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapt extends KeyAdapter
{
    Detective player;

    public KeyAdapt(Detective player) 
    {
        this.player = player;
    }

    public void keyPressed(KeyEvent e) 
    {
        this.player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) 
    {
        this.player.keyReleased(e);
    }
}
