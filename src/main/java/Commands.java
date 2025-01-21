import java.util.List;

public class Commands {

    //Initialisation of taskID and currTask which will only be initialised if the command is mark or unmark
    private int taskID = -1;
    private Tasks currTask = null;
    private String commandWord;

    //Constructor for Commands
    public Commands(String input, List<Tasks> tasksList) {
        //Splits input string
        String[] inputArray = input.split(" ");
        this.commandWord = inputArray[0];

        /*
        Check if the first word of the input is mark or unmark, if it is, it means that there already exists a
        task. Then initialise the currTask to that task
        */
        if (inputArray[0].equalsIgnoreCase("mark") || inputArray[0].equalsIgnoreCase("unmark")) {
            try {
                this.taskID = Integer.parseInt(inputArray[1]) - 1;
                if (this.taskID >= 0 && this.taskID < tasksList.size()) {
                    this.currTask = tasksList.get(this.taskID);
                } else {
                    System.out.println("Invalid task ID.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid taskID format.");
            }
            this.currTask = tasksList.get(this.taskID);
        }
    }

    //Method to get Command's Task ID if the command is mark / unmark
    public int getTaskID() {
        return this.taskID;
    }

    //Method to get Command's current Task if the command is mark / unmark
    public Tasks getCurrTask() {
        return this.currTask;
    }

    //Method to retrieve the first word of the command
    public String getCommandWord() {
        return this.commandWord;
    }
}