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
        // TODO: check if file eixsts. If not, exit pgm
        //       read in from the hash mapped file, not the readable one
        File file = new File("../doc/userinfo.txt"); 

        // read hashmap in
        HashMap<String, String> hmap = readFile(file);

        // get user info
        String[] userInfo = getUserInfo(kbd);

        do {
            // verify user

        }
    }

    /** 
     * Reads a hashmap in from a stored file, returns the hashmap
     * 
     * @param   filename path of file to read in
     * @return  HashMap<String, String> mapping usernames to passwords
     *
     */
    public static HashMap<String, String> readFromFile(String filename) {
        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            hmap = (HashMap<String,String>)ois.readObject(); // read info from file, cast to hashmap
            ois.close();
            fis.close();
        }
        
        return null;
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

    /** 
     * Verifies whether the username:password mapping exists
     *
     * @param   username username to check 
     * @param   password password to check
     * @param   hmap HashMap<String, String> to refer username and password to    
     * @return  true if user exists and supplied the correct password, false otherwise
     */
    public static boolean verifyUser(String username, String password, HashMap<String> hmap) {
        String hPassword = JavaMD5Hash.md5(password); // hashed password using md5
        
        // if the username exists and the password is a correct match, return true
        if (hmap.containsKey(username) && hmap.get(username).equals(hPassword)) {
            return true;
        }

        // otherwise, either username doesn't exist or the password is incorrect
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
            hmap = (HashMap<String,String>)ois.readObject(); // read info from file, cast to hashmap
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