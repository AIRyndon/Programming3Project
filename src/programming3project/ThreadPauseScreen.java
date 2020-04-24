/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author pc
 */
public class ThreadPauseScreen implements Runnable
{
    private boolean stop = false;
    
    @Override
    public void run() 
    {
        while(!stop)
        {
            System.out.println("Server is running.....");
        }
    }
    
    public void stop()
    {
        stop = true;
    }
    
}
