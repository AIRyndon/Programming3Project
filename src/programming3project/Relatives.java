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
public class Relatives
{
    private String name;
    private String role;
    private int age;
    private String speak;
    //private String why; //Why they want to kill him?

    public Relatives(String name, String role, int age, String speak)
    {
        this.setName(name);
        this.setRole(role);
        this.setAge(age);
        this.setSpeak(speak);
    }
    
    public Relatives()
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

    public String getSpeak() 
    {
        return speak;
    }

    public void setSpeak(String speak)
    {
        this.speak = speak;
    }
    
    @Override
    public String toString()
    {
        return "";
    }
}
