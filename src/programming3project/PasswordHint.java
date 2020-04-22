/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author pc
 */
public class PasswordHint
{

    private static LinkedList<String> SAVEDCODES = new LinkedList<>();
    private PasswordHintType function;
    private Password lock;
    private String question;
    private String answer;
    private String hint;
    private String beforeQuestion;
    private int order;

    public PasswordHint(PasswordHintType function, Password lock, int order, String answer)
    {
        this.setFunction(function);
        this.setLock(lock);
        this.setOrder(order);
        setupHint();
        setupQuestionAndAnswer(answer);
        setupTrivias();
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

        System.out.print("Press y to get hint, any character to leave.> ");
        //System.out.println(this.getAnswer());
        boolean enterPass = "y".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (enterPass)
        {
            System.out.println(this.getBeforeQuestion());
            System.out.println(this.getQuestion());
            System.out.print("> ");
            String userInput = Game.SYSTEMINPUT.nextLine();

            while (!userInput.equalsIgnoreCase("q"))
            {
                if (userInput.equalsIgnoreCase(this.getAnswer()))
                {
                    System.out.println("You are right! Great job!");
                    System.out.println("Hint: " + this.getHint());
                    correct = true;
                    userInput = "q";
                } else if (!userInput.equalsIgnoreCase("q"))
                {
                    System.out.println("You are wrong! Press q to quit.> ");
                    userInput = Game.SYSTEMINPUT.nextLine();
                }
            }
        }

        return correct;
    }

    public void saveHint()
    {
        System.out.print("Do you want to save this hint(y)? ");
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

                try (BufferedWriter pw = new BufferedWriter(new FileWriter(
                        Game.getCompletePath("PasswordHints.txt"))))
                {
                    for (int size = 0; size < SAVEDCODES.size(); size++)
                    {
                        pw.append(SAVEDCODES.get(size) + '\n');
                    }

                    System.out.println("The hint has been saved! Press enter to return to your location.");
                    Game.SYSTEMINPUT.nextLine();
                } catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void printHintFile()
    {
        System.out.println("Opening your saved hints:");
        try (BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath("PasswordHints.txt"))))
        {
            String line = "";
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteCode()
    {
        System.out.println("\nYou can only save 2 codes!");
        printHintFile();

        boolean isDelete = false;

        System.out.println("Press d to delete the first one, any character to leave.> ");

        isDelete = "d".equalsIgnoreCase(Game.SYSTEMINPUT.nextLine());

        if (isDelete)
        {
            SAVEDCODES.remove();
        }
    }

    public void setupQuestionAndAnswer(String answer)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath("Questions.txt"))))
        {
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
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void setupTrivias()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(
                Game.getCompletePath("BeforeQuestion.txt"))))
        {
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
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void setupHint()
    {
        String aHint = "";
        if (getFunction() == PasswordHintType.HINTHEAD)
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
    public PasswordHintType getFunction()
    {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(PasswordHintType function)
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
     * @return the hint
     */
    public String getHint()
    {
        return hint;
    }

    /**
     * @param printHint the hint to set
     */
    public void setHint(String printHint)
    {
        this.hint = printHint;
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
