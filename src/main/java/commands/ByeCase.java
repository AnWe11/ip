package commands;

/**
 * Method call if command word is 'bye', prints goodbye message and exits the program.
 */
public class ByeCase implements DefaultCase {

    public ByeCase() { }

    /**
     * Method call to print the goodbye message and exit the program.
     */
    @Override
    public void action() {
        System.out.println("Goodbye! Hope to see you again soon!"+
                "\n_____________________________________________");
        System.exit(0);
    }

}
