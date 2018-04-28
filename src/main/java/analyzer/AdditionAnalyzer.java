package analyzer;

import turingmachine.TuringMachine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @author 150023118
 */
public class AdditionAnalyzer {

    private static String getReverseBinary(int n) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(n));
        return sb.reverse().toString();
    }

    public static String getRandomBinaries(int totalLength) {
        Random random = new Random();
        int halfLength = totalLength / 2;
        int first = random.nextInt((int) Math.round(Math.pow(2, halfLength)));
        int second = random.nextInt((int) Math.round(Math.pow(2, totalLength - halfLength)));
        int sum = first + second;

        return getReverseBinary(first) + "#" + getReverseBinary(second) + "#" + getReverseBinary(sum);
    }

    public static void main(String[] args) {
        File file = new File("./add.txt");
        TuringMachine tm = new TuringMachine(file);

        try (
                PrintWriter writer = new PrintWriter(new FileWriter("add-larger.csv"));
        ) {
            for (int i = 2; i <= 60; i++) {
                for (int j = 0; j < 50; j++) {
                    String input = getRandomBinaries(i);
                    writer.println(input + "," + input.length() + "," + tm.getStepCount(getRandomBinaries(i)));
                }
            }
        } catch (IOException e) {
            System.out.println("File could not be written.");
        }




    }
}
