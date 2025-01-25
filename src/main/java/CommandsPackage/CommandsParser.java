package CommandsPackage;

import Exceptions.InvalidCommandException;
import TaskPackage.*;

public class CommandsParser {

    enum Keywords {
        bye, list, mark, unmark, todo, deadline, event, delete
    }

    //Goodbye string of Chatbot
    String goodbye = "\nGoodbye! Hope to see you again soon!" +
            "\n_____________________________________________";

    //Initialisation of taskID and currTask which will only be initialised if the command is mark or unmark
    private int taskID = -1;
    private TasksDefault currTask = null;
    private String commandWord = null;
    private String taskDescription = null;
    private String deadlineString = null;
    private String fromString = null;
    private String toString = null;
    private TaskManager taskManager = null;

    //Constructor for Commands
    public CommandsParser(String input, TaskManager taskManager) throws InvalidCommandException {
        this.taskManager = taskManager;
        //Splits input string
        String[] inputArray = input.split(" ");
        this.commandWord = inputArray[0];

        switch (commandWord) {
            case "todo":
                ToDoCase todoCase = new ToDoCase(input, taskManager);
                todoCase.action();
                break;
            case "deadline":
                DeadlineCase deadlineCase = new DeadlineCase(input, taskManager);
                deadlineCase.action();
                break;
            case "event":
                EventsCase eventCase = new EventsCase(input, taskManager);
                eventCase.action();
                break;
            case "list":
                ListCase listCase = new ListCase(taskManager);
                listCase.action();
                break;
            case "mark":
            case "unmark":
                MarkUnmarkCase markUnmarkCase = new MarkUnmarkCase(input, this.commandWord, taskManager);
                markUnmarkCase.action();
                break;
            case "bye":
                ByeCase byeCase = new ByeCase();
                byeCase.action();
                break;
            case "delete":
                DeleteCase deleteCase = new DeleteCase(input, taskManager);
                deleteCase.action();
                break;
            default:
                System.out.println("Unknown command word: " + commandWord);
        }
    }
}