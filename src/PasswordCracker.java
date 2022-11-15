import java.util.Scanner;
import java.util.HashMap;
import java.io.File;

public class PasswordCracker {
    public static void main (String[] args) {
        /* Type 1 for solving passwords */
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new File("../doc/dictionary.txt")); //reads in the name of the file we want to open
        }
        catch (Exception e) {
            System.exit(0);
            return;
        }

        HashMap<String, String> digestToPassword = new HashMap<String, String>();
        while(fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            
            // run the MD5 function on word, store in string called digest
            String digest = JavaMD5Hash.md5(word); 
            
            // map digest : word
            digestToPassword.put(digest, word);

        }
        System.out.println(digestToPassword.get("cd090bd19b91ceda44ac52b6b996b535"));

    }
}
//test output to see if it is working
//compare file that has passwords
//given the hash of a password, we want to figure out what the plaintext version of that hash string is
//type one is just a word from our dictionary
//type two is password string with dictionary word
//26 + 10 + 5 = 41 potential characters
//read dictionary file first - for every line in dict. text read in word - while loop
//we can use a buffer reader or scanner
//while (fileScanner.hasNextLine());
//String word = fileScanner.readLine();
//we are going to do a loop that finds all permutations of said word - will require nested loops
//four numbers/special characters at a time
//loop for i
//we are given a hash value, we turn the dictionary to hash values, if the hashes line up then the password in English is shown
