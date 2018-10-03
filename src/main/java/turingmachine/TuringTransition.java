package turingmachine;

import enums.TuringMove;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents a transition from one state to another in the Turing machine.
 *
 * @author Ryosuke Minami
 */
@Getter @AllArgsConstructor @ToString
public class TuringTransition {
    private final String outputState;
    private final String tapeOutput;
    private final TuringMove move;
}
