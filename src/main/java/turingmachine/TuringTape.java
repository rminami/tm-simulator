package turingmachine;

import enums.TuringMove;
import lombok.ToString;

import java.util.LinkedList;

@ToString
public class TuringTape {
    private final LinkedList<String> tape;
    private int currentPos;

    public TuringTape() {
        tape = new LinkedList<>();
        tape.add(" ");
        currentPos = 0;
    }

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
     * @return
     */
    public String read() {
        return tape.get(currentPos);
    }

    public void write(String str) {
        tape.set(currentPos, str);
    }

    public void move(TuringMove move) {
        switch (move) {
            case LEFT: moveLeft();
            case RIGHT: moveRight();
        }
    }

    public void moveLeft() {
        if (currentPos == 0) {
            tape.addFirst("_");
        } else {
            currentPos--;
        }
    }

    public void moveRight() {
        if (currentPos == tape.size() - 1) {
            tape.addLast("_");
        }
        currentPos++;
    }
}
