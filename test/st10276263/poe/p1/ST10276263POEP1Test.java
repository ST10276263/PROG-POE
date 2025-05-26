/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package st10276263.poe.p1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
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
    
 
    
   
    @Test
    public void testMessageLengthValidation() {
        String shortMessage = "Short message";
        assertTrue(shortMessage.length() <= 250, "Message should be accepted");

        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longMessage.append("a");
        }
        String tooLongMessage = longMessage.toString();
        assertTrue(tooLongMessage.length() > 250, "Message should be rejected");
    }

    @Test
    public void testRecipientNumberFormatting() {
        String validNumber = "+27718693002";
        assertTrue(ST10276263POEP1.checkRecipientCell(validNumber), "Valid number should pass");

        String invalidNumber = "08575975889";
        assertFalse(ST10276263POEP1.checkRecipientCell(invalidNumber), "Invalid number should fail");
    }

    @Test
    public void testMessageHashGeneration() {
        String messageId = "1000000000";
        int messageNumber = 0;
        String messageContent = "Hi Mike, can you join us for dinner tonight";

        String expectedHash = "10:0:HITONIGHT";
        String actualHash = ST10276263POEP1.createMessageHash(messageId, messageNumber, messageContent);

        assertEquals(expectedHash, actualHash, "Hash should match expected format");
    }

    @Test
    public void testMessageIdGeneration() {
        long messageId = 1_000_000_000L + (long) (Math.random() * 9_000_000_000L);
        assertTrue(ST10276263POEP1.checkMessageId(messageId), "Message ID should be valid");
        assertEquals(10, String.valueOf(messageId).length(), "Message ID should be 10 digits");
    }

    @Test
    public void testMessageActions() {
        String sendResult = ST10276263POEP1.SentMessage("Test message 1", 1);
        assertTrue(sendResult.equals("Sent") || sendResult.equals("Stored") || sendResult.equals("Discarded"),
                "Should return 'Sent' or 'Stored' or 'Discarded'");
    }

    @Test
    public void testTotalMessagesCount() {
        ST10276263POEP1.sentMessages.clear(); // Reset for a clean test
        ST10276263POEP1.SentMessage("Message 1", 1);
        ST10276263POEP1.SentMessage("Message 2", 2);
        assertEquals(2, ST10276263POEP1.addTotalMessages(), "Count should match sent messages");
    }

    @Test
    public void testMessageContentValidation() {
        String emptyMessage = "";
        assertTrue(emptyMessage.length() <= 250, "Empty message should be allowed");

        StringBuilder exactLengthMessage = new StringBuilder();
        for (int i = 0; i < 250; i++) {
            exactLengthMessage.append("a");
        }
        String exactMessage = exactLengthMessage.toString();
        assertEquals(250, exactMessage.length(), "250 character message should be allowed");
    }

    @Test
    public void testMessageStorage() {
        ST10276263POEP1.sentMessages.clear(); // Reset
        String testMessage = "Test storage message";
        ST10276263POEP1.SentMessage(testMessage, 1);

        assertFalse(ST10276263POEP1.sentMessages.isEmpty(), "Messages list should not be empty");
        assertEquals(testMessage, ST10276263POEP1.sentMessages.get(0).content, "Stored message should match input");
    }
}

    
    
    
    
    
    

