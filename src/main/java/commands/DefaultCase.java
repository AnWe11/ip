package commands;

import exceptions.InvalidCommandException;

/**
 * Implements abstract method action().
 */
public interface DefaultCase {
    public abstract void action() throws InvalidCommandException;
}
