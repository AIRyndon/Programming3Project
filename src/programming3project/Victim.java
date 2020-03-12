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
public class Victim
{
    private String name;
    private String role;
    private char gender;
    private int age;
    private String injuries = "There was a serious wound on his left chest and no more injuries.";

    public Victim(String name, String role, int age, char gender)
    {
        this.setName(name);
        this.setRole(role);
        this.setAge(age);
        this.setGender(gender);
    }

    public Victim()
    {

    }

    //Getters and setters
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

        public char getGender() 
    {
        return gender;
    }

    public void setGender(char gender) 
    {
        this.gender = gender;
    }
    
    public String getInjures()
    {
        return injuries;
    }

    public void setInjures(String injures)
    {
        this.injuries = injures;
    }

    @Override
    public String toString()
    {
        String output = "";

        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.getAge() + "\n";
        output += this.getInjures()+ "\n";

        return output;
    }
}
