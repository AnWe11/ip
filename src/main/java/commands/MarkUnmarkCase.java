package commands;
import exceptions.InvalidCommandException;
import exceptions.InvalidIDException;
import storage.Data;
import tasks.TaskManager;
import tasks.TasksDefault;

import java.io.IOException;

/**
 * Method call if command word is 'mark' or 'unmark'.
 * Mark or unmark the specified task and updates the taskManager class and Storage class.
 */
public class MarkUnmarkCase implements DefaultCase {
    private String input;
    private CommandsParser.Keywords keyword;
    private TaskManager taskManager;
    private Data data;

    /**
     * MarkUnmarkCase instance.
     * @param input taskID of the task the user wants to mark or unmark.
     * @param keyword command word, whether it is 'mark' or 'unmark'.
     * @param taskManager TaskManager class instance.
     * @param data Storage class instance.
     */
    MarkUnmarkCase(String input, CommandsParser.Keywords keyword, TaskManager taskManager, Data data) {
        this.input = input;
        this.keyword = keyword;
        this.taskManager = taskManager;
        this.data = data;
    }

    /**
     * Retrieves the taskID of the task to mark or unmark.
     * Checks if the taskID is provided and is valid.
     * If the command word is 'mark', mark the specified task, else, unmark it.
     * Updates the TaskManager class and Storage class on the mark/unmark state of the specified task.
     * @throws InvalidCommandException
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
