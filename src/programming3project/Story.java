/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class Story 
{
    private LinkedList<String> story;
    
    public Story() throws IOException
    {
        story = getStory();
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
        }
        
        return introStory;
    }
}
