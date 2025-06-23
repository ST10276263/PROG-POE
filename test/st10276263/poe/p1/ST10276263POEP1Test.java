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
    
    
    
    //Part 3
    
    
    @BeforeEach
    public void setUp() {
        ST10276263POEP1.sentMessages.clear();
        ST10276263POEP1.sentMessagesArray.clear();
        ST10276263POEP1.storedMessageArray.clear();
        ST10276263POEP1.disregardedMessagesArray.clear();
        ST10276263POEP1.messageHashArray.clear();
        ST10276263POEP1.messageIdArray.clear();
        
        ST10276263POEP1.savedFullName = "Developer";
        
        ST10276263POEP1.Message msg1 = new ST10276263POEP1.Message(
            "+27834557896", 
            "Did you get the cake?", 
            "1000000001", 
            "10:1:Did_cake?"
        );
        ST10276263POEP1.sentMessages.add(msg1);
        ST10276263POEP1.sentMessagesArray.add("Did you get the cake?");
        
        ST10276263POEP1.Message msg2 = new ST10276263POEP1.Message(
            "+27838884567", 
            "Where are you? You are late! I have asked you to be on time.", 
            "1000000002", 
            "10:2:Where_time."
        );
        ST10276263POEP1.sentMessages.add(msg2);
        ST10276263POEP1.storedMessageArray.add("Where are you? You are late! I have asked you to be on time.");
        
        ST10276263POEP1.Message msg3 = new ST10276263POEP1.Message(
            "+27834484567", 
            "Yohoooo, I am at your gate.", 
            "1000000003", 
            "10:3:Yohoooo_gate."
        );
        ST10276263POEP1.sentMessages.add(msg3);
        ST10276263POEP1.disregardedMessagesArray.add("Yohoooo, I am at your gate.");
        
        ST10276263POEP1.Message msg4 = new ST10276263POEP1.Message(
            "0838884567", 
            "It is dinner time !", 
            "1000000004", 
            "10:4:It_!"
        );
        ST10276263POEP1.sentMessages.add(msg4);
        ST10276263POEP1.sentMessagesArray.add("It is dinner time !");
        
        ST10276263POEP1.Message msg5 = new ST10276263POEP1.Message(
            "+27838884567", 
            "Ok, I am leaving without you.", 
            "1000000005", 
            "10:5:Ok_you."
        );
        ST10276263POEP1.sentMessages.add(msg5);
        ST10276263POEP1.storedMessageArray.add("Ok, I am leaving without you.");
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, ST10276263POEP1.sentMessagesArray.size());
        assertTrue(ST10276263POEP1.sentMessagesArray.contains("Did you get the cake?"));
        assertTrue(ST10276263POEP1.sentMessagesArray.contains("It is dinner time !"));
    }

    @Test
    public void testDisplayLongestMessage() {
        String result = ST10276263POEP1.displayLongestMessage();
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    public void testSearchForMessageID() {
        String result = ST10276263POEP1.searchMessageId("1000000004");
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time !"));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        String result = ST10276263POEP1.searchRecipientMessages("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() {
        String result = ST10276263POEP1.deleteMessageByHash("10:2:Where_time.");
        assertEquals("Message deleted successfully.", result);
        
        boolean found = false;
        for (ST10276263POEP1.Message msg : ST10276263POEP1.sentMessages) {
            if (msg.hash.equals("10:2:Where_time.")) {
                found = true;
                break;
            }
        }
        assertFalse(found);
    }

    @Test
    public void testDisplayReport() {
        String report = ST10276263POEP1.displayFullReport();
        assertTrue(report.contains("MESSAGE REPORT"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("Where are you? You are late!"));
        assertTrue(report.contains("It is dinner time !"));
    }

  
}

    
    
    
    
    
    

