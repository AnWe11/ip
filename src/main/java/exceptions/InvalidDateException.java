package exceptions;

import java.time.format.DateTimeParseException;

public class InvalidDateException extends DateTimeParseException {
    public InvalidDateException(String message, String invalidDate) {
        super(message, invalidDate, 0);
    }
}
