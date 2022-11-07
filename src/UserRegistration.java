/** 
 * Program to register users to the system
 * @author  Zachary Hoffman
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class UserRegistration {
    public static void main (String[] args) {
        boolean success = false;
        File file;
        HashMap<String, String> userInfo;
        Scanner kbd = new Scanner(System.in);

        file = openFile("../doc/userinfo.txt"); 
        userInfo = readFile(file);

        do {
            // create the user and ask to continue
            userInfo = createUser(userInfo);
            System.out.print("Type 'y' to add more users: ");
        } while (kbd.nextLine().toLowerCase().equals("y"));

        System.out.println("Exiting program");
    }

    /**
     * Create a file with provided filename
     * 
     * @param   filename path of file to create
     * @return  file created in method
     */
    public static File createFile(String filename) {
        File file;
        
        try {
            file = new File(filename);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1); // can probably get rid of the system exit or return
            return null;
        }

        return file;
    }
    
    /**
     * Method to open a file
     *
     * @param   filename a string containing the path of the file to be opened
     * @return  file that was opened
     */
    public static File openFile(String filename) {
        File file;

        // create file to store usernames/passwords (if not already exist)
        try {
            file = new File(filename);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1); // can probably get rid of the system exit or return
            return null;
        }

        return file;
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
            if (file.createNewFile()) {
                System.out.println("Created file");
                hmap = new HashMap<String, String>();
            }
            else {
                System.out.println("The file already exists, overwriting");
                
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                hmap = (HashMap<String,String>)ois.readObject(); // read info from file, cast to hashmap
                ois.close();
                fis.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1); // can probably get rid of the system exit or return
            return null;
        }
        
        return hmap;
    }

    /**
     * Create a user for a hashmap. Return the updated hashmap
     *
     * @param   userInfo hashmap to create user on
     * @return  updated version of input HashMap<String, String>
     */
    public static HashMap<String, String> createUser(HashMap<String, String> userInfo) {
        boolean userExists = false;
        String username;
        Scanner kbd = new Scanner(System.in);

        // read user input
        do {
            if (userExists) {
                System.out.print("Username already exists. Please enter a new username: ");
            }
            else {
                System.out.print("Create username: ");
            }

            username = kbd.nextLine();

            userExists = userInfo.containsKey(username); 
        } while (userExists);

        // create password
        System.out.print("Create password: ");
        String password = kbd.nextLine();
        String hashedPassword = JavaMD5Hash.md5(password); // get hash of password

        // put username and hashed password in hashmap
        userInfo.put(username, hashedPassword);
        System.out.println("Successfully created user " + username + ".");

        File file = openFile("../doc/userinfo.txt"); 
        File readableFile = openFile("../doc/password.txt");

        boolean success = writeObjectFile(file, userInfo); // write object
        success = writeReadableFile(readableFile, username, hashedPassword) && success; // write readable

        // return whether writing was successful
        if (success) {
            System.out.println("Successfully wrote to file");
        }
        else {
            System.out.println("Error occurred while writing to file");
            System.exit(1);
        }

        // return the modified hashmap
        return userInfo;
    }

    /**
     * Write hashmap object to file
     *
     * @param   file file to write out to
     * @param   hmap hashmap to write to file
     * @return  true if successfully wrote to file, false if not
     */
    public static boolean writeObjectFile(File file, HashMap<String, String> hmap) {
        // write hashmap to file
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap);
            oos.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            // System.exit(1);
            return false;
        }

        return true;
    }

    /**
     * Write contents of hashmap out to a readable file
     *
     * @param   file file to write to
     * @param   username username to write out
     * @param   hashedPassword hashed password of the username
     * @return  true if succesfully wrote to file, false if not
     *
     */
    public static boolean writeReadableFile(File file, String username, String hashedPassword) {
        // write username and password out to file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            pw.write(username + " " + hashedPassword + "\n");
            pw.close(); 
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1); // might need system exit here, probably not
            return false;
        }

        return true;
    }
}