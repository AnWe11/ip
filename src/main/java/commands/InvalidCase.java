package commands;

import exceptions.InvalidCommandException;

/**
 * Throws InvalidCommandException.
 */
public class InvalidCase implements DefaultCase {

    private final String error;

    public InvalidCase(String error) {
        this.error = error;
    }

    /**
     * Returns an invalid command string.
     */
    @Override
    public String action() {
        return "Invalid command: " + error;
//        throw new InvalidCommandException("Invalid command: " + error);
    }
}
