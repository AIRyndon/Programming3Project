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
    private int age;
    private String injures; //not yet
    private String body;    //not yet   
    
    public Victim(String name, String role, int age)
    {
        this.setName(name);
        this.setRole(role);
        this.setAge(age);
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

    public String getInjures() {
        return injures;
    }

    public void setInjures(String injures) {
        this.injures = injures;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    @Override
    public String toString()
    {
        return "";
    }
}

