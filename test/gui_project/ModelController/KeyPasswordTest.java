package gui_project.ModelController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KeyPasswordTest 
{
    private LockedArea lockedArea;
    
    public KeyPasswordTest()
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
        
    }
    
    @After
    public void tearDown()
    {
        
    }

    /**
     * Test of setPassword method, of class KeyPassword.
     * Make sure that KEYHEAD is formatted as "__XX" and matches half part of the real password
     */
    @Test
    public void testSetupKeyPassword_PasswordHead_FormatAndValueShouldBeCorrect() 
    {
        System.out.println("Setup Password Head: __XX");
        //Arrange
        lockedArea = new LockedArea(0, 0, 5, 5, "LockedAreaTest");
        lockedArea.setPassword("2613");      
        KeyPassword sut = new KeyPassword(0, 0, 5, 5, lockedArea, "~", 
                "KeyPasswordTest", KeyPasswordType.KEYHEAD);
        //Act
        sut.setupKeyPassword();
        String expectedResult = "26XX";
        
        //Assert
        assertEquals(expectedResult, sut.getPassword());
    }
    
    /**
     * Test of setPassword method, of class KeyPassword.
     * Make sure that KEYTAIL is formatted as "XX__" and matches half part of the real password
     */
    @Test
    public void testSetupKeyPassword_PasswordTail_FormatAndValueShouldBeCorrect() 
    {
        System.out.println("Setup Password Tail: XX__");
        //Arrange
        lockedArea = new LockedArea(0, 0, 5, 5, "LockedAreaTest");
        lockedArea.setPassword("9781");        
        KeyPassword sut = new KeyPassword(0, 0, 5, 5, lockedArea, "~", 
                "KeyPasswordTest", KeyPasswordType.KEYTAIL);
        //Act
        sut.setupKeyPassword();
        String expectedResult = "XX81";
        //Assert
        assertEquals(expectedResult, sut.getPassword());
    }
}
