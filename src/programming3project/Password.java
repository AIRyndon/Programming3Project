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
public class Password 
{    
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
        int first = Game.RANDOM.nextInt(10);
        int second = Game.RANDOM.nextInt(10);
        int third = Game.RANDOM.nextInt(10);
        int fourth = Game.RANDOM.nextInt(10);
        
        String pass = Integer.toString(first) + Integer.toString(second) + 
                Integer.toString(third) + Integer.toString(fourth);
        
        this.setPassword(pass);
    }

    public void promtPassword()
    {        
        Game.SYSTEMINPUT.nextLine();
        System.out.println(this.password);
        System.out.print("Press y to enter password, any character to leave: ");
        boolean enterPass = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (enterPass)
        {
            System.out.print("> ");
            String userInput = Game.SYSTEMINPUT.nextLine();
            
            do
            {
                if(userInput.equalsIgnoreCase(this.getPassword()))
                {
                    this.setUnlock(true);
                    userInput = "q";
                }
                else
                {
                    System.out.println("Hahahaha wrong! Try again or press q to quit!");
                    System.out.print("> ");
                    userInput = Game.SYSTEMINPUT.nextLine();
                }
            }
            while(!userInput.equalsIgnoreCase("q"));
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
