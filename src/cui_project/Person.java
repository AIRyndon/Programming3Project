package cui_project;

public abstract class Person
{
    // <editor-fold defaultstate="collapsed" desc="Person Attributes">
    private String name;
    private char gender;
    private int age;
    // </editor-fold>
    
    // <editor-fold desc="Constructor">
    public Person(String name, char gender, int age)
    {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getter methods">
    public String getName()
    {
        return name;
    }
    
    public char getGender()
    {
        return gender;
    }
    
    public int getAge()
    {
        return age;
    }
    // </editor-fold>
}
