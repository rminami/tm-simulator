package turingmachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a Turing Machine.
 *
 * @author 150023118
 */
public class TuringMachine {
    private Map<String, Boolean> states;
    private Set<String> alphabet;

    private Map<String, TuringEdge> nextFinder;
    private List<String> tape;

    private String currentState;

    private boolean isAccepting() {
        return states.get(currentState);
    }

    public TuringMachine(File inputFile) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFile))) {

            states = new HashMap<>();
            alphabet = new HashSet<>();

            String line = reader.readLine();
            String[] parts = line.trim().split(" ");

            int noOfStates = Integer.parseInt(parts[1]);

            line = reader.readLine();
            parts = line.trim().split(" ");
            states.put(parts[0], parts.length > 1 && parts[1].equals("+"));
            currentState = parts[0];

            for (int i = 1; i < noOfStates; i++) {
                line = reader.readLine();
                parts = line.trim().split(" ");
                states.put(parts[0], parts.length > 1 && parts[1].equals("+"));
            }

            // Reading alphabet
            line = reader.readLine();
            parts = line.trim().split(" ");

            int alphabetSize = Integer.parseInt(parts[1]);

            for (int i = 0; i < alphabetSize; i++) {
                alphabet.add(parts[i + 2]);
            }

            nextFinder = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                parts = line.trim().split(" ");
                TuringEdge edge = new TuringEdge(parts[0], parts[1], parts[2], parts[3], charToTuringMove(parts[4].charAt(0)));
                nextFinder.put(parts[0] + parts[1], edge);
            }

        } catch (IOException e) {
            System.out.printf("Could not read '%s'\n", inputFile);
        }
    }

    public TuringMove charToTuringMove(char ch) {
        switch (ch) {
            case 'L':
                return TuringMove.LEFT;
            case 'R':
                return TuringMove.RIGHT;
            case 'S':
                return TuringMove.STOP;
            default:
                return null;
        }
    }

    public void read(String input) {
        TuringEdge edge;
        tape = new LinkedList<>();

        for (char ch : input.toCharArray()) {
            edge = nextFinder.get(currentState + ch);
            currentState = edge.getOutputState();

        }
    }


}
