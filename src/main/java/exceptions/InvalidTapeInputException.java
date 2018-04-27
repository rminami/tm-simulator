package exceptions;

/**
 * @author 150023118
 */
public class InvalidTapeInputException extends RuntimeException {
    private String message;

    public InvalidTapeInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
