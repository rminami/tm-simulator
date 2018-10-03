package turingmachine;

import enums.TuringMove;
import exceptions.InvalidInputException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a state within a Turing machine.
 * The field 'name' stores the name as it appears in the Turing machine description,
 * and the map 'nextTransition' maps input symbols to transitions.
 *
 * @author Ryosuke Minami
 */
@EqualsAndHashCode @ToString
public class TuringState {
    private final @Getter String name;
    private final @Getter boolean isAccepting;
    private final Map<String, TuringTransition> nextMap;

    /**
     * Constructor.
     *
     * @param name The name of the state, as given in the description file.
     * @param isAccepting Whether or not this state accepts.
     */
    public TuringState(String name, boolean isAccepting) {
        this.name = name;
        this.isAccepting = isAccepting;
        nextMap = new HashMap<>();
    }

    /**
     * Adds a transition that the Turing machine can take when it is in this state.
     *
     * @param inSymbol Symbol to read from the tape.
     * @param outState State that the Turing machine will be in after transition.
     * @param outSymbol Symbol to write to the tape.
     * @param move Direction to move on the tape.
     * @throws InvalidInputException If there is already an entry for the state/input combination.
     */
    public void addTransition(String inSymbol, String outState, String outSymbol, TuringMove move)
            throws InvalidInputException {

        if (nextMap.containsKey(inSymbol)) {
            throw new InvalidInputException("Duplicate transition for state " + name + " with input " + inSymbol);
        }
        nextMap.put(inSymbol, new TuringTransition(outState, outSymbol, move));
    }

    /**
     * Gets the nextMap transition that the Turing machine should perform, given
     * the input symbol read from the tape.
     *
     * @param inputSymbol Input symbol from the tape.
     * @return The transition that the Turing machine should perform.
     * @throws InvalidInputException If there was no transition specified for this state/input combination.
     */
    public TuringTransition nextTransition(String inputSymbol) throws InvalidInputException {
        TuringTransition nextTransition = nextMap.get(inputSymbol);
        if (nextTransition == null) {
            throw new InvalidInputException("State " + name + " cannot take input " + inputSymbol);
        }
        return nextTransition;
    }
}
