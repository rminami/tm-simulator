package turingmachine;

import enums.TuringMove;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidInputException;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TuringFactory {
    /**
     * Generates an instance of a Turing machine from an input file formatted as
     * specified in the practical specifications.
     *
     * @param inputFile - Input file containing Turing machine description.
     */
    public TuringMachine tmFromFile(File inputFile) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile))
        ) {
            Map<String, TuringState> states = new HashMap<>();

            // First line contains number of states e.g. 'states 21'
            String[] tokens = readAndSplit(reader);
            int noOfStates = Integer.parseInt(tokens[1]);

            // Second line contains the initial state e.g. 's0'
            tokens = readAndSplit(reader);
            TuringState state = new TuringState(tokens[0], isAcceptingFromInput(tokens));

            // The field 'states' maps the name of the state to the corresponding TuringState object
            states.put(tokens[0], state);
            TuringState initialState = state;

            // Stores all of the other states in the same way
            for (int i = 1; i < noOfStates; i++) {
                tokens = readAndSplit(reader);
                state = new TuringState(tokens[0], isAcceptingFromInput(tokens));
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
            for (String s : states.keySet()) {
                System.out.println(states.get(s).toString());
            }
            System.out.println(alphabet.toString());
            return new TuringMachine(states, initialState);

        } catch (IOException e) {
            throw new InvalidInputException("Could not read '" + inputFile.getName() + "'");
        }
    }

    public TuringMachine tmFromFilePath(String filePath) {
        File inputFile = new File(filePath);
        return tmFromFile(inputFile);
    }

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

    private static TuringMove moveFromInput(String input) throws InvalidDescriptionException {
        char inputChar = input.charAt(0);
        switch (inputChar) {
            case 'L': return TuringMove.LEFT;
            case 'R': return TuringMove.RIGHT;
            case 'S': return TuringMove.STAY;
            default: throw new InvalidDescriptionException("'" + input + "' is not a valid move.");
        }
    }

    private static boolean isAcceptingFromInput(String[] tokens) {
        return tokens.length > 1 && tokens[1].equals("+");
    }
}
