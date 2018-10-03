package exceptions;

import java.io.IOException;

public class InvalidDescriptionException extends IOException {
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
