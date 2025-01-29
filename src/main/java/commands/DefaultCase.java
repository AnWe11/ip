package commands;

import exceptions.InvalidCommandException;

/**
 * Interface containing an abstract method action() which all implementations will have.
 */
public interface DefaultCase {
    public abstract void action() throws InvalidCommandException;
}
