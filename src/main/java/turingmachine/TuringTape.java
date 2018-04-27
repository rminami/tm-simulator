package turingmachine;

import java.util.LinkedList;

/**
 * @author 150023118
 */
public class TuringTape {
    private LinkedList<String> tape;
    private int currentPosition;

    public TuringTape() {
        tape = new LinkedList<>();
        currentPosition = 0;
    }
}
