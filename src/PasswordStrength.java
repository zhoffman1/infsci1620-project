/** 
 * Program used to evaluate password strength
 * @author  Zachary Hoffman
 * @author  Savee Sok-Coyle
 */
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.HashSet;

public class PasswordStrength {
    public static void main(String[] args) {
        BufferedReader br;
        Scanner kbd = new Scanner(System.in);

        // read in dictionary file
        try {
            br = new BufferedReader(new FileReader(new File("../doc/dictionary.txt")));
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }

        System.out.print("Please enter a password for evaluation: ");
        String password = kbd.nextLine();
        String output = "";
        boolean passwordFound = false;
        
        try {
            while (br.ready()) {
                String word = br.readLine();

                // if password is in dictionary, weak
                if (password.equals(word)) {
                    output = password + " is a weak password";
                    passwordFound = true;
                    break; // break since weak is as low of strength as we go
                }
                // if password is a substring of word in dictionary, moderate
                else if (password.contains(word)) {
                    output = password + " is a moderate password";
                    passwordFound = true;
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }

        // if password is not weak or moderate, it must be strong
        if (!passwordFound) {
            output = password + " is a strong password";
        }

        System.out.println(output);
    }
}