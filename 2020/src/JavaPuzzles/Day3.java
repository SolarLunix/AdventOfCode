package JavaPuzzles;

/*******
 *   2020:JavaPuzzles
 *   File: Day3
 *   Created by: Melissa Melaugh
 *   Created on: 03/12/2020
 *   Updated on: 03/12/2020
 *   Project Description: //TODO
 *******/
public class Day3 {
    public static void main(String[] args) {
        String[] hill1 = {  "..##.......",
                            "#...#...#..",
                            ".#....#..#.",
                            "..#.#...#.#",
                            ".#...##..#.",
                            "..#.##.....",
                            ".#.#.#....#",
                            ".#........#",
                            "#.##...#...",
                            "#...##....#",
                            ".#..#...#.#"   };
        traverseSlope1(hill1, 1, 3);
        traverseSlope2(hill1);

        System.out.println("\n=============================================\n");

        String[] hill2 = AoC_Utils.readLines("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\Slope.txt");
        traverseSlope1(hill2, 1, 3);
        traverseSlope2(hill2);
    }

    private static int traverseSlope1(String[] hill, int rise, int run){
        int numberOfTrees = 0;
        int horizontalPosition = 0;
        for(int down = 0; down < hill.length; down += rise){
            String hill_section = hill[down];
            if(hill_section.charAt(horizontalPosition) == '#'){
                numberOfTrees++;
            }
            horizontalPosition += run;
            horizontalPosition %= hill_section.length();
        }
        System.out.println(String.format("You encountered %d trees.", numberOfTrees));
        return numberOfTrees;
    }

    private static void traverseSlope2(String[] hill){
        final int[] rise = {1, 1, 1, 1, 2};
        final int[] run = {1, 3, 5, 7, 1};
        int multipliedTrees = 1;

        for(int combo = 0; combo < rise.length; combo++){
            multipliedTrees *= traverseSlope1(hill, rise[combo], run[combo]);
        }

        System.out.println(String.format("Multipled together is %d.", multipliedTrees));
    }

}
