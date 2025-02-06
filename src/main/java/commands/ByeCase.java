package commands;

/**
 * Returns goodbye message and exits the program.
 */
public class ByeCase implements DefaultCase {

    public ByeCase() { }

    /**
     * Returns goodbye message and exit the program.
     */
    @Override
    public String action() {
        return "Goodbye! Hope to see you again soon!";
    }

}
