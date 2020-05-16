package guimoving;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Detective extends NPC 
{
    int velX = 0;
    int velY = 0;
    int speed = 5;

    public Detective(int locationX, int locationY) 
    {
        super(locationX, locationY);
    }

    public void update() 
    {
        this.locationY += this.velY;
        this.locationX += this.velX;
        checkCollisions();
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(getPlayerImage(), this.locationX, this.locationY, null);
    }

    public Image getPlayerImage() {
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

        if (this.locationX <= 20 || this.locationX >= 560 || this.locationY <= 20 || this.locationY >= 420
                || getBound().intersects(Ground.dogHouse.getBound())) 
        {
            System.out.println("No move");
            
            if (key == KeyEvent.VK_W) 
            {
                this.velY += 10;
            } 
            else if (key == KeyEvent.VK_S) 
            {
                this.velY -= 10;
            } 
            else if (key == KeyEvent.VK_A)
            {
                this.velX += 10;
            } 
            else if (key == KeyEvent.VK_D)
            {
                this.velX -= 10;
            }
        } 
        else if (key == KeyEvent.VK_W) 
        {
            this.velY = -this.speed;
        } 
        else if (key == KeyEvent.VK_S)
        {
            this.velY = this.speed;
        } 
        else if (key == KeyEvent.VK_A) 
        {
            this.velX = -this.speed;
        } 
        else if (key == KeyEvent.VK_D) 
        {
            this.velX = this.speed;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)
        {
            this.velY = 0;
        } 
        else if (key == KeyEvent.VK_S)
        {
            this.velY = 0;
        } 
        else if (key == KeyEvent.VK_A) 
        {
            this.velX = 0;
        } 
        else if (key == KeyEvent.VK_D) 
        {
            this.velX = 0;
        }
    }

    public void checkCollisions() 
    {
        if (getBound().intersects(Ground.butler.getBound()))
        {
            Butler.isTalk = true;
        } 
        else if (getBound().intersects(Ground.hint.getBound())) 
        {
            DogHouse.isLock = true;
        }
    }

    public Rectangle getBound() 
    {
        return new Rectangle(this.locationX, this.locationY,
                getPlayerImage().getWidth(null), getPlayerImage().getHeight(null));
    }
}
