package commands;

public class ByeCase implements DefaultCase {

    public ByeCase() { }

    @Override
    public void action() {
        System.out.println("Goodbye! Hope to see you again soon!"+
                "\n_____________________________________________");
    }

}
