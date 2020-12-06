package JavaPuzzles;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*******
 *   2020:JavaPuzzles
 *   File: Day6
 *   Created by: Melissa Melaugh
 *   Created on: 06/12/2020
 *   Updated on: 06/12/2020
 *   Project Description: //TODO
 *******/
public class Day6 {
    public static void main(String[] args) {
        String[] example = {"abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b"};
        ArrayList<ArrayList<String>> exampleGroups = formatGroup(example);
        countUnique(exampleGroups);

        System.out.println("\n=============================================\n");

        String[] groups = AoC_Utils.readLines("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\PassengerSurvey.txt");
        ArrayList<ArrayList<String>> ourGroups = formatGroup(groups);
        countUnique(ourGroups);
    }

    private static ArrayList<ArrayList<String>> formatGroup(String[] groupList){
        ArrayList<ArrayList<String>> formattedGroups = new ArrayList<>();
        ArrayList<String> group = new ArrayList<>();

        int people = 0;
        for(String person : groupList){
            if(person.length() == 0){
                formattedGroups.add(group);
                group = new ArrayList<>();
            } else {
                people++;
                group.add(person);
            }
        }
        formattedGroups.add(group);

        System.out.println(String.format("There are %d people in %d groups.", people, formattedGroups.size()));
        return formattedGroups;
    }

    private static void countUnique(ArrayList<ArrayList<String>> groups){
        final String ANSWERS = "abcdefghijklmnopqrstuvwxyz";
        int[] groupCount = new int[ANSWERS.toCharArray().length];
        int[] finalCount1 = new int[ANSWERS.toCharArray().length];
        int[] finalCount2 = new int[ANSWERS.toCharArray().length];
        Arrays.fill(groupCount, 0);
        Arrays.fill(finalCount1, 0);
        Arrays.fill(finalCount2, 0);

        for(ArrayList<String> group : groups){
            int numberInGroup = 0;
            groupCount = new int[ANSWERS.toCharArray().length];
            Arrays.fill(groupCount, 0);

            for(String person : group) {
                numberInGroup++;
                for (int i = 0; i < ANSWERS.length(); i++) {
                    if (person.contains(ANSWERS.substring(i, i + 1))) {
                        groupCount[i]++;
                    }
                }
            }

            //System.out.println("\n=============================================\n");

            for(int i = 0; i < groupCount.length; i++){
                if(groupCount[i] > 0){
                    finalCount1[i]++;
                    int add = groupCount[i] / numberInGroup;
                    finalCount2[i] += add;
                }
                //System.out.println(String.format("%d, %d, %d", groupCount[i], finalCount1[i], finalCount2[i]));
            }

        }

        int answer1 = 0;
        for(int num : finalCount1){
            answer1+=num;
        }

        int answer2 = 0;
        for(int num : finalCount2){
            answer2+=num;
        }

        System.out.println("1) The sum of group answers is: " + answer1);
        System.out.println("2) The sum of group answers is: " + answer2);
    }

    private static int[] countReturn(int[] count, int[] finalCount){
        for(int i = 0; i < count.length; i++){
            System.out.println(count[i]);
            finalCount[i] += count[i];
        }
        return finalCount;
    }
}
