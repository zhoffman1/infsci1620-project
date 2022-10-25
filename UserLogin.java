/** 
 * Program for users to log in to the system
 * @author  Zachary Hoffman
 */
import java.io.File;
import java.util.Scanner;

public class UserLogin {
    public static void main (String[] args) {
        Scanner kbd = new Scanner(System.in); // keyboard reader
        // need something to read file in (depends on storing as text or hashmap)

        // ask for username
        System.out.println("Username: ");
        String username = kbd.nextLine();

        // ask for password
        System.out.println("Password: ");
        String password = kbd.nextLine();

        // check if username exists
        
            // hash the supplied password

            // see if they match
    }
}