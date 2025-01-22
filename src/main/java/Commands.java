public class Commands {

    enum Keywords {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    //Goodbye string of Chatbot
    String goodbye = "\nGoodbye! Hope to see you again soon!"+
                            "\n_____________________________________________";

    //Initialisation of taskID and currTask which will only be initialised if the command is mark or unmark
    private int taskID = -1;
    private Tasks currTask = null;
    private String commandWord = null;
    private String taskDescription = null;
    private String deadlineString = null;
    private String fromString = null;
    private String toString = null;
    private TaskManager taskManager = null;

    //Constructor for Commands
    public Commands(String input, TaskManager taskManager) throws InvalidCommandException {
        this.taskManager = taskManager;
        //Splits input string
        String[] inputArray = input.split(" ");
        this.commandWord = inputArray[0];

        switch(commandWord) {
            case "todo":
                createTodoTask(input);
                break;
            case "deadline":
                createDeadlineTask(input);
                break;
            case "event":
                createEventsTask(input);
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                this.taskManager.listTasks();
                break;
            case "mark":
            case "unmark":
                markUnmarkTask(input);
                break;
            case "bye":
                System.out.println(goodbye);
                break;
            case "delete":
                deleteTask(input);
                break;
            default:
                System.out.println("Unknown command word: " + commandWord);
        }
    }

    public void markUnmarkTask(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                if (this.commandWord.equalsIgnoreCase("mark")) {
                    throw new InvalidCommandException("You did not specify which task you would like to mark... :c");
                } else {
                    throw new InvalidCommandException("You did not specify which task you would like to unmark... :[");
                }
            }

            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            if (this.taskID >= 0 && this.taskID <= taskManager.getTotalTasks()) {
                this.currTask = taskManager.getTask(this.taskID);
            } else {
                throw new NumberFormatException("Invalid Task ID");
            }

            this.currTask = taskManager.getTask(this.taskID);
            if (this.commandWord.equalsIgnoreCase("mark")) {
                System.out.println("Nice work! I've marked this task as done:");
                this.currTask.markAsDone();
            } else {
                System.out.println("Ok , I've unmarked this task:");
                this.currTask.unmark();
            }
            System.out.println(this.currTask.getDescription());
            System.out.println(" ");
            this.taskManager.getRemainingTasks();
            System.out.println("\n_____________________________________________");

            System.out.println("_____________________________________________\n");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format to mark task should be \nmark <task ID>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        } catch (NumberFormatException e) {
            System.out.println("Invalid taskID format.");
        }
    }

    //Method to retrieve the first word of the command
    public String getCommandWord() {
        return this.commandWord;
    }

    public void createTodoTask(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("Oops! The task description of todo cannot be empty :(");
            }

            this.taskDescription = input.substring(firstSpaceIndex + 1);
            ToDo todoTask = new ToDo(this.taskDescription);
            taskManager.addTask(todoTask);
            printDescription(todoTask);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for todo tasks should be\ntodo <Task Description>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }

    }

    public void createDeadlineTask(String input) throws InvalidCommandException {
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
            Deadlines deadlineTask = new Deadlines(this.taskDescription, this.deadlineString);
            taskManager.addTask(deadlineTask);
            printDescription(deadlineTask);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for deadline tasks should be\ndeadline <Task Description> /by <deadline date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }

    }

    public void createEventsTask(String input) throws InvalidCommandException {
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
            Events eventsTask = new Events(this.taskDescription, this.fromString, this.toString);
            taskManager.addTask(eventsTask);
            printDescription(eventsTask);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }
    }

    public void deleteTask(String input) throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("o.O You did not specify which task you would like to delete");
            }

            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            this.currTask = taskManager.getTask(this.taskID);
            System.out.println("\n_____________________________________________");
            System.out.println("Ok , I've deleted this task:");
            System.out.println(this.currTask.getDescription());
            System.out.println("_____________________________________________\n");
            taskManager.removeTask(this.taskID);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format to delete task should be \ndelete <task ID>");
            System.out.println("_____________________________________________\n");
            this.taskDescription = "";
            this.commandWord = "error";
        }
    }

    //Method to print task description
    public void printDescription(Tasks task) {
        System.out.println("_____________________________________________\n" + "Sure thing! I've added this task: ");
        System.out.println(task.getDescription());
        System.out.println("You currently have " + this.taskManager.getTotalTasks() + " task(s) in the list.");
        System.out.println("\n_____________________________________________\n");
    }

}