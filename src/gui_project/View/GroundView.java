package gui_project.View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GroundView extends JPanel implements ActionListener
{
    public static ButlerView butler;

    public static DogHouse dogHouse;

    public static HintView hint;

    public static Timer mainTimer;

    public static Random random = new Random();

    public static DetectiveView player;

    public GroundView() 
    {
        setFocusable(true);
        
        dogHouse = new DogHouse(500, 22);
        butler = new ButlerView(random.nextInt(500) + 20, random.nextInt(400) + 20);
        player = new DetectiveView(100, 100);
        hint = new HintView(400, 200);
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
        repaint();
    }

    public void draw(Graphics2D graphic2D) 
    {
        graphic2D.drawString("Ground", 275, 30);
        graphic2D.draw(getBound());
    }
}
