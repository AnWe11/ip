package CommandsPackage;

import Exceptions.InvalidCommandException;
import TaskPackage.*;

public class CommandsParser {

    enum Keywords {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

        public static Keywords converter(String command) throws InvalidCommandException {
            try {
                return Keywords.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException("Invalid command: " + command);
            }
        }
    }

    private String commandWord = null;
    private TaskManager taskManager = null;

    //Constructor for Commands
    public CommandsParser(String input, TaskManager taskManager) throws InvalidCommandException {
        this.taskManager = taskManager;
        //Splits input string
        String[] inputArray = input.split(" ");
        this.commandWord = inputArray[0];

        Keywords keyword = Keywords.converter(commandWord);

        switch (keyword) {
            case TODO:
                ToDoCase todoCase = new ToDoCase(input, taskManager);
                todoCase.action();
                break;
            case DEADLINE:
                DeadlineCase deadlineCase = new DeadlineCase(input, taskManager);
                deadlineCase.action();
                break;
            case EVENT:
                EventsCase eventCase = new EventsCase(input, taskManager);
                eventCase.action();
                break;
            case LIST:
                ListCase listCase = new ListCase(taskManager);
                listCase.action();
                break;
            case MARK:
            case UNMARK:
                MarkUnmarkCase markUnmarkCase = new MarkUnmarkCase(input, keyword, taskManager);
                markUnmarkCase.action();
                break;
            case BYE:
                ByeCase byeCase = new ByeCase();
                byeCase.action();
                break;
            case DELETE:
                DeleteCase deleteCase = new DeleteCase(input, taskManager);
                deleteCase.action();
                break;
            default:
                System.out.println("Unknown command word: " + keyword);
        }
    }
}