package gui_project.ModelController;

import java.awt.Rectangle;
import java.util.Random;

public class LockedArea extends ItemBlock
{
    public static Random RANDOM = new Random();
    private String password;
    private boolean unlocked = false;
    private String name;
    
    public LockedArea(int locationX, int locationY, int width, int height, String name) 
    {
        super(locationX, locationY, width, height);
        this.name = name;
        initialisePassword();
    }
    
    private void initialisePassword()
    {
        //Choose four random numbers between 0 to 9
        int first = RANDOM.nextInt(10);
        int second = RANDOM.nextInt(10);
        int third = RANDOM.nextInt(10);
        int fourth = RANDOM.nextInt(10);

        setPassword(Integer.toString(first) + second + third + fourth);
        System.out.println(getPassword());
    }

    public String getPassword() 
    {
        return password;
    }
    
    void setPassword(String password) 
    {
        this.password = password;
    }
    
    public boolean isUnlocked() 
    {
        return unlocked;
    } 
    
    void setUnLocked(boolean isLocked)
    {
        this.unlocked = isLocked;
        notifyObservers();
    }
    
    public String getName() 
    {
        return name;
    }
}
