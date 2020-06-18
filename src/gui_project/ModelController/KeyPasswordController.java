package gui_project.ModelController;

import gui_project.View.KeyPasswordQuestionPanel;
import gui_project.View.KeyPasswordView;
import java.awt.Graphics2D;

public class KeyPasswordController extends ItemBlockController
{

    private final KeyPasswordView view;
    private KeyPassword keyPassword;
    private KeyPasswordQuestionPanel questionPanel;

    public KeyPasswordController(MainController mainCtrl, KeyPassword keyPassword)
    {
        super(keyPassword);
        this.keyPassword = keyPassword;
        view = new KeyPasswordView(keyPassword, this);
        questionPanel = new KeyPasswordQuestionPanel(mainCtrl, this);
    }

    public boolean checkPassword(String userAnswer)
    {
        if (userAnswer.equalsIgnoreCase(keyPassword.getAnswer()))
        {
            keyPassword.updateCorrect();
        }

        boolean isCorrect = keyPassword.isCorrect();

        return isCorrect;
    }

    public void clearMessageArea()
    {
        keyPassword.clearMessage();
        keyPassword.sendMessage();
    }

    //Getters and setters
    public KeyPasswordQuestionPanel getQuestionPanel()
    {
        return questionPanel;
    }

    public KeyPasswordView getView()
    {
        return view;
    }

    public KeyPassword getKeyPassword()
    {
        return keyPassword;
    }

    public String getMessage()
    {
        return keyPassword.getMessage();
    }

    public void pickup()
    {
        keyPassword.hasPickup();
        keyPassword.updateMessage();
    }

    @Override
    public boolean collisionAction()
    {
        if (keyPassword.isCorrect())
        {
            //If user's answer is correct, allow player to get the key.
            pickup();
            return false;
        }
        return true;
    }

    @Override
    public void draw(Graphics2D g2)
    {
        view.draw(g2);
    }
}
