package gui_project.View;

import gui_project.ModelController.BaseModel;
import gui_project.ModelController.Detective;
import gui_project.ModelController.DetectiveController;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import gui_project.ModelController.BaseObserver;

public class DetectiveView implements BaseObserver
{   
    /*
     *   Hi why did you declare Dective class here 
     *   although DetectiveController already contains it?
     
        Cuz we are using it directly here. too lazy to call get<Model> from
        the Controller :D. Any method is fine cuz the reference all point
        to the same object in the end
     */
    
    private Detective detective;
    private DetectiveController controller;

    public DetectiveView(Detective detective, DetectiveController controller)
    {
        this.controller = controller;
        this.detective = detective;
        detective.registerObserver((BaseObserver) this);
    }

    @Override
    public void update(BaseModel model)
    {
        controller.setLocationX(detective.getLocationX() + detective.getVelX());
        controller.setLocationY(detective.getLocationY() + detective.getVelY());
    }

    public void draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(getPlayerImage(), detective.getLocationX(), detective.getLocationY(), null);
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
