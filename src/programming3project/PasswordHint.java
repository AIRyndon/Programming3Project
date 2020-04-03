/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class PasswordHint
{

    private Scanner scan = new Scanner(System.in);
    private Random rand = new Random();
    private PasswordHintType function;
    private Password lock;
    private String question;
    private String answer;
    private String hint;
    private String beforeQuestion;
    private int order;
    public static int hintSize = 0;
    public static String[] hintFileContent = new String[2];

    public PasswordHint(PasswordHintType function, Password lock, int order)
    {
        this.setFunction(function);
        this.setLock(lock);
        this.setOrder(order);
        setupHint();
        setupQuestionAndAnswer();
        setupBeforeQuestion();
    }

    public PasswordHint()
    {

    }

    public boolean promptAnswer()
    {
        boolean correct = false;

        System.out.println("Press y to get hint, any character to leave: ");
        System.out.println(this.getAnswer());
        boolean enterPass = "y".equalsIgnoreCase(scan.nextLine());
        int count = 0;

        while (enterPass)
        {
            if (count == 0)
            {
                System.out.println(this.getBeforeQuestion());
            }

            System.out.println(this.getQuestion());
            System.out.print("> ");
            String userInput = scan.nextLine();

            if (userInput.equalsIgnoreCase(this.getAnswer()))
            {
                System.out.println("You are right! A round of applause for your intelligence!");
                System.out.println("Hint: " + this.getHint());
                correct = true;
                enterPass = false;
            } else if (!userInput.equalsIgnoreCase("q"))
            {
                System.out.println("You are wrong! Enter your answer again or press q to quit.");
            }

            if (userInput.equalsIgnoreCase("q"))
            {
                enterPass = false;
            }

            count = 1;
        }

        return correct;
    }

    public void saveHint()
    {
        System.out.print("Do you want to save this hint(y)? ");
        boolean save = "y".equalsIgnoreCase(scan.nextLine());

        if (save)
        {
            if (hintSize == 2)
            {
                deleteHint();
            }

            if (hintSize < 2)
            {
                try (PrintWriter pw = new PrintWriter(new FileOutputStream("Hints.txt", true)))
                {
                    pw.append(this.getHint().toString() + "\n");
                    System.out.println("The hint has been saved!");
                    hintSize++;
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
        try (BufferedReader br = new BufferedReader(new FileReader(Game.getCompletePath("Hints.txt"))))
        {
            int count = 0;
            String line = "";

            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
                hintFileContent[count] = line;
                count++;
            }
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteHint()
    {
        System.out.println("Ouf of memory! Press y to you to delete previous hint.");
        printHintFile();

        System.out.println("Press 1 to delete first hint.");
        System.out.println("Press 2 to delete second hint.");
        System.out.println("Press any character to quit.");
        char deleteHint = scan.next().charAt(0);

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(Game.getCompletePath("Hints.txt"), false)))
        {
            if (deleteHint == '1')
            {
                pw.print(hintFileContent[0] + "\n");
                hintSize--;
            } else if (deleteHint == '2')
            {
                pw.print(hintFileContent[1] + "\n");
                hintSize--;
            }
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        //clear buffer
        scan.nextLine();
    }

    public void setupQuestionAndAnswer()
    {
        try (BufferedReader br = new BufferedReader(new FileReader(Game.getCompletePath("Questions.txt"))))
        {
            String line = "";

            while ((line = br.readLine()) != null)
            {
                if (line.contains(Integer.toString(this.getOrder())))
                {
                    this.setQuestion(line.substring(1));
                    this.setAnswer(br.readLine());

                    break;
                }
            }
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void setupBeforeQuestion()
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
        if (getFunction() == PasswordHintType.HINTHEAD)
        {
            String aHint = this.getLock().toString().substring(0, 2) + "XX";

            this.setHint(aHint);
        } else
        {
            String aHint = "XX" + this.getLock().toString().substring(2);

            this.setHint(aHint);
        }
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
