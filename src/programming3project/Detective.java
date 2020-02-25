/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author airyn
 */
public class Detective {
    
    private String name;
    private char gender;
    //private int age; 
    private String background;
    public Actions playerActions;
    
    public Detective(String name, char gender)
    {
        this.setName(name);
        this.setBackground("Detective - Cautious, Smart, and Immensely Knowledgable");
    }
    
    public Detective()
    {
        
    }     

    /**
     * @return the name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * @return the gender
     */
    public char getGender() 
    {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender)
    {
        this.gender = gender;
    }

    /**
     * @return the background
     */
    public String getBackground() 
    {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background)
    {
        this.background = background;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        
        s += "Name: " + this.getName() + "\n";
        s += "Gender: " + (this.getGender() == 'M' ? "Male" : "Female");
        s += "Age: UNKNOWN" + "\n";//Do we need to create another attribute for age? => Then ask user
        s += "Job: " + this.getBackground() + "\n\n";
        
        return s;
    }
}
