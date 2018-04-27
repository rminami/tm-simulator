package turingmachine;

import java.util.LinkedList;

/**
 * @author 150023118
 */
public class TuringTape {
    private LinkedList<String> tape;
    private int currentPosition;

    private final String BLANK = "_";

    public TuringTape() {
        tape = new LinkedList<>();
        currentPosition = 0;
    }

    public TuringTape(String str) {
        tape = new LinkedList<>();
        currentPosition = 0;

        char[] arr = str.toCharArray();
        for (char ch : arr) {
            tape.add(String.valueOf(ch));
        }
    }

    public String read() {
        return tape.get(currentPosition);
    }

    public void write(String str) {
        tape.set(currentPosition, str);
    }

    public void moveLeft() {
        if (currentPosition == 0) {
            tape.addFirst(BLANK);
        } else {
            currentPosition--;
        }
    }

    public void moveRight() {
        if (currentPosition == tape.size() - 1) {
            tape.addLast(BLANK);
        }
        currentPosition++;
    }

    public String toString() {
        return tape.toString();
    }
}
