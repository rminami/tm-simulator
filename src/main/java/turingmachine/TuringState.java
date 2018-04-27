package turingmachine;

import exceptions.InvalidTapeInputException;

import java.util.HashMap;
import java.util.Map;

/**
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

    public void addTransition(String inputSymbol, String outputStateName, String outputSymbol, char move) {
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
