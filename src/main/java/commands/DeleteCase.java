package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.TaskManager;
import tasks.TasksDefault;

import java.io.IOException;

/**
 * Deletes a task.
 */
public class DeleteCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;
    private int taskID;
    private Data data;

    public DeleteCase(String input, TaskManager taskManager, Data data) {
        this.taskManager = taskManager;
        this.input = input;
        this.data = data;
    }

    /**
     * Deletes the specified task based on taskID.
     * If taskID is empty, throw exception.
     * @throws InvalidCommandException If there is no taskID provided on which task to delete.
     */
    @Override
    public void action() throws InvalidCommandException {
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("o.O You did not specify which task you would like to delete");
            }
            this.taskID = Integer.parseInt(input.substring(firstSpaceIndex + 1));
            taskManager.removeTask(this.taskID);
            data.saveData(taskManager);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format to delete task should be \ndelete <task ID>");
            System.out.println("_____________________________________________\n");
        } catch (IOException e) {
            System.out.println("Something went wrong when trying to save the deleted task: " + e.getMessage());
        }
    }
}
