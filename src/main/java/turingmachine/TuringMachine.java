package turingmachine;

import exceptions.InvalidTapeInputException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a Turing Machine.
 *
 * @author 150023118
 */
public class TuringMachine {
    private Map<String, TuringState> states;
    private Set<String> alphabet;
    private TuringTape tape;

    private TuringState initialState;
    private TuringState currentState;

    /**
     * Generates an instance of a Turing machine from an input file formatted as
     * specified in the practical specifications.
     *
     * @param inputFile - Input file containing Turing machine description.
     */
    public TuringMachine(File inputFile) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(inputFile))) {

            states = new HashMap<>();

            String line = reader.readLine();
            String[] parts = line.trim().split(" ");

            int noOfStates = Integer.parseInt(parts[1]);

            line = reader.readLine();
            parts = line.trim().split(" ");

            // The first state given in the input file is the initial state
            TuringState state = new TuringState(parts[0], parts.length > 1 && parts[1].equals("+"));

            // The field 'states' maps the name of the state to the corresponding TuringState object
            states.put(parts[0], state);
            initialState = state;
            currentState = initialState;

            // Stores all of the other states in the same way
            for (int i = 1; i < noOfStates; i++) {
                line = reader.readLine();
                parts = line.trim().split(" ");
                state = new TuringState(parts[0], parts.length > 1 && parts[1].equals("+"));
                states.put(parts[0], state);
            }

            // Reading alphabet
            line = reader.readLine();
            parts = line.trim().split(" ");

            alphabet = new HashSet<>();
            alphabet.add("_");
            int alphabetSize = Integer.parseInt(parts[1]);

            for (int i = 0; i < alphabetSize; i++) {
                alphabet.add(parts[i + 2]);
            }

            while ((line = reader.readLine()) != null) {
                parts = line.trim().split(" ");

                // Allows comment lines starting with '#'
                if (parts[0].equals("#")) {
                    continue;
                }

                if (!alphabet.contains(parts[1])) {
                    throw new IllegalArgumentException(parts[1] + " is not in the alphabet.");
                }
                if (!alphabet.contains(parts[3])) {
                    throw new IllegalArgumentException(parts[3] + " is not in the alphabet.");
                }
                states.get(parts[0]).addTransition(parts[1], parts[2], parts[3], parts[4].charAt(0));
            }

        } catch (IOException e) {
            System.out.printf("Could not read '%s'\n", inputFile);
        }
    }

    private void step() throws InvalidTapeInputException {
        String inputSymbol = tape.read();
        TuringTransition transition = states.get(currentState.getName()).getNextTransition(inputSymbol);

//        System.out.println();
//        System.out.println("Current state is " + currentState.getName());
//        System.out.println("Writing " + transition.getTapeOutput() + " and moving " + transition.getMove());
        tape.write(transition.getTapeOutput());

        if (transition.getMove() == 'L') {
            tape.moveLeft();
        } else if (transition.getMove() == 'R') {
            tape.moveRight();
        }
        currentState = states.get(transition.getOutputState());
//        System.out.println(tape.toString());
    }


    /**
     * Given an input string, this method determines whether or not it is
     * accepted by the Turing machine.
     *
     * @param input - Input to read.
     * @return Whether or not it is accepted.
     */
    public boolean accepts(String input) {

        tape = new TuringTape(input);
        currentState = initialState;

        try {

            while (!currentState.isAccepting()) {
                step();

            }
            return true;
        } catch (InvalidTapeInputException e) {
            return false;
        }
    }

    public int getStepCount(String input) {
        tape = new TuringTape(input);
        currentState = initialState;
        try {
            int stepCount = 0;
            while (!currentState.isAccepting()) {
                step();
                stepCount++;
            }
            return stepCount;
        } catch (InvalidTapeInputException e) {
            return -1;
        }
    }
}
