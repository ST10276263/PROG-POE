
package st10276263.poe.p1;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class ST10276263POEP1 { 
        
    //Stored data
    public static String savedFullName, savedNum, savedUserN, savedPassW; //found this from the Java textbook
    
    
    //Boolean Method to test that the Username meets all conditions
    public static boolean checkUserName(String Username){
        if(!Username.contains("_") || Username.length() > 5) {
            System.out.println("Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length");
            return false;
        }
        System.out.println("Username successfully captured.");
        return true;
    }    
    
    //Boolean Method to test that the Password meets all conditions
    public static boolean checkPasswordComplexity(String Password) {

        boolean hasCapital_Letter = false;
        boolean hasDigit = false;
        boolean hasSpecial_Symbol = false;
        
        for (char ch : Password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasCapital_Letter = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial_Symbol = true;
            }
        }
        
        if (Password.length() < 8 || !hasCapital_Letter || !hasDigit || !hasSpecial_Symbol) {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
        
        System.out.println("Password successfully captured.");
        return true;
    }
    
    //Ensure that PhoneNum starts with +27 and is 12 characters long
    public static boolean checkCellPhoneNumber(String PhoneNum) {
        if(!PhoneNum.startsWith("+27") || PhoneNum.length() != 12 ){
            System.out.println("Cell phone number is incorrectly formatted or does not contain an international code, please correct the number and try again.");
            return false;
        }
        
        System.out.println("Cell phone number successfully captured.");
        return true;   
        }
    
    //Register user
    public static String registerUser(String Username, String Password) {
        String message = "";
        
        if(checkUserName(Username)) {
            savedUserN = Username;
            message += "Username successfully captured.";
        } else {
            message += "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters long";
        }
        
        if(checkPasswordComplexity(Password)) {
            savedPassW = Password;
            message += "Password successfully captured";
        } else {
            message += "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }
        return message;
    }
    
    //Boolean Method to verify that login details match details input by user from registration
    public static boolean loginUser(String Username, String Password) {
        return Username.equals(savedUserN) && Password.equals(savedPassW);
    }
    
    //Method to display status of login
    public static String returnLoginStatus(String Username, String Password) {
        if(loginUser(Username, Password)) {
            return "Welcome " + savedFullName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    
    
    
    
//Part 2 Methods    
    
    // Stored data
    public static String savedReNumber,savedMessageId;
    
    //test that Recipient's number is formatted correctly
    public static boolean checkRecipientCell(String rNumber) {
        if(!rNumber.startsWith("+27") || rNumber.length() != 12 ){
            JOptionPane.showMessageDialog(null,"""
                                               Recipient's phone number is incorrectly formatted or does not contain an international code.
                                               Please correct the number and try again.""");
            return false;
        }

        return true;   
        }
    
        //test that message ID is formatted correctly,not more than 10 digits
        public static boolean checkMessageId(long messageId) {
           if (String.valueOf(messageId).length() == 10) {
               return true;
           }

           return false;   
        }

        // Message list
        public static ArrayList<Message> sentMessages = new ArrayList<>();

        // Message class
        static class Message {
            String recipientNumber, content, messageId, hash;

            public Message(String recipientNumber, String content, String messageId, String hash) {
                this.recipientNumber = recipientNumber;
                this.content = content;
                this.messageId = messageId;
                this.hash = hash;
            }

            public String toString() {
                return "Sent to: " + recipientNumber 
                        + "\nMessage ID: " + messageId
                        + "\nMessage hash: " + hash
                        + "\nMessage sent: " + content;
            }
        }
    
        public static String SentMessage(String message, int messageNumber) {
        int messageOptions = Integer.parseInt(JOptionPane.showInputDialog(null, """
                        Choose an Option:
                        1) Send message
                        2) Store without sending
                        3) Disregard
                        """, "QuickChat", JOptionPane.INFORMATION_MESSAGE));

        // Auto generate message ID
        if (messageOptions == 1 || messageOptions == 2) {
            long messageId = 1_000_000_000L + (long) (Math.random() * 9_000_000_000L);
            if (checkMessageId(messageId)) {
                savedMessageId = String.valueOf(messageId);
            }

            String messageHash = createMessageHash(savedMessageId, messageNumber, message);
                
                Message newMessage = new Message(savedReNumber, message, savedMessageId, messageHash);
                sentMessages.add(newMessage);

            //Addition of Array Method calls    
            if (messageOptions == 1) {
                sentMessagesArray.add(message);    
                messageHashArray.add(messageHash);     
                messageIdArray.add(savedMessageId);     
                JOptionPane.showMessageDialog(null, newMessage,
                        "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                return "Sent";
            } else {
                storedMessageArray.add(message);     
                JOptionPane.showMessageDialog(null, "Message stored (not sent).",
                        "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                return "Stored";
            }
        } else if (messageOptions == 3) {
            disregardedMessagesArray.add(message);     
            JOptionPane.showMessageDialog(null, "Message discarded.",
                    "QuickChat", JOptionPane.INFORMATION_MESSAGE);
            return "Discarded";
        } else {
            JOptionPane.showMessageDialog(null, "Invalid choice. Message discarded by default.",
                    "QuickChat", JOptionPane.INFORMATION_MESSAGE);
            return "Invalid";
        }
        }
    
        //Method to add the total messages that have been sent by the user
        public static int addTotalMessages() {
            return sentMessages.size();
        }


        public static String printMessage() {
            if (sentMessages.isEmpty()) {
                return "No messages were sent.";
            }

            StringBuilder messageList = new StringBuilder("Messages sent:\n\n");
            for (Message msg : sentMessages) {
                messageList.append(msg.toString()).append("\n\n");
            }
            return messageList.toString();
        }
        
        public static String createMessageHash(String messageId, int messageNumber, String messageContent) {
            String[] words = messageContent.trim().split("\\s+");
            String firstWord = words.length > 0 ? words[0] : "";
            String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
            String hash = messageId.substring(0, 2) + ":" + messageNumber + ":" + firstWord + "_" + lastWord;
            return hash.toUpperCase();
        }
        
        //New Addition of JSON Methods
        private static void writeMessagesToJsonFile() {
        File file = new File("messages.json");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("[\n");
            for (int i = 0; i < sentMessages.size(); i++) {
                Message msg = sentMessages.get(i);
                writer.write(String.format(
                    "  {\n" +
                    "    \"recipientNumber\": \"%s\",\n" +
                    "    \"content\": \"%s\",\n" +
                    "    \"messageId\": \"%s\",\n" +
                    "    \"hash\": \"%s\"\n" +
                    "  }",
                    escapeJson(msg.recipientNumber),
                    escapeJson(msg.content),
                    escapeJson(msg.messageId),
                    escapeJson(msg.hash)
                ));
                if (i < sentMessages.size() - 1) writer.write(",");
                writer.write("\n");
            }
            writer.write("]");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving messages: " + e.getMessage(),
                                          "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void readMessagesFromJsonFile() {
        File file = new File("messages.json");
        sentMessages.clear();
        
        if (!file.exists()) return;
        
        try (Scanner scanner = new Scanner(file)) {
            StringBuilder json = new StringBuilder();
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine().trim());
            }
            
            String[] messages = json.toString().split("\\},\\s*\\{");
            for (String msg : messages) {
                msg = msg.replaceAll("[\\[\\]{}]", "");
                if (msg.trim().isEmpty()) continue;
                
                String[] parts = msg.split("\",\\s*\"");
                String recipient = "", content = "", id = "", hash = "";
                
                for (String part : parts) {
                    if (part.startsWith("recipientNumber")) 
                        recipient = part.split(":")[1].replace("\"", "").trim();
                    else if (part.startsWith("content")) 
                        content = part.split(":")[1].replace("\"", "").trim();
                    else if (part.startsWith("messageId")) 
                        id = part.split(":")[1].replace("\"", "").trim();
                    else if (part.startsWith("hash")) 
                        hash = part.split(":")[1].replace("\"", "").trim();
                }
                sentMessages.add(new Message(recipient, content, id, hash));
            }
        } catch (FileNotFoundException e) {
            // Silent fail - file will be created on first save
        }
    }

    private static String escapeJson(String input) {
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r");
    }
    
    
    
    
    
    //Part 3 Methods
        
        public static ArrayList<String> sentMessagesArray = new ArrayList<>();
        public static ArrayList<String> disregardedMessagesArray = new ArrayList<>();
        public static ArrayList<String> storedMessageArray = new ArrayList<>();
        public static ArrayList<String> messageHashArray = new ArrayList<>();
        public static ArrayList<String> messageIdArray = new ArrayList<>();
        
        //Method to display Sender and Recipient of all sent messages
    public static String displaySenderAndRecipient() {
        StringBuilder result = new StringBuilder();
        for (Message msg : sentMessages) {
            result.append("Sender: ").append(savedFullName)
                .append("\nRecipient: ").append(msg.recipientNumber)
                .append("\n\n");
        }
        return result.toString();
    }
    
    //Method displaying longest sent message
    public static String displayLongestMessage() {
        if (sentMessages.isEmpty()) return "No messages sent.";
        
        Message longest = sentMessages.get(0);
        for (Message msg : sentMessages) {
            if (msg.content.length() > longest.content.length()) {
                longest = msg;
            }
        }
        return "Longest message:\n" + longest.content;
    }
    
     //Method to search for messageID
    public static String searchMessageId(String id) {
        for (Message msg : sentMessages) {
            if (msg.messageId.equals(id)) {
                return "Recipient: " + msg.recipientNumber + "\nMessage: " + msg.content;
            }
        }
        return "Message ID not found.";
    }

    // Method to search for all messages sent to specific recipient
    public static String searchRecipientMessages(String recipient) {
        StringBuilder result = new StringBuilder();
        for (Message msg : sentMessages) {
            if (msg.recipientNumber.equals(recipient)) {
                result.append("Message: ").append(msg.content).append("\n\n");
            }
        }
        return result.length() > 0 ? result.toString() : "No messages found for this recipient.";
    }

    // Method to delete a message with its message hash
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < sentMessages.size(); i++) {
            if (sentMessages.get(i).hash.equals(hash)) {
                sentMessages.remove(i);
                return "Message deleted successfully.";
            }
        }
        return "Message hash not found.";
    }

    //Method to display report of full details for all messages sent
    public static String displayFullReport() {
        if (sentMessages.isEmpty()) return "No messages sent.";
        
        StringBuilder report = new StringBuilder("MESSAGE REPORT\n\n");
        for (Message msg : sentMessages) {
            report.append("Recipient: ").append(msg.recipientNumber)
                  .append("\nMessage ID: ").append(msg.messageId)
                  .append("\nMessage Hash: ").append(msg.hash)
                  .append("\nMessage: ").append(msg.content)
                  .append("\n\n");
        }
        return report.toString();
    }

        
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         
        //Declare variables
        String FirstName, LastName, Username, Password, PhoneNum;
        
        //ChatApp registration
        System.out.println("==== Join ChatApp! ====");
        
        System.out.println();
        
        System.out.println("Please enter your credentials.");
 
        //Registration: Accept user's information(Name, Surname & PhoneNum)
        System.out.print("Enter your first name: ");
        FirstName = scanner.nextLine();
        
        System.out.print("Enter your last name: ");
        LastName = scanner.nextLine();
        
        savedFullName = FirstName + " " + LastName;
        
        //Get and validate Phone number
        while (true) {
            System.out.print("Enter your phone number: ");
            PhoneNum = scanner.nextLine();
            if (checkCellPhoneNumber(PhoneNum)) {
                savedNum = PhoneNum;
                break;
            }
        }
        
        System.out.println("\n==== Create a ChatApp account. ====");
        
        System.out.println();
        
        //Accept Username from user
        while (true) {
            System.out.print("Enter your username: ");
            Username = scanner.nextLine();
            if(checkUserName(Username)) {
                savedUserN = Username;
                break;
            }
        }    
            
        System.out.println();
        
        //Accept Password from user
        while (true) {
            System.out.print("Enter your password: ");
            Password = scanner.nextLine();
            if (checkPasswordComplexity(Password)) {
                  savedPassW = Password;
                  break;
            }
        }
        
        System.out.println();
        
        System.out.println("Registration complete! Thank you, " + savedFullName + ".");
        
        System.out.println();
        
        //Login to ChatApp
        System.out.println("==== Login to ChatApp. ====\n");
        
        String LoginUserN = "", LoginPassword = "";
        
        while (true) {
            System.out.print("Enter your username: ");
            LoginUserN = scanner.nextLine();
            if (!LoginUserN.equals(savedUserN)) {
            System.out.println("Incorrect username used, please re-enter.");
            } else {
               break;
            }    
        }
        
        while (true) {    
           System.out.print("Enter your password: ");
           LoginPassword = scanner.nextLine();
           if (!LoginPassword.equals(savedPassW)) {
            System.out.println("Incorrect password used, please re-enter.");
           } else {
               break;
           }    
        }
        
        System.out.println();
        
        System.out.println(returnLoginStatus(LoginUserN, LoginPassword));
        
        
        
        
        
        
        
        
        //POE Part 2 - Updated & includes addtitions for Part 3
        

       //Declare variables
        int option = 0, numberOfMessages;
        String rNumber;
        
        //Welcome Page
        JOptionPane.showMessageDialog(null,"Hi " + savedFullName        //add "savedFullName" when done
                                      ,"Welcome to QuickChat!", JOptionPane.INFORMATION_MESSAGE);  
        
        //Menu Creation
        while(option != 3) {
            option = Integer.parseInt(JOptionPane.showInputDialog(null,"""
                                            Menu
                                            1) Send Messages
                                            2) Show recently sent messages
                                            3) Quit"""
                                            , "QuickChat", JOptionPane.INFORMATION_MESSAGE));
            
            //QuickChat Main Menu
            switch (option) {
                case 1:
                    // Get recipient number
                    while (true) {
                        rNumber = JOptionPane.showInputDialog(null, "Enter recipient's number:", "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                        if (checkRecipientCell(rNumber)) {
                            savedReNumber = rNumber;
                            break;
                        }
                    }

                    // Get number of messages to send
                    numberOfMessages = Integer.parseInt(
                            JOptionPane.showInputDialog(null, "Please enter the number of messages you want to send:",
                                    "QuickChat", JOptionPane.INFORMATION_MESSAGE));

                    // Message loop
                    for (int i = 0; i < numberOfMessages; i++) {
                        String message;
                        do {
                            message = JOptionPane.showInputDialog(null, "Enter message #" + (i + 1) + " (Max 250 chars):",
                                    "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                        } while (message.length() > 250);

                        //Method used for the user to either "store", "disregard" or "send" the message
                        SentMessage(message, i + 1);
                    }
                    break;
                    
                    //Changed from "Coming Soon" to "Report Options Menu"
                case 2:int reportOptions = Integer.parseInt(JOptionPane.showInputDialog(null, """
                                                                                              Report Options:
                                                                                              1) Display Sender or Recipient of all messages.
                                                                                              2) Display longest sent message.
                                                                                              3) Search for a message ID.
                                                                                              4) Search for all messages sent to a specific recipient.
                                                                                              5) Delete message using message hash.
                                                                                              6) Display full details of all messages sent.
                                                                                              7) Back to Main Menu""",
                                                                                              "QuickChat", JOptionPane.INFORMATION_MESSAGE));
                        //Report Options Menu
                        switch (reportOptions) {
                            case 1:JOptionPane.showMessageDialog(null, displaySenderAndRecipient());
                                break;
                            case 2:JOptionPane.showMessageDialog(null,displayLongestMessage());
                                break;
                            case 3:String searchMessageId = JOptionPane.showInputDialog(null, "Enter message Id to search.");
                                   JOptionPane.showMessageDialog(null, searchMessageId(searchMessageId));
                                break;
                            case 4:String searchRecipient = JOptionPane.showInputDialog(null, "Enter recipient's number to search.");
                                   JOptionPane.showMessageDialog(null, searchRecipientMessages(searchRecipient));
                                break;
                            case 5:String deleteHash = JOptionPane.showInputDialog(null, "Enter the message hash to delete.");
                                   JOptionPane.showMessageDialog(null, deleteMessageByHash(deleteHash));
                                break;
                            case 6:JOptionPane.showMessageDialog(null, displayFullReport());
                                break;
                            case 7:
                                break;
                            default:JOptionPane.showMessageDialog(null, "Invalid option. Please select any option from 1-6");
                        //End of Report Menu code    
                        }
                        
                    break;
                case 3:JOptionPane.showMessageDialog(null, "Goodbye " + savedFullName                                   //User quitting program
                                                    , "Closing QuickChat", JOptionPane.INFORMATION_MESSAGE); 
                    break;
                default: JOptionPane.showMessageDialog(null, "Invalid option. Please select 1, 2 or 3."
                                                      , "QuickChat", JOptionPane.INFORMATION_MESSAGE);   //Prompt if user enters the wrong Menu Option
                    break;
                    
            }
        }
        
   
    }
    
}

//Reference:
//Farrell, J., 2023. Java programming 10th ed. Boston: Cengage Learning.

//OpenAI.(2025).ChatGPT (April 14 version)[Large language model][online] Available at: <https://chat.openai.com/ >[Accessed 16 April  2025].

//W3Schools, (n.d.). Java while loop. [online] Available at: <https://www.w3schools.com/java/java_while_loop.asp> [Accessed 15 April 2025].

