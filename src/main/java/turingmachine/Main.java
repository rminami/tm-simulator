package turingmachine;

import java.io.File;

/**
 * @author 150023118
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("./input.txt");
        TuringMachine tm = new TuringMachine(file);

    }
}
