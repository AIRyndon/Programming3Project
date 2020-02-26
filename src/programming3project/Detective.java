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
public class Detective extends Person {

    private String background;
    public Actions playerActions;

    public Detective(String name, char gender, int age) {
        super(name, gender, age);      
    }

    /**
     * @return the background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public String toString() {
        String output = "";

        output += "Name: " + this.getName() + "\n";
        output += "Gender: " + (this.getGender() == 'M' ? "Male" : "Female");
        output += "Age: " + this.getAge() + "\n";//Do we need to create another attribute for age? => Then ask user
        output += "Job: " + this.getBackground() + "\n\n";

        return output;
    }
}
