/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_project.ModelController;

import java.awt.Graphics2D;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Angelo
 */
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
    public void hintVisible_ShouldAlwaysBeFalse_AfterPickup() throws IOException
    {
        System.out.println("Hint visibility test");
        //Arrange
        Detective detective = new Detective();
        MainController mainCtrl = new MainController(detective);
        DetectiveController detectiveCtrl = new DetectiveController(detective);
        Hint hint = new Hint("Test", "Test", 500, 260, 10, 10);
        HintController sut = new HintController(mainCtrl, detectiveCtrl, hint);

        //Act
        //set hint visibility to true;
        sut.reveal();
        assertEquals(true, hint.isVisible());

        sut.pickup();
        //try to reveal the hint again
        sut.reveal();
        sut.reveal();

        //Assert
        assertEquals(false, hint.isVisible());
    }
}
