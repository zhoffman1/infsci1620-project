/**
 * Program to crack a password with a given hash value
 * @author Zachary Hoffman
 * @author Savee Sok-Coyle
 */
import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

public class PasswordCracker {
    public static void main (String[] args) {
        Scanner kbd = new Scanner(System.in);
        
        /* Type 1 for solving passwords */
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(new File("../doc/dictionary.txt")); //reads in the dictionary file
        }
        catch (Exception e) {
            System.exit(0);
            return;
        }

        System.out.print("Please enter the MD5 hash of a password: ");
        String password = kbd.nextLine();
        long startTime = System.nanoTime(); //gets the time when user inserts the hash of the password
        long endTime = 0;
        long totalTime = 0;
        boolean passwordFound = false;

        HashMap<String, String> digestToPassword = new HashMap<String, String>(); //Hashmap in order to match word with hash
        ArrayList<String> words = new ArrayList<String>(); //holds the all of the words in the dictionary
        while(fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();

            words.add(word);
            // run the MD5 function on word, store in string called digest
            String digest = JavaMD5Hash.md5(word); 
            if(password.equals(digest)) { //checks if the password matches the hash
                passwordFound = true;
            }
            
            // map digest : word
            digestToPassword.put(digest, word);

        }
        if(passwordFound) {
            System.out.println("The password is " +
            digestToPassword.get(password)); //input a specific hash out and output the plaintext password match
            endTime = System.nanoTime(); //returns how long it takes for the program to run
            totalTime = endTime - startTime;
            System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
            System.exit(0); //exits the program
        }
        else {
            System.out.println("The password is not a type 1 password. Now checking type 2 passwords...");
        }

        checkPassword(words, password, startTime); //function for Type 2 of solving passwords

        if(passwordFound == false) {
            System.out.println("The password is not in the system.");
        }
    }
    /* Type 2 for solving passwords */
    static void checkPassword(ArrayList<String> words, String password, long startTime) {
        String[] num = new String[]{"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; //creates an array of the numbers
        String[] spec = new String[]{"", "@", "#", "$", "%", "&"}; //creates an array of the special charaters
        long endTime = 0;
        long totalTime = 0;
        boolean passwordFound = false;
        for(String word : words) {
            for(int i = 0; i< num.length && !passwordFound; i++) { //sets num1
                for(int j = 0; j < num.length && !passwordFound; j++){  //sets num2
                    for(int k = 0; k < spec.length && !passwordFound; k++){ //sets spec1
                        for(int l = 0; l < spec.length && !passwordFound; l++) { //sets spec2

                            /*
                             * All possible ways of the password existing
                             * 
                             * 1: num1 + num2 + spec1 + spec2 + word
                             * 2: num1 + spec1 + num2 + spec2 + word
                             * 3: num1 + spec1 + spec2 + num2 + word
                             * 4: spec1 + num1 + spec2 + num2 + word
                             * 5: spec1 + spec2 + num1 + num2 + word
                             * 6: spec1 + num1 + num2 + spec2 + word - 30
                             */

                            String specWord = num[i] + num[j] + spec[k] + spec[l] + word; //case #1
                            String specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + num[j] + spec[l] + word; //case #2
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + spec[l] + num[j] + word; //case #3
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + spec[l] + num[j] + word; //case #4
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + spec[l] + num[i] + num[j] + word; //case #5
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + num[j] + spec[l] + word; //case #6
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            //word in fourth
                            specWord = num[i] + num[j] + spec[k] + word + spec[l]; //case #7
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + num[j] + word + spec[l]; //case #8
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + spec[l] + word + num[j]; //case #9
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + spec[l] + word + num[j]; //case #10
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + spec[l] + num[i] + word + num[j]; //case #11
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + num[j] + word + spec[l]; //case #12
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                        
                            //word in middle
                            specWord = num[i] + num[j] + word + spec[k] + spec[l]; //case #13
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + word + num[j] + spec[l]; //case #14
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + spec[k] + word + spec[l] + num[j]; //case #15
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + word + spec[l] + num[j]; //case #16
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + spec[l] + word + num[i] + num[j]; //case #17
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + num[i] + word + num[j] + spec[l]; //case #18
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            //word in second
                            specWord = num[i] + word + num[j] + spec[k] + spec[l]; //case #19
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + word + spec[k] + num[j] + spec[l]; //case #20
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = num[i] + word + spec[k] + spec[l] + num[j] + word; //case #21
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + word + num[i] + spec[l] + num[j]; //case #22
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + word + spec[l] + num[i] + num[j]; //case #23
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = spec[k] + word + num[i] + num[j] + spec[l]; //case #24
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            //word in first
                            specWord = word + num[i] + num[j] + spec[k] + spec[l]; //case #25
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = word + num[i] + spec[k] + num[j] + spec[l]; //case #26
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = word + num[i] + spec[k] + spec[l] + num[j]; //case #27
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = word + spec[k] + num[i] + spec[l] + num[j]; //case #28
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = word + spec[k] + spec[l] + num[i] + num[j]; //case #29
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                            specWord = word + spec[k] + num[i] + num[j] + spec[l] + word; //case #30
                            specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                                    System.out.println("The password is " + specWord);
                                    passwordFound = true;
                                    break;
                                }
                        }
                    }
                }
            }
        }
        if(passwordFound == true)
        {
            endTime = System.nanoTime(); //returns how long it takes for the program to run
            totalTime = endTime - startTime;
            System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
            System.exit(0); //exits the program
        }
        if(passwordFound == false) {
            System.out.println("The password is not in the system.");
            System.exit(0);
        };
                
    }
}