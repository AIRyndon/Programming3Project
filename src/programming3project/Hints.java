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
public class Hints 
{
    private Random rand = new Random();
    private static Scanner scan = new Scanner(System.in);
    
    private Locks lock;
    private Functions function;
    private String question;
    private String answer;
    private String hint;
    private int order;
    private String beforeQuestion;
    
    public Hints(Functions function, Locks lock, int order) throws IOException
    {
        this.setFunction(function);
        this.setLock(lock);
        this.setOrder(order);
        setupHint();
        setupQuestionAndAnswer();
        setupBeforeQuestion();
    }
    
    public Hints()
    {
        
    }
    
    public boolean promtAnswer()
    {
        boolean correct = false;

        System.out.println("Press y to get hint, any character to leave: ");
        System.out.println(this.getAnswer());
        boolean enterPass = "y".equalsIgnoreCase(scan.nextLine());
        int count = 0;
        
        while(enterPass)
        {
            if(count == 0)
            {
                System.out.println(this.getBeforeQuestion());
            }
            
            System.out.println(this.getQuestion());
            System.out.print("> ");
            String userInput = scan.nextLine();
            
            if(userInput.equalsIgnoreCase(this.getAnswer()))
            {
                System.out.println("You are right! A round of applause for your itelligence!");
                System.out.println("Hint: " + this.getHint());
                correct = true;
                enterPass = false;
            }
            else if(!userInput.equalsIgnoreCase("q"))
            {
                System.out.println("You are wrong! Enter your answer again or press q to quit.");
            }
            
            if(userInput.equalsIgnoreCase("q"))
            {
                enterPass = false;
            }
            
            count = 1;
        }
        
        return correct;
    }
    
    public void saveHint() throws FileNotFoundException
    {
        System.out.println("Note: You cannot read this information again.");
        System.out.print("Do you want to save this hint(y)? ");
        boolean save = "y".equalsIgnoreCase(scan.nextLine());
        
        if(save)
        {
            PrintWriter pw = new PrintWriter(new FileOutputStream("Hints.txt", true));
            pw.append(this.getHint().toString() + "\n");
            pw.close();
            System.out.println("The hint has been saved!");
        }
    }
    
    public void setupQuestionAndAnswer() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("Questions.txt"));
        
        String line = "";
        
        while((line = br.readLine()) != null)
        {
            if(line.contains(Integer.toString(this.getOrder())))
            {
                this.setQuestion(line.substring(1));
                this.setAnswer(br.readLine());
                
                break;
            }
        }
        
        br.close();
    }
    
    public void setupBeforeQuestion() throws FileNotFoundException, IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("BeforeQuestion.txt"));
        
        String line = "";
        String beforeQuestions = "";
        
        while((line = br.readLine()) != null)
        {
            if(line.contains(Integer.toString(this.getOrder())))
            {
                while(!(line = br.readLine()).isEmpty())
                {
                    beforeQuestions += line + "\n";
                }
                
                break;
            }
        }
        
        this.setBeforeQuestion(beforeQuestions);
        br.close();
    }

    public void setupHint()
    {
        if(getFunction() == Functions.HINTHEAD)
        {
            String aHint = this.getLock().toString().substring(0, 2) + "XX";
            
            this.setHint(aHint);
        }
        else 
        {
            String aHint = "XX" + this.getLock().toString().substring(2);
            
            this.setHint(aHint);
        }
    }
    
    /**
     * @return the function
     */
    public Functions getFunction() {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(Functions function) {
        this.function = function;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * @param printHint the hint to set
     */
    public void setHint(String printHint) {
        this.hint = printHint;
    }

    /**
     * @return the lock
     */
    public Locks getLock() {
        return lock;
    }

    /**
     * @param lock the lock to set
     */
    public void setLock(Locks lock) {
        this.lock = lock;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }
    
    /**
     * @return the beforeQuestion
     */
    public String getBeforeQuestion() {
        return beforeQuestion;
    }

    /**
     * @param printQuestion the beforeQuestion to set
     */
    public void setBeforeQuestion(String printQuestion) {
        this.beforeQuestion = printQuestion;
    }
    
    @Override
    public String toString()
    {
        return this.getHint();
    }


}
