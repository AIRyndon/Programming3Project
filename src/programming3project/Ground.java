/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

/**
 *
 * @author pc
 */
public class Ground extends Room
{
    public Ground(int width, int height)
    {
        super(width, height);
    }
    
    /**
     * Printing the ground
     */
    @Override
    public void printRoom()  
    {
        //Loops for height
        for (int i = 0; i < this.getHeight(); i++)
        {
            for (int j = 0; j < this.getWidth(); j++)
            {
                if(j == 0 || j == this.getWidth() - 1)
                {
                    System.out.print("|");
                }
                else if(i == 0 || i == this.getHeight() - 1)
                {
                    System.out.print("----");
                }
                else
                {
                    System.out.print("    ");
                }
            }
            
            System.out.println("");
        }
    }
}
