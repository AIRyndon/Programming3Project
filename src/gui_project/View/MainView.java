package gui_project.View;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView
{
    private final JFrame frame;
    private final JPanel mainPanel;

    public MainView()
    {
        frame = new JFrame("RPG Game");
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setPreferredSize(new Dimension(800, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void renderView()
    {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void addPanel(JPanel panel, String name)
    {
        mainPanel.add(panel, name);
    }

    public void showPanel(String panelName)
    {
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }
    
    /**
    *  this is here so we can dispose the frame when we want to restart
    * @return the main JFrame
    */
    public JFrame getWindow(){
        return frame;
    }
}
