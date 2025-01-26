package commands;

import exceptions.InvalidCommandException;

public interface DefaultCase {
    public abstract void action() throws InvalidCommandException;
}
