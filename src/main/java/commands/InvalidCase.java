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
     * Throws an InvalidCommandException.
     * @throws InvalidCommandException If given command is not in the list of valid keywords expected.
     */
    @Override
    public void action() throws InvalidCommandException {
        throw new InvalidCommandException("Invalid command: " + error);
    }
}
