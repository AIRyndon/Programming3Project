/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author angel
 */

public abstract class Person {
    
    private final String name;
    private final char gender;
    private final int age;
    
    public Person(String name, char gender, int age){
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }  
}
