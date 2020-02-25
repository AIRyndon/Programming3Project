/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author airyn
 */
public class Programming3Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String workingDir = System.getProperty("user.dir");
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        new File(workingDir + "/FileDB/").mkdir();
        
        File file = new File(workingDir + "/FileDB/" + "game-state.txt");
        
        try{
            file.createNewFile();
        }catch (IOException e){
        
        }
        
        Detective Taylor = new Detective();
        
        System.out.println("Outside the house...");
        if (Taylor.playerActions.MOVE) {
            
        }
        
              
    }
    
}
