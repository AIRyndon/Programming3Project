package gui_project.View;

import gui_project.ModelController.BaseObserver;
import gui_project.ModelController.Detective;
import gui_project.ModelController.DetectiveController;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class DetectiveView implements BaseObserver
{   
    /*
     *   Hi why did you declare Dective class here 
     *   although DetectiveController already contains it?
     */
    
    private Detective detective;
    private DetectiveController controller;

    public DetectiveView(Detective detective, DetectiveController controller)
    {
        this.controller = controller;
        this.detective = detective;
        detective.registerObserver((BaseObserver) this);
    }

    //TODO - move it to DetectiveController
    @Override
    public void update()
    {
        /*I did this here to show the Observer Pattern - we should actually
        set this in DetectiveController and avoid this roundabout :D
        */
        controller.setLocationX(detective.getLocationX() + detective.getVelX());
        controller.setLocationY(detective.getLocationY() + detective.getVelY());
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(getPlayerImage(), detective.getLocationX(), detective.getLocationY(), null);
        //graphics2D.draw(getBound());
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

    public Rectangle getBound()
    {
        return new Rectangle(detective.getLocationX() + 10, detective.getLocationY() + 15,
                getPlayerImage().getWidth(null) - 20, getPlayerImage().getHeight(null) - 20);
    }
}
