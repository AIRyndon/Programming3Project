package gui_project.ModelController;

public class NPCVictim extends NPC
{
    private final String injuries = "There was a serious wound on his left chest and no more injuries.";
    private final String scene;
    private final String name;
    private final Integer age;
    
    
    public NPCVictim(String role, String symbol, int locationX, int locationY, 
            int width, int height, String scene, String name, int age) 
    {
        super(role, symbol, locationX, locationY, width, height);
        
        this.scene = scene;
        this.name = name;
        this.age = age;
    }
    
    //Getters
    public String getInjuries()
    {
        return injuries;
    }
    
    public String getScene() 
    {
        return scene;
    }
    
    public String getName()
    {
        return name;
    }
    
    public Integer getAge() 
    {
        return age;
    }
}
