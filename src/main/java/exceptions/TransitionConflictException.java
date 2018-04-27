package exceptions;

/**
 * @author 150023118
 */
public class TransitionConflictException extends RuntimeException {

    private String message;

    public TransitionConflictException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
