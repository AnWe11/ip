package CommandsPackage;
import Exceptions.InvalidCommandException;
import TaskPackage.TaskManager;
import TaskPackage.TasksDefault;

import java.security.Key;

public class MarkUnmarkCase implements DefaultCase {
    private String input;
    private CommandsParser.Keywords keyword;
    private int taskID;
    private TaskManager taskManager;
    private TasksDefault currTask;

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

            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            if (this.taskID >= 0 && this.taskID <= taskManager.getTotalTasks()) {
                this.currTask = taskManager.getTask(this.taskID);
            } else {
                throw new NumberFormatException("Invalid Task ID");
            }

            this.currTask = taskManager.getTask(this.taskID);
            if (this.keyword == CommandsParser.Keywords.MARK) {
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
        } catch (NumberFormatException e) {
            System.out.println("Invalid taskID format.");
        }
    }
}
