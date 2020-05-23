package gui_project.View;

import gui_project.ModelController.BaseController;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView
{
    private final BaseController controller;
    private final JFrame frame;
    private final JPanel mainPanel;
    private final RoomView roomOne;
    private final RoomTwoView roomTwo;

    /**
     * Creates new form ViewGame
     */
    public MainView(BaseController controller)
    {
        this.controller = controller;
        frame = new JFrame("RPG Game");
        roomOne = new RoomView();
        roomTwo = new RoomTwoView();
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.add(roomOne,roomOne.getName());
        mainPanel.add(roomTwo,roomTwo.getName());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void renderView()
    {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void addPanel(JPanel panel)
    {
        mainPanel.add(panel, panel.getName());
    }
}
