package gui_project.View;

import gui_project.ModelController.BaseObserver;
import gui_project.ModelController.Detective;
import gui_project.ModelController.DetectiveController;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class DetectiveView extends NPCView implements BaseObserver
{
    private DetectiveController controller;
    private Detective detective;

    public DetectiveView(Detective detective, DetectiveController controller)
    {
        super(detective,controller);
        this.controller = controller;
        this.detective = detective;
        detective.registerObserver(this);
    }

    @Override
    public void update()
    {
        controller.setLocationX(detective.getLocationX() + detective.getVelX());
        controller.setLocationY(detective.getLocationY() + detective.getVelY());
        checkCollisions();
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(getPlayerImage(), npc.getLocationX(), npc.getLocationY(), null);
    }   

    public Image getPlayerImage()
    {
        ImageIcon imageIcon = new ImageIcon("./Images/person.png");
        Image image = imageIcon.getImage();
        Image changeImageSize = image.getScaledInstance(50, 50, 4);
        imageIcon = new ImageIcon(changeImageSize);
        image = imageIcon.getImage();
        return image;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (npc.getLocationX() <= 20 || npc.getLocationX() >= 560 || npc.getLocationY() <= 20 || npc.getLocationY() >= 420
                || getBound().intersects(GroundView.dogHouse.getBound()))
        {
            System.out.println("No move");

            switch (key)
            {
                case KeyEvent.VK_W:
                    controller.setVelY(detective.getVelY() + 10);
                    break;
                case KeyEvent.VK_S:
                    controller.setVelY(detective.getVelY() - 10);
                    break;
                case KeyEvent.VK_A:
                    controller.setVelX(detective.getVelX() + 10);
                    break;
                case KeyEvent.VK_D:
                    controller.setVelX(detective.getVelX() - 10);
                    break;
                default:
                    break;
            }
        } else if (key == KeyEvent.VK_W)
        {
            controller.setVelY(-(detective.getSpeed()));
        } else if (key == KeyEvent.VK_S)
        {
            controller.setVelY(detective.getSpeed());
        } else if (key == KeyEvent.VK_A)
        {
            controller.setVelX(-(detective.getSpeed()));
        } else if (key == KeyEvent.VK_D)
        {
            controller.setVelX(detective.getSpeed());
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        switch (key)
        {
            case KeyEvent.VK_W:
                controller.setVelY(0);
                break;
            case KeyEvent.VK_S:
                controller.setVelY(0);
                break;
            case KeyEvent.VK_A:
                controller.setVelX(0);
                break;
            case KeyEvent.VK_D:
                controller.setVelX(0);
                break;
            default:
                break;
        }
    }

    public void checkCollisions()
    {
        if (getBound().intersects(GroundView.butler.getBound()))
        {
            GroundView.butler.npcController.setHasTalked(true);
        } else if (getBound().intersects(GroundView.hint.getBound()))
        {
            GroundView.dogHouse.setLock(true);
        }
    }

    public Rectangle getBound()
    {
        return new Rectangle(npc.getLocationX(), npc.getLocationY(),
                getPlayerImage().getWidth(null), getPlayerImage().getHeight(null));
    }
}
