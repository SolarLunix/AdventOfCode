package JavaPuzzles;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*******
 *   2020:JavaPuzzles
 *   File: Find2020
 *   Created by: Melissa Melaugh
 *   Created on: 01/12/2020
 *   Updated on: 01/12/2020
 *   Project Description: //TODO
 *******/
public class Find2020 {
    protected static final int DESIRED_SUM = 2020;

    public static void main(String[] args) {
        int[] expenseReport1 = {1721, 979, 366, 299, 675, 1456};
        getTwoSumMultiplied(expenseReport1);
        getThreeSumMultiplied(expenseReport1);

        System.out.println("\n=============================================\n");

        File file = new File("C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\ExpenseReport.txt");
        int[] expenseReport2 = getExpenseReport(file);
        getTwoSumMultiplied(expenseReport2);
        getThreeSumMultiplied(expenseReport2);
    }

    private static int getTwoSumMultiplied(int[] expenses){
        for(int num1Index = 0; num1Index < expenses.length; num1Index++){
            for(int num2Index = num1Index+1; num2Index < expenses.length; num2Index++){
                int number1 = expenses[num1Index];
                int number2 = expenses[num2Index];
                if((number1+number2) == DESIRED_SUM){
                    System.out.println(String.format("The two numbers are %d and %d", number1, number2));
                    System.out.println(String.format("The answer is %d\n", (number1*number2)));
                    return (number1*number2);
                }
            }
        }
        return 0;
    }

    private static int getThreeSumMultiplied(int[] expenses){
        for(int num1Index = 0; num1Index < expenses.length; num1Index++){
            for(int num2Index = num1Index+1; num2Index < expenses.length; num2Index++){
                for(int num3Index = num2Index+1; num3Index < expenses.length; num3Index++){
                    int number1 = expenses[num1Index];
                    int number2 = expenses[num2Index];
                    int number3 = expenses[num3Index];
                    if((number1+number2+number3) == DESIRED_SUM) {
                        System.out.println(String.format("The three numbers are %d, %d and %d", number1, number2, number3));
                        System.out.println(String.format("The answer is %d", (number1 * number2 * number3)));
                        return (number1 * number2 * number3);
                    }
                }
            }
        }
        return 0;
    }

    private static int[] getExpenseReport(File file){
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Integer> expense = new ArrayList<Integer>();
            while (scanner.hasNext()) {
                if(scanner.hasNextInt()) {
                    expense.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }

            int[] expenses = new int[expense.size()];
            for(int i = 0; i < expense.size(); i++){
                expenses[i] = expense.get(i).intValue();
            }

            return expenses;
        } catch (Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
    }
}
