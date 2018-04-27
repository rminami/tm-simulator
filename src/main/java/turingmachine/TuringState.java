package turingmachine;

import exceptions.InvalidTapeInputException;
import exceptions.TransitionConflictException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 150023118
 */
public class TuringState {
    private String name;
    private boolean isAccepting;

    // Maps input symbols read from the tape to transitions
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

    public TuringTransition next(String tapeInput) throws InvalidTapeInputException {
        TuringTransition nextTransition = next.get(tapeInput);
        if (nextTransition == null) {
            throw new InvalidTapeInputException("State " + name + " cannot take input " + tapeInput);
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
