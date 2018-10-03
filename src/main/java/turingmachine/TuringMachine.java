package turingmachine;

import exceptions.InvalidInputException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@EqualsAndHashCode @ToString
public class TuringMachine {
    private final Map<String, TuringState> states;
//    private final Set<String> alphabet;
    private TuringTape tape;

    private final TuringState initialState;
    private TuringState currentState;

    public TuringMachine(Map<String, TuringState> states, TuringState initialState) {
        this.states = states;
//        this.alphabet = alphabet;
        this.tape = new TuringTape("_");
        this.initialState = initialState;
        this.currentState = initialState;
    }

    private void step() throws InvalidInputException {
        String inputSymbol = tape.read();
        TuringTransition transition = states.get(currentState.getName()).getNextTransition(inputSymbol);

        System.out.println("Read " + inputSymbol + ", " + transition.toString());
        tape.write(transition.getTapeOutput());
        tape.move(transition.getMove());

        currentState = states.get(transition.getOutputState());
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
        } catch (InvalidInputException e) {
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
        } catch (InvalidInputException e) {
            return -1;
        }
    }
}
