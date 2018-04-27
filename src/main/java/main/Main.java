package turingmachine;

import java.io.File;

/**
 * @author 150023118
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("./palindrome.txt");
        TuringMachine tm = new TuringMachine(file);

        String[] inputs = {"010", "2112", "20102", "2122"};

        for (String input : inputs) {
            if (tm.accepts(input))
                System.out.println(input + " was accepted.");
            else
                System.out.println(input + " was not accepted");
        }
    }
}
