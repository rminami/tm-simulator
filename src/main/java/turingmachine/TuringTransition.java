package turingmachine;

/**
 * @author 150023118
 */
public class TuringTransition {

    private String outputState;
    private String tapeOutput;
    private char move;

    public TuringTransition(String outputState, String tapeOutput, char move) {
        this.outputState = outputState;
        this.tapeOutput = tapeOutput;
        this.move = move;
    }

    public String getOutputState() {
        return outputState;
    }

    public String getTapeOutput() {
        return tapeOutput;
    }

    public char getMove() {
        return move;
    }
}
