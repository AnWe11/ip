package commands;
import exceptions.InvalidCommandException;
import exceptions.InvalidIDException;
import storage.Data;
import tasks.TaskManager;
import tasks.TasksDefault;

import java.io.IOException;

/**
 * Updates task's mark or unmark status
 */
public class MarkUnmarkCase implements DefaultCase {
    private String input;
    private CommandsParser.Keywords keyword;
    private TaskManager taskManager;
    private Data data;

    MarkUnmarkCase(String input, CommandsParser.Keywords keyword, TaskManager taskManager, Data data) {
        this.input = input;
        this.keyword = keyword;
        this.taskManager = taskManager;
        this.data = data;
    }

    /**
     * Updates task's mark or unmark status.
     * If taskID is not provided, throw exception.
     * @throws InvalidCommandException If taskID is not provided.
     */
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
            System.out.println("_____________________________________________");
            currTask = taskManager.getTask(taskID);
            if (this.keyword == CommandsParser.Keywords.MARK) {
                System.out.println("Nice work! I've marked this task as done:");
                currTask.markAsDone();
            } else {
                System.out.println("Ok , I've unmarked this task:");
                currTask.unmark();
            }
            data.saveData(this.taskManager);
            System.out.println(currTask.getDescription());
            System.out.println(" ");
            this.taskManager.getRemainingTasks();
            System.out.println("_____________________________________________");
        } catch (InvalidCommandException e) {
            System.out.println("_____________________________________________");
            System.out.println(e.getMessage());
            System.out.println("Input format to mark task should be \nmark <task ID>");
            System.out.println("_____________________________________________");
        } catch (InvalidIDException e) {
            System.out.println("_____________________________________________");
            System.out.println(e.getMessage());
            System.out.println("ID provided is invalid.");
            System.out.println("_____________________________________________");
        } catch (IOException e) {
            System.out.println("Something went wrong when trying to save the marked/unmarked tasks: " + e.getMessage());
        }
    }
}
