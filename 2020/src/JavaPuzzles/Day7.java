package JavaPuzzles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/*******
 *   2020:JavaPuzzles
 *   File: Day7
 *   Created by: Melissa Melaugh
 *   Created on: 07/12/2020
 *   Updated on: 07/12/2020
 *   Project Description: //TODO
 *******/
public class Day7 {
    public static void main(String[] args) {
        String[] exampleRules = {"light red bags contain 1 bright white bag, 2 muted yellow bags.",
                "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                "bright white bags contain 1 shiny gold bag.",
                "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                "faded blue bags contain no other bags.",
                "dotted black bags contain no other bags."};
        String[] exampleRules2 = {"shiny gold bags contain 2 dark red bags.",
                "dark red bags contain 2 dark orange bags.",
                "dark orange bags contain 2 dark yellow bags.",
                "dark yellow bags contain 2 dark green bags.",
                "dark green bags contain 2 dark blue bags.",
                "dark blue bags contain 2 dark violet bags.",
                "dark violet bags contain no other bags."};
        Hashtable<String, String> exampleRulesDict = rulesToDictionary(exampleRules);
        Hashtable<String, String> exampleRulesDict2 = rulesToDictionary(exampleRules2);
        String[] rules = AoC_Utils.readLines("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\BagOfBags.txt");
        Hashtable<String, String> rulesDict = rulesToDictionary(rules);

        String mybag = "shiny gold";
        countUnique(getAllOuterBags(mybag, exampleRulesDict, new ArrayList<String>()));
        countUnique(getAllOuterBags(mybag, rulesDict, new ArrayList<String>()));
        getAllInnerBags(mybag, exampleRulesDict);
        System.out.println("Looking for 32");
        getAllInnerBags(mybag, exampleRulesDict2);
        System.out.println("Looking for 126");
        getAllInnerBags(mybag, rulesDict);
    }

    public static Hashtable<String, String> rulesToDictionary(String[] rules){
        Hashtable<String, String> rulesDict = new Hashtable<>();
        for(String rule:rules){
            String[] values = rule.split(" bags contain ");
            rulesDict.put(values[0], values[1]);
            //System.out.println(values[0] + ":\t" + values[1]);
        }
        return rulesDict;
    }

    public static ArrayList<String> getOuterBag(String bagColour, Hashtable<String, String> rules){
        ArrayList<String> outerBag = new ArrayList<>();
        for(String rule : rules.keySet()){
            if(rules.get(rule).contains(bagColour)){
                outerBag.add(rule);
            }
        }
        return outerBag;
    }

    public static ArrayList<String> getAllOuterBags(String bag, Hashtable<String, String> rules, ArrayList<String> canContian){
        for(String colour : getOuterBag(bag, rules)){
            canContian.add(colour);
            canContian = getAllOuterBags(colour, rules, canContian);
            //System.out.println(colour);
        }
        return canContian;
    }

    public static void countUnique(ArrayList<String> bags){
        HashSet<String> hs = new HashSet<String>();

        for(int i = 0; i < bags.size(); i++)
        {
            hs.add(bags.get(i));
        }

        System.out.println("There are " + hs.size() + " unique outer bag colours your gold bag could be in.");
    }

    public static int getInnerBags(String bagColour, Hashtable<String, String> rules){
        int total = 0;
        String rule = rules.get(bagColour);
        //System.out.println("\n" + rule);
        if(rule.contains("no other")) {
            return 0;
        } else {
            String[] innerBags = rule.split(", ");
            for (String bag : innerBags) {
                String[] words = bag.split(" ");
                int numBags = Integer.parseInt(words[0]);
                //System.out.println(numBags);
                String colour = String.format("%s %s", words[1], words[2]);
                total += (numBags * getInnerBags(colour, rules));
                total += numBags;
            }
        }
        return total;
    }

    public static void getAllInnerBags(String bagColour, Hashtable<String, String> rules){
        System.out.println("Your gold bag contains " + (getInnerBags(bagColour, rules)) + " bags.");
    }

}
