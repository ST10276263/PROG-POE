/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package st10276263.poe.p1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author matthews
 */
public class ST10276263POEP1Test {
    
    public ST10276263POEP1Test() {
    }

    @Test
    public void testCheckUserNameValid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertTrue(test.checkUserName("kyl_1"), "Username is valid");
    }
    
    @Test
    public void testCheckUserNameInvalid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertFalse(test.checkUserName("kyle!!!!!!!!"), "Username is invalid");
    }

    @Test
    public void testCheckPasswordComplexityValid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertTrue(test.checkPasswordComplexity("Ch&&secke99!"), "Password is valid");
    }
    
    @Test
    public void testCheckPasswordComplexityInvalid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertFalse(test.checkPasswordComplexity("password"), "Password is invalid");
    }

    @Test
    public void testCheckCellPhoneNumberValid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertTrue(test.checkCellPhoneNumber("+2783896876"), "Cell number successfully captured.");
    }
    
    @Test
    public void testCheckCellPhoneNumberInvalid() {
        ST10276263POEP1 test = new ST10276263POEP1();
        assertFalse(test.checkCellPhoneNumber("0383896876"), "Cell phone number is incorrectly formatted");
    }
    
    
    
    
    
    
    //Part 2
    
    
    
    
    
    
    
}
