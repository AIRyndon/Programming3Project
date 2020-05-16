package guimoving;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ground extends JPanel implements ActionListener
{

    public static Butler butler;

    public static DogHouse dogHouse;

    public static Hint hint;

    public static Timer mainTimer;

    public static Random random = new Random();

    public static Detective player;

    public Ground() 
    {
        setFocusable(true);
        
        dogHouse = new DogHouse(500, 22);
        butler = new Butler(random.nextInt(500) + 20, random.nextInt(400) + 20);
        player = new Detective(100, 100);
        hint = new Hint(400, 200);
        addKeyListener(new KeyAdapt(player));
        
        mainTimer = new Timer(10, this);
        mainTimer.start();
    }

    public static Rectangle getBound() 
    {
        return new Rectangle(10, 10, 570, 450);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D graphic2D = (Graphics2D) g;
        draw(graphic2D);
        
        player.draw(graphic2D);
        butler.draw(graphic2D);
        dogHouse.draw(graphic2D);
        hint.draw(graphic2D);
    }

    public void actionPerformed(ActionEvent e)
    {
        player.update();
        repaint();
    }

    public void draw(Graphics2D graphic2D) 
    {
        graphic2D.drawString("Ground", 275, 30);
        graphic2D.draw(getBound());
    }
}
