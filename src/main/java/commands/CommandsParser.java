package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.*;

/**
 * Returns corresponding methods based on respective command words.
 */
public class CommandsParser {


    // List of command keywords the user can input that will trigger a method call.
    enum Keywords {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, INVALID;

        public static Keywords converter(String command) throws InvalidCommandException {
            try {
                return Keywords.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Keywords.INVALID;
            }
        }
    }

    private String commandWord = null;
    private String responseString;

    /**
     * Returns respective methods.
     * @param input Command input string.
     * @param taskManager TaskManager class instance.
     * @param data Storage class instance.
     * @throws InvalidCommandException If first word of the input is not in the list of keywords.
     */
    public CommandsParser(String input, TaskManager taskManager, Data data) throws InvalidCommandException {

        try {
            //Splits input string
            String[] inputArray = input.split(" ");
            this.commandWord = inputArray[0];

            Keywords keyword = Keywords.converter(commandWord);

            switch (keyword) {
            case TODO:
                ToDoCase todoCase = new ToDoCase(input, taskManager, data);
                responseString = todoCase.action();
                break;
            case DEADLINE:
                DeadlineCase deadlineCase = new DeadlineCase(input, taskManager, data);
                responseString = deadlineCase.action();
                break;
            case EVENT:
                EventsCase eventCase = new EventsCase(input, taskManager, data);
                responseString = eventCase.action();
                break;
            case LIST:
                ListCase listCase = new ListCase(taskManager);
                responseString = listCase.action();
                break;
            case MARK:
            case UNMARK:
                MarkUnmarkCase markUnmarkCase = new MarkUnmarkCase(input, keyword, taskManager, data);
                responseString = markUnmarkCase.action();
                break;
            case BYE:
                ByeCase byeCase = new ByeCase();
                responseString = byeCase.action();
                break;
            case DELETE:
                DeleteCase deleteCase = new DeleteCase(input, taskManager, data);
                responseString = deleteCase.action();
                break;
            case FIND:
                FindCase findCase = new FindCase(input, taskManager);
                responseString = findCase.action();
                break;
            case INVALID:
                InvalidCase invalidCase = new InvalidCase(input);
                responseString = invalidCase.action();
                break;
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            responseString = e.getMessage();
        }
    }
    public String getResponseString() {
        return this.responseString;
    }
}