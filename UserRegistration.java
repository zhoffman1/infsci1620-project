/** 
 * Program to register users to the system
 * @author  Zachary Hoffman
 */
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.HashMap;

public class UserRegistration {
    public static void main (String[] args) {
        Scanner kbd = new Scanner(System.in);
        boolean exists = false;
        String username;
        // create file to store usernames/passwords (if not already exist)
        
        // if file exists already, read in the hashmap
        // otherwise, create an empty HashMap<String, String>

        // read user input
        do {
            if (exists) {
                System.out.println("Username already exists. Please enter a new username: ");
            }
            else {
                System.out.println("Create username: ");
            }

            username = kbd.nextLine();
            exists = false; // TODO: make this actually check the preexisting file
                            // true if name already exists, false if not
        } while (exists);

        System.out.println("Create password: ");

        // get hash of password
        
        // store username and hashed password in file (username not already exist)

        // can probably store using a hashmap (makes .txt file unreadable)

        System.out.println("Successfully created user " + username + ".");

        // TODO: make the whole program loop so that the user can create multiple users in 1 go
    }
}