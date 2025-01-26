package CommandsPackage;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidIDException;
import TaskPackage.TaskManager;
import TaskPackage.TasksDefault;

import java.security.Key;

public class MarkUnmarkCase implements DefaultCase {
    private String input;
    private CommandsParser.Keywords keyword;
    private TaskManager taskManager;

    MarkUnmarkCase(String input, CommandsParser.Keywords keyword, TaskManager taskManager) {
        this.input = input;
        this.keyword = keyword;
        this.taskManager = taskManager;
    }

    @Override
    public void action() throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                if (this.keyword == CommandsParser.Keywords.MARK) {
                    throw new InvalidCommandException("You did not specify which task you would like to mark... :c");
                } else {
                    throw new InvalidCommandException("You did not specify which task you would like to unmark... :[");
                }
            }

            int taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            TasksDefault currTask;
            if (taskID >= 0 && taskID <= taskManager.getTotalTasks()) {
                currTask = taskManager.getTask(taskID);
            } else {
                throw new InvalidIDException("Invalid Task ID");
            }

            currTask = taskManager.getTask(taskID);
            if (this.keyword == CommandsParser.Keywords.MARK) {
                System.out.println("Nice work! I've marked this task as done:");
                currTask.markAsDone();
            } else {
                System.out.println("Ok , I've unmarked this task:");
                currTask.unmark();
            }
            System.out.println(currTask.getDescription());
            System.out.println(" ");
            this.taskManager.getRemainingTasks();
            System.out.println("_____________________________________________");
            System.out.println("_____________________________________________");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format to mark task should be \nmark <task ID>");
            System.out.println("_____________________________________________");
        } catch (InvalidIDException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("ID provided is invalid.");
            System.out.println("_____________________________________________");
        }
    }
}
