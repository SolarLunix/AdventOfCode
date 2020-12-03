package JavaPuzzles;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*******
 *   2020:JavaPuzzles
 *   File: AoC_Utils
 *   Created by: Melissa Melaugh
 *   Created on: 03/12/2020
 *   Updated on: 03/12/2020
 *   Project Description: //TODO
 *******/
public class AoC_Utils {
    public static String[] readLines(String path){
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            ArrayList<String> myArray = new ArrayList<String>();
            while (scanner.hasNext()) {
                myArray.add(scanner.nextLine());
            }

            String[] lines = new String[myArray.size()];
            for(int i = 0; i < myArray.size(); i++){
                lines[i] = myArray.get(i);
            }

            return lines;
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
