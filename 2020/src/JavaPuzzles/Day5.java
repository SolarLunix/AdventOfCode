package JavaPuzzles;

import java.util.*;

/*******
 *   2020:JavaPuzzles
 *   File: Day5
 *   Created by: Melissa Melaugh
 *   Created on: 05/12/2020
 *   Updated on: 05/12/2020
 *   Project Description: //TODO
 *******/
public class Day5 {
    public static void main(String[] args) {
        String[] seats = {"FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL"}; //
        ArrayList<Hashtable<String, String>> example = makeSeats(seats);
        printSeats(example);
        findMaxSeat(example);

        String[] scannedPasses = AoC_Utils.readLines("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\BoardingPasses.txt");
        ArrayList<Hashtable<String, String>> puzzel = makeSeats(scannedPasses);
        findMaxSeat(puzzel);
        System.out.println("My seat ID is " + findMySeat(puzzel));
    }

    private static void printSeats(ArrayList<Hashtable<String, String>> seats){
        for(Hashtable<String, String> seat : seats){
            System.out.println(String.format("%s: row %s, column %s, seat ID %s",
                    seat.get("code"), seat.get("row"), seat.get("column"),
                    seat.get("seatID")));
        }
    }

    private static void findMaxSeat(ArrayList<Hashtable<String, String>> seats){
        int max = 0;
        for(Hashtable<String, String> seat : seats){
            int seatID = Integer.parseInt(seat.get("seatID"));
            max = Math.max(max, seatID);
        }

        System.out.println("Your max seat ID is: " + max);
    }

    private static int findMySeat(ArrayList<Hashtable<String, String>> seats){
        int[] seatIDs = new int[seats.size()];
        for(int i = 0; i < seats.size(); i++){
            Hashtable<String, String> seat = seats.get(i);
            int seatID = Integer.parseInt((seat.get("seatID")));
            seatIDs[i] = seatID;
        }

        Arrays.sort(seatIDs);
        int mySeat = 0;
        for(int i = 0; i < seatIDs.length -1; i++){
            if(seatIDs[i] + 2 == seatIDs[i+1]){
                //System.out.println(String.format("%d %d", seatIDs[i], seatIDs[i+1]));
                mySeat = seatIDs[i] + 1;
            }
        }

        return mySeat;
    }

    private static ArrayList<Hashtable<String, String>> makeSeats(String[] seatsList){
        ArrayList<Hashtable<String, String>> seats = new ArrayList<>();

        for(String seatCode : seatsList){
            Hashtable<String, String> aSeat = new Hashtable<>();
            aSeat.put("code", seatCode);
            int rowmax = 127;
            int rowmin = 0;
            int columnmax = 7;
            int columnmin = 0;
            int row = 0;
            int column = 0;

            for(int i = 0; i < 10; i++){
                //System.out.println(String.format("%d, %d \t %d, %d", rowmin, rowmax, columnmin, columnmax));
                if(i < 6){
                    if(seatCode.charAt(i) == 'F'){
                        rowmax = findHalf(rowmin, rowmax, false);
                    } else {
                        rowmin = findHalf(rowmin, rowmax, true);
                    }
                } else if(i == 6){
                    if(seatCode.charAt(i) == 'F'){
                        row = rowmin;
                    } else {
                        row = rowmax;
                    }
                } else if (i < 9 ){
                    if(seatCode.charAt(i) == 'L'){
                        columnmax = findHalf(columnmin, columnmax, false);
                    } else {
                        columnmin = findHalf(columnmin, columnmax, true);
                    }
                } else {
                    if(seatCode.charAt(i) == 'L'){
                        column = columnmin;
                    } else {
                        column = columnmax;
                    }
                }
            }

            int seatID = (row * 8) + column;
            String value = String.format("%d", row);
            aSeat.put("row", value);
            value = String.format("%d", column);
            aSeat.put("column", value);
            value = String.format("%d", seatID);
            aSeat.put("seatID", value);

            seats.add(aSeat);
        }
        return seats;
    }

    private static int findHalf (int small, int large, boolean changeMin){
        int half = 0;
        if(changeMin) {
            half = (large + small) / 2 + 1;
        } else {
            half = (large + small) / 2;
        }
        return half;
    }

}
