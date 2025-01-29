package commands;

import exceptions.InvalidCommandException;

/**
 * Method call if command word is not inside list of keywords, throws an InvalidCommandException.
 */
public class InvalidCase implements DefaultCase {

    private final String error;

    /**
     * InvalidCase instance.
     * @param error inputString from user.
     */
    public InvalidCase(String error) {
        this.error = error;
    }

    /**
     * Throws an InvalidCommandException.
     * @throws InvalidCommandException
     */
    @Override
    public void action() throws InvalidCommandException {
        throw new InvalidCommandException("Invalid command: " + error);
    }
}
