
package st10276263.poe.p1;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

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

            if (messageOptions == 1) {
                JOptionPane.showMessageDialog(null, newMessage,
                        "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                return "Sent";
            } else {
                JOptionPane.showMessageDialog(null, "Message stored (not sent).",
                        "QuickChat", JOptionPane.INFORMATION_MESSAGE);
                return "Stored";
            }
        } else if (messageOptions == 3) {
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
        
        
        
        
        
        
        
        
        //POE Part 2
        

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
                case 2:JOptionPane.showMessageDialog(null," \"Coming Soon\" "                           //  Feature has not yet been implemented
                                                    , "QuickChat", JOptionPane.INFORMATION_MESSAGE); 
                    break;
                case 3:JOptionPane.showMessageDialog(null, "Bye bye!"                                   //User quitting program
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

