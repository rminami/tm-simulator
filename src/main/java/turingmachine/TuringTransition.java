package turingmachine;

import enums.TuringMove;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString
public class TuringTransition {
    private final String outputState;
    private final String tapeOutput;
    private final TuringMove move;
}
