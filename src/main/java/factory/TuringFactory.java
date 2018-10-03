package factory;

import enums.TuringMove;
import exceptions.InvalidDescriptionException;
import org.jetbrains.annotations.Nullable;
import turingmachine.TuringMachine;
import turingmachine.TuringState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class reads Turing machine description files, and generates TuringMachine
 * instances according to the descriptions.
 *
 * @author Ryosuke Minami
 */
public class TuringFactory {
    /**
     * Generates an instance of a Turing machine from an input file formatted as
     * specified in the specification. See the `tms` directory for examples.
     *
     * @param inputFile Input file containing the Turing machine description.
     * @return Turing machine instance according to the description.
     * @throws InvalidDescriptionException If the description is not in the correct format.
     */
    public TuringMachine tmFromFile(File inputFile) throws IOException, InvalidDescriptionException {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile))
        ) {
            Map<String, TuringState> states = new HashMap<>();

            // First line contains number of states e.g. 'states 21'
            String[] tokens = readAndSplit(reader);
            int noOfStates = Integer.parseInt(tokens[1]);

            // Second line contains the initial state e.g. 's0'
            tokens = readAndSplit(reader);
            TuringState state = new TuringState(tokens[0], acceptBoolFromInput(tokens));

            // The field 'states' maps the name of the state to the corresponding TuringState object
            states.put(tokens[0], state);
            TuringState initialState = state;

            // Stores all of the other states n the same manner
            for (int i = 1; i < noOfStates; i++) {
                tokens = readAndSplit(reader);
                state = new TuringState(tokens[0], acceptBoolFromInput(tokens));
                states.put(tokens[0], state);
            }

            // Next line contains the keyword 'alphabet', the size of the alphabet, and its members.
            // e.g. 'alphabet 3 a b c'.
            tokens = readAndSplit(reader);
            Set<String> alphabet = new HashSet<>();
            alphabet.add("_"); // Underscore is a placeholder for empty tape cells.
            int alphabetSize = Integer.parseInt(tokens[1]);

            for (int i = 2; i < alphabetSize + 2; i++) {
                alphabet.add(tokens[i]);
            }

            while ((tokens = readAndSplit(reader)) != null) {
                // Allows comment lines starting with '#'
                if (tokens[0].equals("#")) {
                    continue;
                }
                if (!alphabet.contains(tokens[1])) {
                    throw new InvalidDescriptionException("'" + tokens[1] + "' is not in the alphabet.");
                }
                if (!alphabet.contains(tokens[3])) {
                    throw new InvalidDescriptionException("'" + tokens[3] + "' is not in the alphabet.");
                }
                states.get(tokens[0]).addTransition(tokens[1], tokens[2], tokens[3], moveFromInput(tokens[4]));
            }
            return new TuringMachine(states, initialState);

        } catch (NullPointerException | NumberFormatException e) {
            throw new InvalidDescriptionException("Invalid description.");
        }
    }

    /**
     * Generates an instance of a Turing machine from an input file path. Just a small wrapper
     * around the tmFromFile method.
     *
     * @param filePath Input file path.
     * @return Turing machine instance according to the description.
     * @throws InvalidDescriptionException If the description is not in the correct format.
     */
    public TuringMachine tmFromFilePath(String filePath) throws IOException, InvalidDescriptionException {
        File inputFile = new File(filePath);
        return tmFromFile(inputFile);
    }

    /**
     * Reads a new line from the file, splits the line into tokens, and returns the tokens.
     *
     * @param reader Buffered reader to read from.
     * @return Tokens from the next line. Returns null if reader is at the last line.
     * @throws IOException If the next line cannot be read.
     */
    private static @Nullable String[] readAndSplit(BufferedReader reader) throws IOException {
        String[] tokens = {""};
        while (tokens[0].length() == 0) {
            String line = reader.readLine();
            if (line == null) {
                return null;
            }
            tokens = line.trim().split("\\s+");
        }
        return tokens;
    }

    /**
     * Maps description inputs to the enum used in the simulator.
     *
     * @param input Input string as provided in the description.
     * @return A {@link TuringMove} corresponding to the input.
     * @throws InvalidDescriptionException If the input is not 'L', 'R', or 'S'.
     */
    private static TuringMove moveFromInput(String input) throws InvalidDescriptionException {
        char inputChar = input.charAt(0);
        switch (inputChar) {
            case 'L': return TuringMove.LEFT;
            case 'R': return TuringMove.RIGHT;
            case 'S': return TuringMove.STAY;
            default: throw new InvalidDescriptionException("'" + input + "' is not a valid move.");
        }
    }

    /**
     * Maps description inputs to a boolean based on whether or not the most recently read
     * state is an accepting state.
     *
     * @param tokens Input tokens from the most recently read line.
     * @return Boolean representing whether or not the most recently read state is an accepting state.
     */
    private static boolean acceptBoolFromInput(String[] tokens) {
        return tokens.length > 1 && tokens[1].equals("+");
    }
}
