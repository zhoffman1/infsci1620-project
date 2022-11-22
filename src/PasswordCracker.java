import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class PasswordCracker {
    public static void main (String[] args) {
        /* Type 1 for solving passwords */
        Scanner fileScanner; // change to bufferedReader later
        
        try {
            fileScanner = new Scanner(new File("../doc/dictionary.txt")); //reads in the name of the file we want to open
        }
        catch (Exception e) {
            System.exit(0);
            return;
        }

        HashMap<String, String> digestToPassword = new HashMap<String, String>();
        
        // change this to a buffered reader later
        while(fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            
            // run the MD5 function on word, store in string called digest
            String digest = JavaMD5Hash.md5(word); 
            
            // map digest : word
            digestToPassword.put(digest, word);
        }

        // testing
        System.out.println(digestToPassword.get("cd090bd19b91ceda44ac52b6b996b535"));

    }

    public static String findTypeTwo(HashMap<String, String> hmap, String word) {
        String[] numbers = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] specials = {"", "@", "#", "$", "%", "&"};
        String output = null;
        for (String num1 : numbers) {
            for (String num2 : numbers) {
                for (String spec1 : specials) {
                    for (String spec2 : specials) {
                        String[] permutations = permute(num1 + num2 + spec1 + spec2);

                        for (String permutation : permutations) {
                            String combine = "";
                            
                            // 4:0
                            combine = permutation + word;
                            if (hmap.containsKey(JavaMD5Hash.md5(combine))) {
                                output = hmap.get(combine);
                                return output;
                            }
                            
                            // 3:1 TODO (how to deal with empty string?)
                            combine = permutation.substring(0,3) + word + permutation.charAt(3);
                            if (hmap.containsKey(JavaMD5Hash.md5(combine))) {
                                output = hmap.get(combine);
                                return output;
                            }

                            // 2:2 TODO (how to deal with empty string?)
                            combine = permutation + word;
                            if (hmap.containsKey(JavaMD5Hash.md5(combine))) {
                                output = hmap.get(combine);
                                return output;
                            }

                            // 1:3 TODO (how to deal with empty string?)
                            combine = permutation + word;
                            if (hmap.containsKey(JavaMD5Hash.md5(combine))) {
                                output = hmap.get(combine);
                                return output;
                            }

                            // 0:4
                            combine = word + permutation;
                            if (hmap.containsKey(JavaMD5Hash.md5(combine))) {
                                output = hmap.get(combine);
                                return output;
                            }
                        }
                    }
                }
            }
        }

        return output;
    }

    // create a string array of all permutations of the input string
    public static String[] permute(String stringToPermute) {
        String[] permutations;

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
