/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class KeyPassword
{
    public static LinkedList<String> SAVEDCODES = new LinkedList<>();
    private KeyPasswordType function;
    private Password lock;
    private String question;
    private String answer;
    private String keyPassword;
    private String beforeQuestion;
    private int order;

    public KeyPassword(KeyPasswordType function, Password lock, int order, String answer) throws IOException
    {
        this.setFunction(function);
        this.setLock(lock);
        this.setOrder(order);
        
        setupHint();
        setupQuestionAndAnswer(answer);
        setupTrivias();
    }
    
    public static void giveLockAdvice()
    {
        if (SAVEDCODES.size() == 0)
        {
            System.out.println("*Find key password to unlock the door.\n");
        }
    }
    
    public static LinkedList<String> getSavedCodes()
    {
        LinkedList<String> copy = new LinkedList<>();
        Iterator<String> iterator = SAVEDCODES.iterator();

        while (iterator.hasNext())
        {
            copy.add(iterator.next());
        }

        return copy;
    }

    public boolean promptAnswer()
    {
        boolean correct = false;

        System.out.print("\nPress y to get password, any character to leave: ");
        boolean enterPass = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (enterPass)
        {
            System.out.println(this.getBeforeQuestion());
            System.out.println(this.getQuestion());
            System.out.print("> ");
            String userInput = Game.SYSTEMINPUT.nextLine();

            do
            {
                if (userInput.equalsIgnoreCase(this.getAnswer()))
                {
                    System.out.println("You are right! Great job!");
                    System.out.println("Key password: " + this.getHint());
                    correct = true;
                    userInput = "q";
                } 
                else
                {
                    System.out.println("You are wrong! Answer again or press q to quit.");
                    System.out.print("> ");
                    userInput = Game.SYSTEMINPUT.nextLine();
                }
            } while (!userInput.equalsIgnoreCase("q"));
        }

        return correct;
    }

    public void saveKeyPassword() throws IOException
    {
        System.out.print("\nDo you want to save the key password (y)? ");
        boolean save = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (save)
        {
            if (SAVEDCODES.size() == 2)
            {
                deleteCode();
            }

            if (SAVEDCODES.size() < 2)
            {
                SAVEDCODES.add(this.getHint());

                try (FileWriter pw = new FileWriter(
                        Game.getCompletePath("PasswordHints.txt")))
                {
                    for (int size = 0; size < SAVEDCODES.size(); size++)
                    {
                        pw.append(SAVEDCODES.get(size) + '\n');
                    }

                    System.out.println("The key password has been saved! Press enter to continue.");
                    Game.SYSTEMINPUT.nextLine();
                } 
                catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void deleteCode()
    {
        System.out.println("\nYou can only save 2 key passwords!");
        Game.printSavedKeyPassword(0);

        boolean isDelete = false;

        System.out.print("Press y to delete the first one, any character to leave: ");

        isDelete = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (isDelete)
        {
            SAVEDCODES.remove();
        }
    }

    public void setupQuestionAndAnswer(String answer) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath("Questions.txt")));
        String line = "";

        while ((line = br.readLine()) != null)
        {
            if (line.contains(Integer.toString(this.getOrder())))
            {
                this.setQuestion(line.substring(1));
                this.setAnswer(answer);

                break;
            }
        }
    }

    public void setupTrivias() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath("BeforeQuestion.txt")));
        String line = "";
        String beforeQuestions = "";

        while ((line = br.readLine()) != null)
        {
            if (line.contains(Integer.toString(this.getOrder())))
            {
                while (!(line = br.readLine()).isEmpty())
                {
                    beforeQuestions += line + "\n";
                }

                break;
            }
        }

        this.setBeforeQuestion(beforeQuestions);
    }

    public void setupHint()
    {
        String aHint = "";
        if (getFunction() == KeyPasswordType.KEYHEAD)
        {
            aHint = this.getLock().toString().substring(0, 2) + "XX";
        } else
        {
            aHint = "XX" + this.getLock().toString().substring(2);
        }
        this.setHint(aHint);
    }

    /**
     * @return the function
     */
    public KeyPasswordType getFunction()
    {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(KeyPasswordType function)
    {
        this.function = function;
    }

    /**
     * @return the question
     */
    public String getQuestion()
    {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question)
    {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer()
    {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    /**
     * @return the keyPassword
     */
    public String getHint()
    {
        return keyPassword;
    }

    /**
     * @param printHint the keyPassword to set
     */
    public void setHint(String printHint)
    {
        this.keyPassword = printHint;
    }

    /**
     * @return the lock
     */
    public Password getLock()
    {
        return lock;
    }

    /**
     * @param lock the lock to set
     */
    public void setLock(Password lock)
    {
        this.lock = lock;
    }

    /**
     * @return the order
     */
    public int getOrder()
    {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order)
    {
        this.order = order;
    }

    /**
     * @return the beforeQuestion
     */
    public String getBeforeQuestion()
    {
        return beforeQuestion;
    }

    /**
     * @param printQuestion the beforeQuestion to set
     */
    public void setBeforeQuestion(String printQuestion)
    {
        this.beforeQuestion = printQuestion;
    }

    @Override
    public String toString()
    {
        return this.getHint();
    }
}
