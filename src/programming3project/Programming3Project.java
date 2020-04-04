/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.IOException;

/**
 *
 * @author airyn
 */
public class Programming3Project
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {     
        System.out.println("Welcome to the game\n");
        
        Game game = new Game();       
        game.startGame();
        
        while(game.playAgain())
        {
            game = new Game();
            System.out.println("\nWelcome to the game");
            game.startGame();
        }
        
        System.out.println("Thank you for playing!\n");
    }
}
