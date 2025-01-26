package commands;

import exceptions.InvalidCommandException;

public class InvalidCase implements DefaultCase {

    private final String error;

    public InvalidCase(String error) {
        this.error = error;
    }

    @Override
    public void action() throws InvalidCommandException {
        System.out.println("_____________________________________________");
        System.out.println("Unknown command: " + error);
        System.out.println("_____________________________________________");
    }
}
