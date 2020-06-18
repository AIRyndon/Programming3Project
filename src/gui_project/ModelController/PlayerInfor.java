package gui_project.ModelController;

public class PlayerInfor extends BaseModel
{
    private int age;
    private String name;
    private char gender;
    
    public PlayerInfor()
    {
        
    }

    //Getters and setters
    public int getAge() 
    {
        return age;
    }
    
    void setAge(int age) 
    {
        this.age = age;
    }
    
    public String getName() 
    {
        return name;
    }

    void setName(String name) 
    {
        this.name = name;
        notifyObservers();
    }
    
    public char getGender() 
    {
        return gender;
    }
    
    void setGender(char gender)
    {
        //input gender as UPPERCASE
        String upperCaseGender = gender + "";
        upperCaseGender.toUpperCase();
        
        this.gender = upperCaseGender.charAt(0);
    }
}
