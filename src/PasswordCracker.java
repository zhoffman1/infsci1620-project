import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

public class PasswordCracker {
    public static void main (String[] args) {
        /* Find Time to Crack Passwords */
        long startTime = System.nanoTime();
        boolean passwordFound = false;
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
        ArrayList<String> words = new ArrayList<String>();
        while(fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();

            words.add(word);
            // run the MD5 function on word, store in string called digest
            String digest = JavaMD5Hash.md5(word); 
            
            // map digest : word
            digestToPassword.put(digest, word);

        }
        System.out.println(digestToPassword.get("cd090bd19b91ceda44ac52b6b996b535"));

        checkPassword(words, "f8c6cb91de05c3a815d7ed9fe29181d6");
    }
    /* Type 2 for solving passwords */
    static void checkPassword(ArrayList<String> words, String digestPassword) { //cannot figure out how to check through every iteration
        String[] num = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] spec = new String[]{"@", "#", "$", "%", "&"};
        String specWord = "";
        String specDigest = "";
        long endTime = 0;
        for(String word : words) {
            //need to make loops for special characters, numbers, and characters
            //there are 15 possible iterations and 15 potential characters
            //I am thinking to make an array of all characters possible then for every loop do i++ and then run every character in the array, 
            //then have to do nested loops for every single one
            //1: plain word
            //2: _ word
            //3: word _
            //4: _ _ word
            //5: _ word _
            //6: word _ _
            //below is #2
            for(int i = 0; i < num.length; i++) { //num at front
                specWord = num[i] + word;
                specDigest = JavaMD5Hash.md5(specWord);
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int j = 0; j < spec.length; j++) { //char at front
                specWord = spec[j] + word;
                specDigest = JavaMD5Hash.md5(specWord);
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            //#3
            for(int i = 0; i< num.length; i++) { //num at back
                specWord = word + num[i];
                specDigest = JavaMD5Hash.md5(specWord);
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int j = 0; j < spec.length; j++) { //char at back
                specWord = word + spec[j];
                specDigest = JavaMD5Hash.md5(specWord);
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            //#4
            for(int i = 0; i< num.length; i++) { //num and char at front
                for(int j = 0; j < spec.length; j++){
                    specWord = num[i] + spec[j] + word;
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            } 
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i< num.length; i++) { //num and num at front
                for(int j = 0; j < num.length; j++){
                    specWord = num[i] + num[j] + word;
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            } 
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char and num at front
                for(int j = 0; j < num.length; j++) {
                    specWord = spec[i] + num[j] + word;
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char and char at front
                for(int j = 0; j < spec.length; j++) {
                    specWord = spec[i] + spec[j] + word;
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            //#5
            for(int i = 0; i< num.length; i++) { //num front and num back
                for(int j = 0; j < num.length; j++){
                    specWord = num[i] + word + num[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < num.length; i++) { //num front and char back
                for(int j = 0; j < spec.length; j++) {
                    specWord = num[i] + word + spec[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char front and num back
                for(int j = 0; j < num.length; j++) {
                    specWord = spec[i] + word + num[i];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char front and char back
                for(int j = 0; j < spec.length; j++) {
                    specWord = spec[i] + word + spec[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            //#6
            for(int i = 0; i< num.length; i++) { //num and char at back
                for(int j = 0; j < spec.length; j++){
                    specWord = word + num[i] + spec[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i< num.length; i++) { //num and num at back
                for(int j = 0; j < num.length; j++){
                    specWord = word + num[i] + num[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char and num at back
                for(int j = 0; j < num.length; j++) {
                    specWord = word + spec[i] + num[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
            }
            for(int i = 0; i < spec.length; i++) { //char and char at back
                for(int j = 0; j < spec.length; j++) {
                    specWord = word + spec[i] + spec[j];
                    specDigest = JavaMD5Hash.md5(specWord);
                }
            }
            if (specDigest.equals(digestPassword)) {
                System.out.println("The password is " + specWord);
                endTime = System.nanoTime();
                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                System.exit(0);
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
            for(int w = 1; w <= 4; w++) {
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            if(w ==1) { //num and num and char at front
                                specWord = num[i] + num[j] + spec[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //num and num at front and char at back
                                specWord = num[i] + num[j] + word + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //num at front and num and char at back
                                specWord = num[i] + word + num[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //num and num and char at back
                                specWord = word + num[i] + num[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);         
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
                                specWord = num[i] + spec[j] + spec[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //num and char at front and char at back
                                specWord = num[i] + spec[j] + word + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //num at front and char and char at back
                                specWord = num[i] + word + spec[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //num and char and char at back
                                specWord = word + num[i] + spec[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
                                specWord = num[i] + spec[j] + num[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //num and char at front and num at back
                                specWord = num[i] + spec[j] + word + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //num at front and char and num at back
                                specWord = num[i] + word + spec[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //num and char and num at back
                                specWord = word + num[i] + spec[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
                                specWord = spec[i] + spec[j] + num[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //char and char at front and num at back
                                specWord = spec[i] + spec[j] + word + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //char at front and char and num at back
                                specWord = spec[i] + word + spec[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //char and char and num at back
                                specWord = word + spec[i] + spec[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
                                specWord = spec[i] + num[j] + spec[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //char and num at front and char at back
                                specWord = spec[i] + num[j] + word + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //char at front and num and char at back
                                specWord = spec[i] + word + num[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //char and num and char at back
                                specWord = word + spec[i] + num[j] + spec[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
                                specWord = spec[i] + num[j] + num[k] + word;
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w ==2) { //char and num at front and num at back
                                specWord = spec[i] + num[j] + word + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 3) { //char at front and num and num at back
                                specWord = spec[i] + word + num[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
                            }
                            else if(w == 4) { //char and num and num at back
                                specWord = word + spec[i] + num[j] + num[k];
                                specDigest = JavaMD5Hash.md5(specWord);
                            }
                            if (specDigest.equals(digestPassword)) {
                                System.out.println("The password is " + specWord);
                                endTime = System.nanoTime();
                                System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                System.exit(0);
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
            for(int w = 1; w <= 5; w++) { //num, num, char, char
                for(int i = 0; i< num.length; i++) {
                    for(int j = 0; j < num.length; j++){ 
                        for(int k = 0; k < spec.length; k++){
                            for(int l = 0; l < spec.length; l++) {
                                if(w ==1) { //num and num and char and char at front
                                    specWord = num[i] + num[j] + spec[k] + spec[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //num and num and char at front and char at back
                                    specWord = num[i] + num[j] + spec[k] + word + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //num and num at front and char and char back
                                    specWord = num[i] + num[j] + word + spec[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //num at front and num and char and char at back
                                    specWord = num[i] + word + num[j] + spec[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //num and num and char and char at back
                                    specWord = word + num[i] + num[j] + spec[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
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
                                    specWord = num[i] + spec[j] + spec[k] + num[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //num and char and char at front and num at back
                                    specWord = num[i] + spec[j] + spec[k] + word + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //num and char at front and char and num back
                                    specWord = num[i] + spec[j] + word + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //num at front and char and char and num at back
                                    specWord = num[i] + word + spec[j] + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //num and char and char and num at back
                                    specWord = word + num[i] + spec[j] + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
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
                                    specWord = num[i] + spec[j] + num[k] + spec[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //num and char and num at front and char at back
                                    specWord = num[i] + spec[j] + num[k] + word + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //num and char at front and num and char back
                                    specWord = num[i] + spec[j] + word + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //num at front and char and num and char at back
                                    specWord = num[i] + word + spec[j] + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //num and char and num and char at back
                                    specWord = word + num[i] + spec[j] + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
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
                                    specWord = spec[i] + spec[j] + num[k] + num[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //char and char and num at front and num at back
                                    specWord = spec[i] + spec[j] + num[k] + word + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //char and char at front and num and num back
                                    specWord = spec[i] + spec[j] + word + num[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //char at front and char and num and num at back
                                    specWord = spec[i] + word + spec[j] + num[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //char and char and num and num at back
                                    specWord = word + spec[i] + spec[j] + num[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
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
                                    specWord = spec[i] + num[j] + spec[k] + num[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //char and num and char at front and num at back
                                    specWord = spec[i] + num[j] + spec[k] + word + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //char and num at front and char and num back
                                    specWord = spec[i] + num[j] + word + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //char at front and num and char and num at back
                                    specWord = spec[i] + word + num[j] + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //char and num and char and num at back
                                    specWord = word + spec[i] + num[j] + spec[k] + num[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
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
                                    specWord = spec[i] + num[j] + num[k] + spec[l] + word;
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w ==2) { //char and num and num at front and char at back
                                    specWord = spec[i] + num[j] + num[k] + word + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 3) { //char and num at front and num and char back
                                    specWord = spec[i] + num[j] + word + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 4) { //char at front and num and num and char at back
                                    specWord = spec[i] + word + num[j] + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                                else if(w == 5) { //char and num and num and char at back
                                    specWord = word + spec[i] + num[j] + num[k] + spec[l];
                                    specDigest = JavaMD5Hash.md5(specWord);
                                }
                                if (specDigest.equals(digestPassword)) {
                                    System.out.println("The password is " + specWord);
                                    endTime = System.nanoTime();
                                    System.out.println("The total time to find the password is " + endTime/1000000000 + " seconds.");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        //return(endTime);
    }
}
//test output to see if it is working - done
//compare file that has passwords - done
//given the hash of a password, we want to figure out what the plaintext version of that hash string is - done
//type one is just a word from our dictionary - done
//type two is password string with dictionary word - done
//26 + 10 + 5 = 41 potential characters - no, there are no letters 
//in the word so it would just be 0-9 and 5 characters = 15 potential characters
//also remember only 2 special characters and 2 numbers
//read dictionary file first - for every line in dict. text read in word - while loop
//we can use a buffer reader or scanner
//while (fileScanner.hasNextLine());
//String word = fileScanner.readLine();
//we are going to do a loop that finds all permutations of said word - will require nested loops
//four numbers/special characters at a time
//loop for i
//we are given a hash value, we turn the dictionary to hash values, if the hashes line up then the password in English is shown
