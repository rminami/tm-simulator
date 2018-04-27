package turingmachine;

/**
 * @author 150023118
 */
public class TuringEdge {
    private String inputState;
    private String outputState;
    private String inputSymbol;
    private String outputSymbol;
    private TuringMove move;

    public TuringEdge(String inputState, String inputSymbol, String outputState, String outputSymbol, TuringMove move) {
        this.inputState = inputState;
        this.outputState = outputState;
        this.inputSymbol = inputSymbol;
        this.outputSymbol = outputSymbol;
        this.move = move;
    }

    public String getInputState() {
        return inputState;
    }

    public String getOutputState() {
        return outputState;
    }

    public String getInputSymbol() {
        return inputSymbol;
    }

    public String getOutputSymbol() {
        return outputSymbol;
    }

    public TuringMove getMove() {
        return move;
    }
}
