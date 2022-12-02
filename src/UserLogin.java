/** 
 * Program for users to log in to the system
 * @author  Zachary Hoffman
 * @author  Savee Sok-Coyle
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
        File file = new File("../doc/userinfo.txt");

        // check if file exists
        if (!file.exists()) {
            System.out.println("File does not exist. Exiting program.");
            System.exit(0);
        }

        // read hashmap in
        HashMap<String, String> hmap = readFile(file);

        // get user info
        String[] userInfo = getUserInfo(kbd);

        // verify user
        boolean correct = verifyUser(userInfo[0], userInfo[1], hmap);

        // print accordingly
        if (correct) {
            System.out.println("You are now logged in as user " + userInfo[0]);
        }
        else {
            System.out.println("Username or password was incorrect. Run program to try again.");
        }
    }

    /** 
     * Reads a hashmap in from a stored file, returns the hashmap
     * To be removed. readFile() instead
     * 
     * @param   filename path of file to read in
     * @return  HashMap<String, String> mapping usernames to passwords
     *
     */
    public static HashMap<String, String> readFromFile(String filename) {
        HashMap<String, String> hmap;

        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            // read info from file, cast to hashmap
            hmap = (HashMap<String,String>)ois.readObject();
            ois.close();
            fis.close();

            return hmap;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Ask for user to input their username and password, return an array 
     * containing their inputs
     *
     * @param   kbd scanner reading from keyboard (System.in)
     * @return  String array containing the username input at index 0 and 
     *          password input at index 1
     */
    public static String[] getUserInfo(Scanner kbd) {
        String arr[] = new String[2];

        // ask for username
        System.out.print("Username: ");
        String username = kbd.nextLine();

        // ask for password
        System.out.print("Password: ");
        String password = kbd.nextLine();

        // store username and password in array
        arr[0] = username;
        arr[1] = password;

        return arr;
    }

    /** 
     * Verifies whether the username:password mapping exists
     *
     * @param   username username to check 
     * @param   password password to check
     * @param   hmap HashMap<String, String> to refer username and password to    
     * @return  true if user exists and supplied the correct password, false otherwise
     */
    public static boolean verifyUser(String username, String password, HashMap<String, String> hmap) {
        String hPassword = JavaMD5Hash.md5(password); // hashed password using md5
        
        // if the username exists and the password is a correct match, return true
        if (hmap.containsKey(username) && hmap.get(username).equals(hPassword)) {
            return true;
        }

        // either username doesn't exist or the password is incorrect
        return false;
    }

     /**
     * Method to read a file in as a HashMap<String, String>
     *
     * @param   file file to read in from
     * @return  HashMap<String, String> input of the file
     */
    public static HashMap<String, String> readFile(File file) {
        HashMap<String, String> hmap = new HashMap<String, String>();

        try {
            // check if file exists, if not exit
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            // read info from file, cast to hashmap
            hmap = (HashMap<String,String>)ois.readObject(); 
            ois.close();
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
        
        return hmap;
    }
}