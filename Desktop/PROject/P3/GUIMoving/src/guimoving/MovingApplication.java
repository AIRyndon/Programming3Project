package guimoving;

import javax.swing.JFrame;

public class MovingApplication 
{
    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("Moving");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.add(new Ground());
        frame.setVisible(true);
    }
}
