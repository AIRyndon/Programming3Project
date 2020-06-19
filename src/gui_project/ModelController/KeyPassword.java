package gui_project.ModelController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyPassword extends ItemBlock
{
    private final LockedArea lockedArea;
    private final String name;
    private final KeyPasswordType keyType;
    private String password;
    private String message;
    private String[] trivia;
    private String question;
    private String answer;
    private boolean correct = false;
    private boolean pickup = false;
    private String symbol;

    public KeyPassword(int locationX, int locationY, int width, int height, LockedArea lockedArea,
            String symbol, String name, KeyPasswordType keyType)
    {
        super(locationX, locationY, width, height);
        this.lockedArea = lockedArea;
        this.name = name;
        this.symbol = symbol;
        this.keyType = keyType;

        setupKeyPassword();
        setupQuestionAndAnswer();
        setupTrivia();
    }

    public void setupKeyPassword()
    {
        if (keyType == KeyPasswordType.KEYTAIL)
        {
            password = "XX" + lockedArea.getPassword().substring(2);
        } 
        else
        {
            password = lockedArea.getPassword().substring(0, 2) + "XX";
        }

        System.out.println(password);
    }

    private void setupQuestionAndAnswer()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(
                    System.getProperty("user.dir") + "/FileDB/" + ("Questions.txt")));

            String line = "";

            while ((line = br.readLine()) != null)
            {
                if (line.contains(symbol))
                {
                    String[] questionAndAnswer = line.substring(1).split("/");
                    question = questionAndAnswer[0];
                    answer = questionAndAnswer[1];

                    break;
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void setupTrivia()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(
                    System.getProperty("user.dir") + "/FileDB/" + ("BeforeQuestion.txt")));
            String line = "";
            String triviaResult = "";

            while ((line = br.readLine()) != null)
            {
                if (line.contains(symbol))
                {
                    while (!(line = br.readLine()).isEmpty())
                    {
                        triviaResult += line + "\n";
                    }

                    break;
                }
            }

            trivia = triviaResult.split("\n");
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    //Setters and getters
    public String getAnswer()
    {
        return answer;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getTrivia()
    {
        return trivia;
    }

    public boolean isCorrect()
    {
        return correct;
    }

    public boolean isPickup()
    {
        return pickup;
    }

    void hasPickup()
    {
        if (correct)
        {
            this.pickup = true;
        }
    }

    void updateCorrect()
    {
        correct = true;
        notifyObservers();
    }
    
    public String getMessage()
    {
        return message;
    }

    void updateMessage()
    {
        this.message = password;
        sendMessage();
    }

    void clearMessage()
    {
        this.message = "";
    }

    void sendMessage()
    {
        notifyObservers();
    }
}
