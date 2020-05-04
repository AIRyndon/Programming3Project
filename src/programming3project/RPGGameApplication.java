package programming3project;

import java.io.IOException;

public class RPGGameApplication
{
    public static void main(String[] args) throws IOException
    {     
        System.out.println("Welcome to the game\n");
        
        Game game = new Game();       
        game.startGame();
        
        if(game.playAgain())
        {
            game = new Game();
            System.out.println("\nWelcome to the game");
            game.startGame();
        }
        
        System.out.println("Thank you for playing!\n");
    }
}