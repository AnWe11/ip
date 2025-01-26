package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.*;

public class CommandsParser {

    enum Keywords {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;

        public static Keywords converter(String command) throws InvalidCommandException {
            try {
                return Keywords.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return Keywords.INVALID;
            }
        }
    }

    private String commandWord = null;

    //Constructor for Commands
    public CommandsParser(String input, TaskManager taskManager, Data data) throws InvalidCommandException {

        try {
            //Splits input string
            String[] inputArray = input.split(" ");
            this.commandWord = inputArray[0];

            Keywords keyword = Keywords.converter(commandWord);

            switch (keyword) {
                case TODO:
                    ToDoCase todoCase = new ToDoCase(input, taskManager, data);
                    todoCase.action();
                    break;
                case DEADLINE:
                    DeadlineCase deadlineCase = new DeadlineCase(input, taskManager, data);
                    deadlineCase.action();
                    break;
                case EVENT:
                    EventsCase eventCase = new EventsCase(input, taskManager, data);
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
                case INVALID:
                    InvalidCase invalidCase = new InvalidCase(input);
                    invalidCase.action();
                    break;
            }
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }

    }
}