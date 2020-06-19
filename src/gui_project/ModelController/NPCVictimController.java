package gui_project.ModelController;


import gui_project.View.NPCVictimView;

public class NPCVictimController extends NPCController
{
    private final NPCVictim victim;
    private final NPCVictimView view;
    
    public NPCVictimController(MainController mainCtrl, NPCVictim victim) 
    {
        super(mainCtrl,victim);
        
        this.victim = victim;
        this.view = new NPCVictimView(mainCtrl, this);
    }

    public NPCVictim getVictim() 
    {
        return victim;
    }
    
    public NPCVictimView getVictimView() 
    {
        return view;
    }
}
