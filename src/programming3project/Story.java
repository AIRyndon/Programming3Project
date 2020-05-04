package programming3project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Story 
{
    // <editor-fold defaultstate="collapsed" desc="Story Attributes">
    private LinkedList<String> story;
    private String ending;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public Story() throws IOException
    {
        story = getStory();
        ending = getEnding();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Public Methods">
    public String getEnding() throws IOException
    {
        String ending = "";
        boolean identifyEnding = false;
        
        BufferedReader br = new BufferedReader(new FileReader(Game.getCompletePath("Story.txt")));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            if(identifyEnding)
            {
                ending += line + '\n';
            }
            
            if(line.equals("Ending"))
            {
                identifyEnding = true;
            }
        }
        
        return ending;
    }
    
    public LinkedList<String> getStory() throws IOException
    {
        LinkedList<String> introStory = new LinkedList<String>();
        
        //Read text from file
        BufferedReader br = new BufferedReader(new FileReader(Game.getCompletePath("Story.txt")));
        String line = "";
        while ((line = br.readLine()) != null)
        {
            introStory.add(line);
            
            if(line.equals("Ending"))
            {
                break;
            }
        }
        
        return introStory;
    }
    // </editor-fold>
}
