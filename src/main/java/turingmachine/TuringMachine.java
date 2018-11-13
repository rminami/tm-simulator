package turingmachine;

import exceptions.InvalidInputException;

import java.util.Map;

/**
 * Turing machine simulator.
 *
 * @author Ryosuke Minami
 */
public class TuringMachine {
    private final Map<String, TuringState> states;
    private TuringTape tape;

    private final TuringState initialState;
    private TuringState currentState;

    /**
     * Constructor.
     *
     * @param states Maps state names to TuringState objects.
     * @param initialState Turing machine starts in this state. Must be included in states.
     */
    public TuringMachine(Map<String, TuringState> states, TuringState initialState) {
        this.states = states;
        this.tape = new TuringTape();
        this.initialState = initialState;
        this.currentState = initialState;
    }

    /**
     * Executes another step according to the specifications.
     *
     * @throws InvalidInputException If the provided input is invalid.
     */
    private void step() throws InvalidInputException {
        String inputSymbol = tape.read();
        TuringTransition transition = currentState.nextTransition(inputSymbol);

        tape.write(transition.getTapeOutput());
        tape.move(transition.getMove());

        currentState = states.get(transition.getOutputState());
    }

    /**
     * Given an input string, this method determines whether or not it is
     * accepted by the Turing machine.
     *
     * @param input Input to read.
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

    /**
     * Given an input string, this method determines the number of steps
     * required for the Turing machine to reach an accepting state.
     *
     * @param input Input to read.
     * @return Number of steps until Turing machine accepts,
     *         -1 if the Turing machine does not accept the input.
     */
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
