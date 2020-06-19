package gui_project.ModelController;

import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DetectiveControllerTest
{
    private DetectiveController sut;
    private Detective detective;
    private Rectangle itemBlock;

    public DetectiveControllerTest()
    {
        
    }

    @BeforeClass
    public static void setUpClass()
    {

    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        detective = new Detective();
        sut = new DetectiveController(detective);
        itemBlock = new Rectangle(10,15,770,470);
    }

    @After
    public void tearDown()
    {
       
    }

    @Test
    public void detectiveXLocation_ShouldNotChange_IfThereIsCollision()
    {
        System.out.println("Detective X location collision test");
        //Arrange
        int keyCode = 65;
        boolean houseCollision = false;
        boolean roomBoundaryCollision = true;
        detective.setLocationX(0);
        
        //Act
        sut.keyPressed(keyCode, itemBlock, houseCollision, roomBoundaryCollision);
        //Assert
        assertEquals(0, detective.getLocationX());
    }
    
    @Test
    public void detectiveYLocation_ShouldNotChange_IfThereIsCollision()
    {
        System.out.println("Detective Y location collision test");
        //Arrange
        int keyCode = 87;
        boolean houseCollision = false;
        boolean roomBoundaryCollision = true;
        detective.setLocationY(0);
        
        //Act
        sut.keyPressed(keyCode, itemBlock, houseCollision, roomBoundaryCollision);
        //Assert
        assertEquals(0, detective.getLocationY());
    }
}
