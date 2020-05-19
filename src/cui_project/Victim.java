package cui_project;

public class Victim
{
    // <editor-fold defaultstate="collapsed" desc="Victim Attributes">
    private String name;
    private String role;
    private String location;
    private char gender;
    private int age;
    private String injuries = "There was a serious wound on his left chest and no more injuries.";
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Victim(String name, String role, int age, char gender, String location)
    {
        this.name = name;
        this.role = role;
        this.age = age;
        this.gender = gender;
        this.location = location;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getting Method">
    public String getName()
    {
        return name;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="ToString Method">
    @Override
    public String toString()
    {
        String output = "";
        
        output += "Victim infomation\n";
        output += "Name: " + this.getName() + "\n";
        output += "Position: " + this.role + "\n";
        output += "Gender: " + (this.gender == 'M' ? "Male\n" : "Female\n");
        output += "Age: " + this.age + "\n";
        output += "Scene: " + this.location + "\n";
        output += this.injuries;
        
        return output;
    }
    // </editor-fold>
}