package programming3project;

public class Password
{
    // <editor-fold defaultstate="collapsed" desc="Password Attributes">
    private boolean unlock;
    private String password;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Password()
    {
        this.setUnlock(false);
        
        initialisePassword();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public boolean isUnlock()
    {
        return unlock;
    }
    
    public void setUnlock(boolean unlock)
    {
        this.unlock = unlock;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public methods">
    public void promtPassword()
    {
        //todo - delete the below line -> Hide password
        System.out.println(this.password);
        
        System.out.print("Press y to enter password, any character to leave: ");
        boolean enterPass = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());
        
        if (enterPass)
        {
            System.out.print("> ");
            String userInput = Game.SYSTEMINPUT.nextLine();
            
            do
            {
                if (userInput.equals(this.getPassword()))
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
            while (!userInput.equalsIgnoreCase("q"));
        }
    }
    
    @Override
    public String toString()
    {
        return this.getPassword();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Private Method">
    private void initialisePassword()
    {
        //Choose four random numbers between 0 to 9
        int first = Game.RANDOM.nextInt(10);
        int second = Game.RANDOM.nextInt(10);
        int third = Game.RANDOM.nextInt(10);
        int fourth = Game.RANDOM.nextInt(10);
        
        String pass = Integer.toString(first) + second +
                third + fourth;
        
        this.setPassword(pass);
    }
    // </editor-fold>
}
