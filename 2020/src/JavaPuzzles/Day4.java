package JavaPuzzles;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.Hashtable;

/*******
 *   2020:JavaPuzzles
 *   File: Day4
 *   Created by: Melissa Melaugh
 *   Created on: 04/12/2020
 *   Updated on: 04/12/2020
 *   Project Description: //TODO
 *******/
public class Day4 {
    private static final String[] requiredField = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final String[] optionalField = {"cid"};

    public static void main(String[] args) {
        String[] example = { "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:1937 iyr:2017 cid:147 hgt:183cm",
                "",
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
                "hcl:#cfa07d byr:1929",
                "hcl:#ae17e1 iyr:2013",
                "eyr:2024",
                "ecl:brn pid:760753108 byr:1931",
                "hgt:179cm",
                "",
                "hcl:#cfa07d eyr:2025 pid:166559648",
                "iyr:2011 ecl:brn hgt:59in"
        };
        ArrayList<Hashtable> examplePassports = createPassports(example);
        getNumberValid(examplePassports, 1);

        String[] example2 = {"eyr:1972 cid:100",
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
                "" ,
                "iyr:2019",
                "hcl:#602927 eyr:1967 hgt:170cm",
                "ecl:grn pid:012533040 byr:1946",
                "",
                "hcl:dab227 iyr:2012",
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
                "",
                "hgt:59cm ecl:zzz",
                "eyr:2038 hcl:74454a iyr:2023",
                "pid:3556412378 byr:2007",
                "",
                "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980",
                "hcl:#623a2f",
                "",
                "eyr:2029 ecl:blu cid:129 byr:1989",
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
                "",
                "hcl:#888785",
                "hgt:164cm byr:2001 iyr:2015 cid:88",
                "pid:545766238 ecl:hzl",
                "eyr:2022",
                "",
                "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
        };
        examplePassports = createPassports(example2);
        System.out.println();
        getNumberValid(examplePassports, 2);

        System.out.println("\n=============================================\n");

        String[] testPassportsString = AoC_Utils.readLines("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\Passports.txt");
        ArrayList<Hashtable> testPassports = createPassports(testPassportsString);
        getNumberValid(testPassports, 1);
        System.out.println();
        getNumberValid(testPassports, 2);
    }

    private static void getNumberValid(ArrayList<Hashtable> passports, int part){
        int numValid = 0;
        int totalPassports = 0;
        for(Hashtable<String, String> passport : passports){
            totalPassports++;
            if(checkValid1(passport) && part == 1){
                numValid++;
            } else if(checkValid2(passport) && part == 2){
                numValid++;
            }
        }
        System.out.println(String.format("There are %d valid passports out of %d total.", numValid, totalPassports));
    }

    private static boolean checkValid1(Hashtable<String, String>passport){
        for(String field : requiredField){
            if(!passport.containsKey(field)){
                return false;
            }
        }
        return true;
    }

    private static boolean checkValid2(Hashtable<String, String>passport){
        if(checkValid1(passport)){
            boolean byr = checkYear(passport.get("byr"), 1920, 2002);
            boolean iyr = checkYear(passport.get("iyr"), 2010, 2020);
            boolean eyr = checkYear(passport.get("eyr"), 2020, 2030);
            boolean hgt = checkHeight(passport.get("hgt"));
            boolean hcl = checkHairColour(passport.get("hcl"));
            boolean ecl = checkEyeColour(passport.get("ecl"));
            boolean pid = checkPassport(passport.get("pid"));

            return (byr && iyr && eyr && hgt && hcl && ecl && pid);
        }
        return false;
    }

    private static boolean checkPassport(String pid){
        if(pid.length() == 9){
            return checkInt(pid, 0, 999999999);
        }
        return false;
    }

    private static boolean checkEyeColour(String eyeColour){
        String[] valid = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        boolean isValid = false;
        for(String color : valid){
            if(eyeColour.equals(color)){
                isValid = true;
            }
        }
        return isValid;
    }

    private static boolean checkHairColour(String hair){
        String valid = "0123456789abcdef";
        if(hair.charAt(0) == '#' && hair.length() == 7){
            boolean isValid = true;
            int i = 1;
            while(i < 7 && isValid){
                if(!valid.contains(hair.substring(i, i+1))){
                    isValid = false;
                }
                i++;
            }
            return isValid;
        }
        return false;
    }

    private static boolean checkHeight(String height){
        String unit = height.substring(height.length()-2);
        String number = height.substring(0, height.length()-2);
        if(unit.contains("in")){
            return checkInt(number, 59, 76);
        } else if (unit.contains("cm")){
            return checkInt(number, 150, 193);
        }
        return false;
    }

    private static boolean checkYear(String year, int minYear, int maxYear){
        if(year != null && year.length() == 4) {
            return checkInt(year, minYear, maxYear);
        }
        return false;
    }

    private static boolean checkInt(String myIntString, int myMin, int myMax){
        try{
            int myInt = Integer.parseInt(myIntString);
            if(myInt >= myMin && myInt <= myMax)
                return true;
            return false;
        } catch (Exception exception) {
            return false;
        }
    }

    private static ArrayList createPassports(String[] passportValues){
        Hashtable<String, String> passport = new Hashtable<>();
        ArrayList<Hashtable> passports = new ArrayList<>();

        for(String line : passportValues){
            if(line.length() == 0){
                passports.add(passport);
                passport = new Hashtable<>();
            } else {
                String[] values = line.split(" ");
                for(String value : values){
                    String[] keyValue = value.split(":");
                    passport.put(keyValue[0], keyValue[1]);
                }
            }
        }
        passports.add(passport);

        return passports;
    }
}
