package exceptions;

/**
 * Thrown when a Turing machine description does not comply with the specifications.
 *
 * @author Ryosuke Minami
 */
public class InvalidDescriptionException extends RuntimeException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
