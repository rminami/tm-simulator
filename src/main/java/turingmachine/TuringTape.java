package turingmachine;

import enums.TuringMove;

import java.util.LinkedList;

/**
 * Represents the tape that the Turing machine moves along and reads/writes to.
 *
 * @author Ryosuke Minami
 */
public class TuringTape {
    private final LinkedList<String> tape;
    private int currentPos;

    /**
     * Constructor. Initializes the tape with a length of one
     * with a blank symbol printed on the first cell.
     */
    public TuringTape() {
        tape = new LinkedList<>();
        tape.add("_");
        currentPos = 0;
    }

    /**
     * Constructor. Initializes the tape with the symbols comprising
     * the string given as the parameter.
     *
     * @param str String containing initial values for the tape.
     */
    public TuringTape(String str) {
        tape = new LinkedList<>();
        currentPos = 0;

        char[] arr = str.toCharArray();
        for (char ch : arr) {
            tape.add(String.valueOf(ch));
        }
    }

    /**
     * Reads the value on the tape at the current position.
     *
     * @return Symbol printed on current position of the tape.
     */
    public String read() {
        return tape.get(currentPos);
    }

    /**
     * Overwrites the value on the current position on the tape with a new value.
     *
     * @param str Value to print on tape.
     */
    public void write(String str) {
        tape.set(currentPos, str);
    }

    /**
     * Moves the Turing machine on the tape.
     *
     * @param move The direction to move the machine in.
     */
    public void move(TuringMove move) {
        switch (move) {
            case LEFT: moveLeft();
            case RIGHT: moveRight();
        }
    }

    /** Moves Turing machine to the left. */
    public void moveLeft() {
        if (currentPos == 0) {
            tape.addFirst("_");
        } else {
            currentPos--;
        }
    }

    /** Moves Turing machine to the right. */
    public void moveRight() {
        if (currentPos == tape.size() - 1) {
            tape.addLast("_");
        }
        currentPos++;
    }
}
