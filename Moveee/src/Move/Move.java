package Move;

import java.io.Console;

public class Move {

	public static void main(String[] args) {      
	      Console cnsl = null;
	      String alpha = null;
	      
	      try {
	         // creates a console object
	         cnsl = System.console();

	         // if console is not null
	         if (cnsl != null) {
	            
	            // read line from the user input
	            alpha = cnsl.readLine("Name: ");
	            
	            // prints
	            System.out.println("Name is: " + alpha);
	            
	            // read password into the char array
	            char[] pwd = cnsl.readPassword("Password: ");
	            
	            // prints
	            System.out.println("Password is: "+pwd[0]);
	         } 
	         
	      } catch(Exception ex) {
	         // if any error occurs
	         System.out.println("DSFDS");    
	      }
	   }
	}