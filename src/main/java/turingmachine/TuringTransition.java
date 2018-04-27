package turingmachine;

/**
 * @author 150023118
 */
public class TuringTransition {

    private String outputState;
    private String outputSymbol;
    private char move;

    public TuringTransition(String outputState, String outputSymbol, char move) {
        this.outputState = outputState;
        this.outputSymbol = outputSymbol;
        this.move = move;
    }

    public String getOutputState() {
        return outputState;
    }

    public String getOutputSymbol() {
        return outputSymbol;
    }

    public char getMove() {
        return move;
    }
}
