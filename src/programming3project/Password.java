/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Password 
{
    private Random rand = new Random();
    private Scanner scan = new Scanner(System.in);
    
    private boolean unlock;
    private String password;
    
    public Password()
    {
        this.setUnlock(false);
        
        initialisePassword();
        
    }
    
    void initialisePassword()
    {
        //Choose four random numbers between 0 to 9
        int first = rand.nextInt(10);
        int second = rand.nextInt(10);
        int third = rand.nextInt(10);
        int fourth = rand.nextInt(10);
        
        String pass = Integer.toString(first) + Integer.toString(second) + 
                Integer.toString(third) + Integer.toString(fourth);
        
        this.setPassword(pass);
    }

    public void promtPassword()
    {        
        System.out.println("Press y to enter password, any character to leave");
        System.out.println(this.password);
        boolean enterPass = "y".equalsIgnoreCase(scan.nextLine());

        while(enterPass)
        {
            System.out.print("> ");
            String userInput = scan.nextLine();

            if(userInput.equalsIgnoreCase(this.getPassword()))
            {
                this.setUnlock(true);
                enterPass = false;
            }
            else
            {
                System.out.println("Hahahaha wrong! Try again or press q to quit!");
            }

            if(userInput.equalsIgnoreCase("q"))
            {
                enterPass = false;
            }
        }
    }
    
    public void unlock()
    {
        this.setUnlock(true);
    }

    /**
     * @return the unlock
     */
    public boolean isUnlock() 
    {
        return unlock;
    }

    /**
     * @param unlock the unlock to set
     */
    public void setUnlock(boolean unlock) 
    {
        this.unlock = unlock;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString()
    {
        return this.getPassword();
    }
}
