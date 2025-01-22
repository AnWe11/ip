import java.util.List;

public class Commands {

    enum Keywords {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    //Initialisation of taskID and currTask which will only be initialised if the command is mark or unmark
    private int taskID = -1;
    private Tasks currTask = null;
    private String commandWord = null;
    private String taskDescription = null;
    private String deadlineString = null;
    private String fromString = null;
    private String toString = null;
    private List<Tasks> tasksList;

    //Constructor for Commands
    public Commands(String input, List<Tasks> tasksList) throws InvalidCommandException {
        this.tasksList = tasksList;
        //Splits input string
        String[] inputArray = input.split(" ");
        this.commandWord = inputArray[0];

        switch(commandWord) {
            case "todo":
                todoFilter(input);
                break;
            case "deadline":
                deadlineFilter(input);
                break;
            case "event":
                eventsFilter(input);
                break;
            case "list":
            case "mark":
            case "unmark":
            case "bye": break;
            case "delete":
                deleteTask(input);
                break;
            default:
                System.out.println("Unknown command word: " + commandWord);
                commandWord = "error";
        }


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

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void todoFilter(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("Oops! The task description of todo cannot be empty :(");
            }
            this.taskDescription = input.substring(firstSpaceIndex + 1);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for todo tasks should be\ntodo <Task Description>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }

    }

    public void deadlineFilter(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("Luigi is sad because there is no task description )':");
            }
            this.taskDescription = input.substring(firstSpaceIndex + 1);

            if (!this.taskDescription.contains("/by")) {
                throw new InvalidCommandException("Oops! You did not specify a deadline date D:");
            }
            String[] parts = this.taskDescription.split("/by", 2);
            this.taskDescription = parts[0];
            this.deadlineString = parts[1];
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for deadline tasks should be\ndeadline <Task Description> /by <deadline date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }

    }

    public void eventsFilter(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException(":((((((((( You did not include a task description");
            }
            this.taskDescription = input.substring(firstSpaceIndex + 1);

            if (!(this.taskDescription.contains("/from") && this.taskDescription.contains("/to"))) {
                throw new InvalidCommandException("The schedule timing is missing! ._.");
            }

            String[] parts = this.taskDescription.split("/from", 2);
            this.taskDescription = parts[0];
            String[] fromToString = parts[1].split("/to", 2);
            fromString = fromToString[0];
            toString = fromToString[1];
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }
    }

    public String getDeadlineString() {
        return this.deadlineString;
    }

    public String getFromString() {
        return this.fromString;
    }

    public String getToString() {
        return this.toString;
    }

    public void deleteTask(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("o.O You did not specify which task you would like to delete");
            }
            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            this.currTask = tasksList.get(this.taskID - 1);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }
    }

}