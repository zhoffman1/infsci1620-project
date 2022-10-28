/** 
 * Program for users to log in to the system
 * @author  Zachary Hoffman
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class UserLogin {
    public static void main (String[] args) {
        Scanner kbd = new Scanner(System.in);
        // read hashmap in

        // get user info
        String[2] userInfo = getUserInfo(kbd);
        
        // check if username exists

        
            // hash the supplied password

            // see if they match
    }

    /**
     * Ask for user to input their username and password, return an array 
     * containing their inputs
     *
     * @param   kbd scanner reading from keyboard (System.in)
     * @return  String array containing the username input at index 0 and 
     *          password input at index 1
     *
     */
    public static String[] getUserInfo(Scanner kbd) {
        String arr[] = new String[2];

        // ask for username
        System.out.println("Username: ");
        String username = kbd.nextLine();

        // ask for password
        System.out.println("Password: ");
        String password = kbd.nextLine();

        // store username and password in array
        arr[0] = username;
        arr[1] = password;

        return arr;
    }

    // check whether this user actually exists and if their password is valid
    public static boolean verifyUser(String username, String password, HashMap<String> hmap) {
        
    }
}