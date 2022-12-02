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
        long startTime = System.nanoTime();
        long endTime = 0;
        long totalTime = 0;
        boolean passwordFound = false;
        boolean typeOne = false;
        boolean typeTwo = false;

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

        checkPassword(words, password, startTime); //function for Type 2 of solving passwords
    }
    /* Type 2 for solving passwords */
    static void checkPassword(ArrayList<String> words, String password, long startTime) { 
        String[] num = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; //creates an array of the numbers
        String[] spec = new String[]{"@", "#", "$", "%", "&"}; //creates an array of the special charaters
        long endTime = 0;
        long totalTime = 0;
        for(String word : words) {
            //there are 15 possible iterations and 15 potential characters
            //1: plain word (Type 1)
            //2: _ word
            //3: word _
            //4: _ _ word
            //5: _ word _
            //6: word _ _
            //below is #2
            for(int i = 0; i < num.length; i++) { //num at front
                String specWord = num[i] + word;
                String specDigest = JavaMD5Hash.md5(specWord); //creates the hash of the special word
                if (specDigest.equals(password)) { //checks if the hash given matches this hash
                    System.out.println("The password is " + specWord);
                    endTime = System.nanoTime(); //returns how long it takes for the program to run
                    totalTime = endTime - startTime;
                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                    System.exit(0); //exits the program
                }
            }
           for(int j = 0; j < spec.length; j++) { //char at front
                String specWord = spec[j] + word;
                String specDigest = JavaMD5Hash.md5(specWord);
                if (specDigest.equals(password)) {
                    System.out.println("The password is " + specWord);
                    endTime = System.nanoTime();
                    totalTime = endTime - startTime;
                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                    System.exit(0);
                }
            }
            //#3
            for(int i = 0; i< num.length; i++) { //num at back
                String specWord = word + num[i];
                String specDigest = JavaMD5Hash.md5(specWord);
                if (specDigest.equals(password)) {
                    System.out.println("The password is " + specWord);
                    endTime = System.nanoTime();
                    totalTime = endTime - startTime;
                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                    System.exit(0);
                }
            }
            for(int j = 0; j < spec.length; j++) { //char at back
                String specWord = word + spec[j];
                String specDigest = JavaMD5Hash.md5(specWord);
                if (specDigest.equals(password)) {
                    System.out.println("The password is " + specWord);
                    endTime = System.nanoTime();
                    totalTime = endTime - startTime;
                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                    System.exit(0);
                }
            }
            //#4
            for(int i = 0; i< num.length; i++) { //num and char at front
                for(int j = 0; j < spec.length; j++){
                    String specWord = num[i] + spec[j] + word;
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            } 
            for(int i = 0; i< num.length; i++) { //num and num at front
                for(int j = 0; j < num.length; j++){
                    String specWord = num[i] + num[j] + word;
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char and num at front
                for(int j = 0; j < num.length; j++) {
                    String specWord = spec[i] + num[j] + word;
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char and char at front
                for(int j = 0; j < spec.length; j++) {
                    String specWord = spec[i] + spec[j] + word;
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            //#5
            for(int i = 0; i< num.length; i++) { //num front and num back
                for(int j = 0; j < num.length; j++){
                    String specWord = num[i] + word + num[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < num.length; i++) { //num front and char back
                for(int j = 0; j < spec.length; j++) {
                    String specWord = num[i] + word + spec[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char front and num back
                for(int j = 0; j < num.length; j++) {
                    String specWord = spec[i] + word + num[i];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char front and char back
                for(int j = 0; j < spec.length; j++) {
                    String specWord = spec[i] + word + spec[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            //#6
            for(int i = 0; i< num.length; i++) { //num and char at back
                for(int j = 0; j < spec.length; j++){
                    String specWord = word + num[i] + spec[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i< num.length; i++) { //num and num at back
                for(int j = 0; j < num.length; j++){
                    String specWord = word + num[i] + num[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char and num at back
                for(int j = 0; j < num.length; j++) {
                    String specWord = word + spec[i] + num[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
            for(int i = 0; i < spec.length; i++) { //char and char at back
                for(int j = 0; j < spec.length; j++) {
                    String specWord = word + spec[i] + spec[j];
                    String specDigest = JavaMD5Hash.md5(specWord);
                    if (specDigest.equals(password)) {
                        System.out.println("The password is " + specWord);
                        endTime = System.nanoTime();
                        totalTime = endTime - startTime;
                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                        System.exit(0);
                    }
                }
            }
        }
            //#7: _ _ _ word
            //#8: _ _ word _
            //#9: _ word _ _
            //#10: word _ _ _

            //a possible way to solve repeatedness is to have a loop and move the word position each time
            //Word#1 = _ _ _ word
            //Word#2 = _ _ word _
            //Word#3 = _ word _ _
            //Word#4 = word _ _ _

            //#7 - #10 (#1 - num, num, char #2 - num, char, char #3 - num, char, num #4 - char, char, num #5 - char, num, char #6 - char, num, num)
        for(String word : words) {
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            if(w ==1) { //num and num and char at front
                                String specWord = num[i] + num[j] + spec[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //num and num at front and char at back
                                String specWord = num[i] + num[j] + word + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //num at front and num and char at back
                                String specWord = num[i] + word + num[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //num and num and char at back
                                String specWord = word + num[i] + num[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);  
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }       
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < spec.length; j++){
                        for(int k = 0; k < spec.length; k++){
                            if(w ==1) { //num and char and char at front
                                String specWord = num[i] + spec[j] + spec[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //num and char at front and char at back
                                String specWord = num[i] + spec[j] + word + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //num at front and char and char at back
                                String specWord = num[i] + word + spec[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //num and char and char at back
                                String specWord = word + num[i] + spec[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< num.length; i++) { //num and char and num at front
                    for(int j = 0; j < spec.length; j++){
                        for(int k = 0; k < num.length; k++){
                            if(w ==1) { //num and char and num at front
                                String specWord = num[i] + spec[j] + num[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //num and char at front and num at back
                                String specWord = num[i] + spec[j] + word + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //num at front and char and num at back
                                String specWord = num[i] + word + spec[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //num and char and num at back
                                String specWord = word + num[i] + spec[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< spec.length; i++) { 
                    for(int j = 0; j < spec.length; j++){
                        for(int k = 0; k < num.length; k++){
                            if(w ==1) { //char and char and num at front
                                String specWord = spec[i] + spec[j] + num[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //char and char at front and num at back
                                String specWord = spec[i] + spec[j] + word + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //char at front and char and num at back
                                String specWord = spec[i] + word + spec[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //char and char and num at back
                                String specWord = word + spec[i] + spec[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< spec.length; i++) { //char and num and char at front
                    for(int j = 0; j < num.length; j++){
                        for(int k = 0; k < spec.length; k++){
                            if(w ==1) { //char and num and char at front
                                String specWord = spec[i] + num[j] + spec[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //char and num at front and char at back
                                String specWord = spec[i] + num[j] + word + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //char at front and num and char at back
                                String specWord = spec[i] + word + num[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //char and num and char at back
                                String specWord = word + spec[i] + num[j] + spec[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< spec.length; i++) { //char and num and num at front
                    for(int j = 0; j < num.length; j++){
                        for(int k = 0; k < num.length; k++){
                            if(w ==1) { //char and num and num at front
                                String specWord = spec[i] + num[j] + num[k] + word;
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w ==2) { //char and num at front and num at back
                                String specWord = spec[i] + num[j] + word + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 3) { //char at front and num and num at back
                                String specWord = spec[i] + word + num[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                            else if(w == 4) { //char and num and num at back
                                String specWord = word + spec[i] + num[j] + num[k];
                                String specDigest = JavaMD5Hash.md5(specWord);
                                if (specDigest.equals(password)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    totalTime = endTime - startTime;
                                    System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }

            //#11: _ _ _ _ word
            //#12: _ _ _ word _
            //#13: _ _ word _ _
            //#14: _ word _ _ _
            //#15: word _ _ _ _
            //#11 - #15 (#1 - num, num, char, char #2 - num, char, char, num #3 - num, char, num, char #4 - char, char, num, num)
            //(#5 - char, num, char, num #6 - char, num, num, char)
        for(String word : words) {
            for(int w = 1; w <= 5; w++) { //num, num, char, char
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            for(int l = 0; l < spec.length; l++) {
                                if(w ==1) { //num and num and char and char at front
                                    String specWord = num[i] + num[j] + spec[k] + spec[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //num and num and char at front and char at back
                                    String specWord = num[i] + num[j] + spec[k] + word + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //num and num at front and char and char back
                                    String specWord = num[i] + num[j] + word + spec[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //num at front and num and char and char at back
                                    String specWord = num[i] + word + num[j] + spec[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //num and num and char and char at back
                                    String specWord = word + num[i] + num[j] + spec[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 5; w++) { //num, char, char, num
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < spec.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            for(int l = 0; l < num.length; l++) {
                                if(w ==1) { //num and char and char and num at front
                                    String specWord = num[i] + spec[j] + spec[k] + num[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //num and char and char at front and num at back
                                    String specWord = num[i] + spec[j] + spec[k] + word + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //num and char at front and char and num back
                                    String specWord = num[i] + spec[j] + word + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //num at front and char and char and num at back
                                    String specWord = num[i] + word + spec[j] + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //num and char and char and num at back
                                    String specWord = word + num[i] + spec[j] + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 5; w++) { //num, char, num, char
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < spec.length; j++){ 
                        for(int k = 0; k < num.length; k++){
                            for(int l = 0; l < spec.length; l++) {
                                if(w ==1) { //num and char and num and char at front
                                    String specWord = num[i] + spec[j] + num[k] + spec[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //num and char and num at front and char at back
                                    String specWord = num[i] + spec[j] + num[k] + word + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //num and char at front and num and char back
                                    String specWord = num[i] + spec[j] + word + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //num at front and char and num and char at back
                                    String specWord = num[i] + word + spec[j] + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //num and char and num and char at back
                                    String specWord = word + num[i] + spec[j] + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 5; w++) { //char, char, num, num
                for(int i = 0; i< spec.length; i++) {
                    for(int j = 0; j < spec.length; j++){ 
                        for(int k = 0; k < num.length; k++){
                            for(int l = 0; l < num.length; l++) {
                                if(w ==1) { //char and char and num and num at front
                                    String specWord = spec[i] + spec[j] + num[k] + num[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //char and char and num at front and num at back
                                    String specWord = spec[i] + spec[j] + num[k] + word + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //char and char at front and num and num back
                                    String specWord = spec[i] + spec[j] + word + num[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //char at front and char and num and num at back
                                    String specWord = spec[i] + word + spec[j] + num[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //char and char and num and num at back
                                    String specWord = word + spec[i] + spec[j] + num[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 5; w++) { //char, num, char, num
                for(int i = 0; i< spec.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            for(int l = 0; l < num.length; l++) {
                                if(w ==1) { //char and num and char and num at front
                                    String specWord = spec[i] + num[j] + spec[k] + num[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //char and num and char at front and num at back
                                    String specWord = spec[i] + num[j] + spec[k] + word + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //char and num at front and char and num back
                                    String specWord = spec[i] + num[j] + word + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //char at front and num and char and num at back
                                    String specWord = spec[i] + word + num[j] + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //char and num and char and num at back
                                    String specWord = word + spec[i] + num[j] + spec[k] + num[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int w = 1; w <= 5; w++) { //char, num, num, char
                for(int i = 0; i< spec.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < num.length; k++){
                            for(int l = 0; l < spec.length; l++) {
                                if(w ==1) { //char and num and num and char at front
                                    String specWord = spec[i] + num[j] + num[k] + spec[l] + word;
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w ==2) { //char and num and num at front and char at back
                                    String specWord = spec[i] + num[j] + num[k] + word + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 3) { //char and num at front and num and char back
                                    String specWord = spec[i] + num[j] + word + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 4) { //char at front and num and num and char at back
                                    String specWord = spec[i] + word + num[j] + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                                else if(w == 5) { //char and num and num and char at back
                                    String specWord = word + spec[i] + num[j] + num[k] + spec[l];
                                    String specDigest = JavaMD5Hash.md5(specWord);
                                    if (specDigest.equals(password)) {
                                        System.out.println("The password is " + specWord);
                                        endTime = System.nanoTime();
                                        totalTime = endTime - startTime;
                                        System.out.println("The total time to find the password is " + totalTime/1000000000 + " seconds.");
                                        System.exit(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}