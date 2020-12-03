package JavaPuzzles;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*******
 *   2020:JavaPuzzles
 *   File: Day2
 *   Created by: Melissa Melaugh
 *   Created on: 02/12/2020
 *   Updated on: 02/12/2020
 *   Project Description: //TODO
 *******/
public class Day2 {
    public static void main(String[] args) {
        String[] passwords = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        validPasswords1(passwords);
        validPasswords2(passwords);

        System.out.println("\n=============================================\n");

        File file = new File("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\Passwords.txt");
        passwords = readPasswords(file);
        validPasswords1(passwords);
        validPasswords2(passwords);
    }

    public static void validPasswords1(String[] passwords){
        int valid = 0;
        for(String pass : passwords){
            String[] splitPassword = pass.split(":");
            String[] full_rule = splitPassword[0].split(" ");
            String[] amount = full_rule[0].split("-");
            int minimum = Integer.parseInt(amount[0]);
            int maximum = Integer.parseInt(amount[1]);
            String rule = full_rule[1];
            String password = splitPassword[1];

            int count = 0;
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == rule.charAt(0)) {
                    count++;
                }
            }

            if(count >= minimum && count <= maximum){
                valid++;
            }
        }
        System.out.println(String.format("There are %d valid rule 1 passwords", valid));
    }

    public static void validPasswords2(String[] passwords){
        int valid = 0;
        for(String pass : passwords){
            String[] splitPassword = pass.split(":");
            String[] full_rule = splitPassword[0].split(" ");
            String[] amount = full_rule[0].split("-");
            int containedAt = Integer.parseInt(amount[0]);
            int notContainedAt = Integer.parseInt(amount[1]);
            char rule = full_rule[1].charAt(0);
            char[] password = splitPassword[1].toCharArray();

            boolean hasCharacterPos1 = password[containedAt] == rule;
            boolean notCharacterPos2 = password[notContainedAt] != rule;

            if(hasCharacterPos1 && notCharacterPos2){
                valid++;
            } else if(!hasCharacterPos1 && !notCharacterPos2){
                valid++;
            }
        }
        System.out.println(String.format("There are %d valid rule 2 passwords", valid));
    }

    public static String[] readPasswords(File file){
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<String> passwordsArray = new ArrayList<String>();
            while (scanner.hasNext()) {
                passwordsArray.add(scanner.nextLine());
            }

            String[] passwords = new String[passwordsArray.size()];
            for(int i = 0; i < passwordsArray.size(); i++){
                passwords[i] = passwordsArray.get(i);
            }

            return passwords;
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
