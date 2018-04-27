package turingmachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    private TuringState currentState;

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

            // The first state given in the input file is the initial state
            TuringState initialState = new TuringState(parts[0], parts.length > 1 && parts[1].equals("+"));

            // The field 'states' maps the name of the state to the corresponding TuringState object
            states.put(parts[0], initialState);
            currentState = initialState;

            // Stores all of the other states in the same way
            for (int i = 1; i < noOfStates; i++) {
                line = reader.readLine();
                parts = line.trim().split(" ");
                TuringState state = new TuringState(parts[0], parts.length > 1 && parts[1].equals("+"));
                states.put(parts[0], state);
            }

            // Reading alphabet
            line = reader.readLine();
            parts = line.trim().split(" ");

            int alphabetSize = Integer.parseInt(parts[1]);

            for (int i = 0; i < alphabetSize; i++) {
                alphabet.add(parts[i + 2]);
            }

            while ((line = reader.readLine()) != null) {
                parts = line.trim().split(" ");
                states.get(parts[0]).addTransition(parts[1], parts[2], parts[3], parts[4].charAt(0));
            }

        } catch (IOException e) {
            System.out.printf("Could not read '%s'\n", inputFile);
        }
    }


    public boolean processInput(String inputStr) {

        tape = new TuringTape(inputStr);


        while (!currentState.isAccepting()) {
            String inputSymbol = tape.read();

            TuringTransition transition = states.get(currentState.getName()).nextTransition(inputSymbol);

            tape.write(transition.getOutputSymbol());

            if (transition.getMove() == 'L') {
                tape.moveLeft();
            }
            if (transition.getMove() == 'R') {
                tape.moveRight();
            }

            currentState = states.get(transition.getOutputState());

        }
        return true;
    }

    public TuringState getCurrentState() {
        return currentState;
    }
}
