package gui_project.ModelController;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HintControllerTest
{
    public HintControllerTest()
    {
        
    }

    @Before
    public void setUp()
    {
        
    }

    @After
    public void tearDown()
    {
        
    }

    @Test
    public void hintVisible_ShouldAlwaysBeFalse_AfterPickup() throws IOException, SQLException
    {
        System.out.println("Hint visibility test");
        //Arrange
        Hint hint = new Hint("Test", "Test", 500, 260, 10, 10);
        HintController sut = new HintController(null, null, hint);

        //Act
        //set hint visibility to true;
        sut.reveal();
        assertEquals(true, hint.isVisible());

        sut.pickup();
        //try to reveal the hint again
        sut.reveal();

        //Assert
        assertEquals(false, hint.isVisible());
    }
}
