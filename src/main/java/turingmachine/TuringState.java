package turingmachine;

import exceptions.InvalidTapeInputException;
import exceptions.TransitionConflictException;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a state within a Turing machine.
 * The field 'name' stores the name as it appears in the Turing machine description,
 * and the map 'getNextTransition' maps input symbols to transitions.
 *
 * @author 150023118
 */
public class TuringState {
    private String name;
    private boolean isAccepting;

    private Map<String, TuringTransition> next;

    public TuringState(String name, boolean isAccepting) {
        this.name = name;
        this.isAccepting = isAccepting;
        next = new HashMap<>();
    }

    // Adds a transition for this state
    public void addTransition(String inputSymbol, String outputStateName, String outputSymbol,
                              char move)throws TransitionConflictException {
        if (next.containsKey(inputSymbol)) {
            throw new TransitionConflictException("Duplicate transition for state " + name + " with input " + inputSymbol);
        }
        next.put(inputSymbol, new TuringTransition(outputStateName, outputSymbol, move));
    }

    /**
     * Gets the next transition that the Turing machine should perform, given
     * the input symbol read from the tape.
     *
     * @param inputSymbol - Input symbol from the tape.
     * @return The next transition that the Turing machine should perform.
     * @throws InvalidTapeInputException if there was no transition specified for this state/input combination
     */
    public TuringTransition getNextTransition(String inputSymbol) throws InvalidTapeInputException {
        TuringTransition nextTransition = next.get(inputSymbol);
        if (nextTransition == null) {
            throw new InvalidTapeInputException("State " + name + " cannot take input " + inputSymbol);
        }
        return nextTransition;
    }

    public String getName() {
        return name;
    }

    public boolean isAccepting() {
        return isAccepting;
    }

}
