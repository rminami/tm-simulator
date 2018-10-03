package exceptions;

/**
 * Thrown when the Turing machine encounters an input that it does not accept.
 *
 * @author Ryosuke Minami
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
