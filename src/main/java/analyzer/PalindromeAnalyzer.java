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
public class PalindromeAnalyzer {

    public static String getRandomPalindrome(int length) {
        char allowedChars[] = {'0', '1', '2'};
        Random random = new Random();

        int halfLength = length / 2;
        char[] randomChars = new char[halfLength];

        // Generates random characters for palindrome
        for (int i = 0; i < halfLength; i++) {
            randomChars[i] = allowedChars[random.nextInt(allowedChars.length)];
        }

        // Constructs random palindrome string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < halfLength; i++) {
            sb.append(randomChars[i]);
        }
        if (length % 2 != 0) {
            sb.append(allowedChars[random.nextInt(allowedChars.length)]);
        }
        for (int i = halfLength - 1; i >= 0; i--) {
            sb.append(randomChars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        File file = new File("./palindrome.txt");
        TuringMachine tm = new TuringMachine(file);

        try (
                PrintWriter writer = new PrintWriter(new FileWriter("palindrome-2.csv"));
        ) {
            for (int i = 1; i <= 100; i++) {
                writer.println(i + "," + tm.getStepCount(getRandomPalindrome(i)));
            }
        } catch (IOException e) {
            System.out.println("File could not be written.");
        }
    }
}
