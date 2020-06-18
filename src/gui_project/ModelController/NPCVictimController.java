package gui_project.ModelController;

//Not sure if it is neccesary?

import gui_project.View.NPCVictimView;

public class NPCVictimController extends NPCController
{
    private NPCVictim victim;
    private NPCVictimView view;
    private MainController mainCtrl;
    
    public NPCVictimController(MainController mainCtrl, NPCVictim victim) 
    {
        super(mainCtrl,victim);
        
        this.mainCtrl = mainCtrl;
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
