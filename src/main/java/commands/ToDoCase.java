package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.TaskManager;
import tasks.ToDo;

import java.io.IOException;

/**
 * Method call if command word is 'todo', creates a todo task instance.
 */
public class ToDoCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;
    private Data data;

    /**
     * ToDoCase instance.
     * @param input input String containing only task description.
     * @param taskManager TaskManager class instance.
     * @param data Storage class instance.
     */
    public ToDoCase(String input, TaskManager taskManager, Data data) {
        this.taskManager = taskManager;
        this.input = input;
        this.data = data;
    }

    /**
     * Splits the input String to retrieve the task description.
     * Checks if the input string is valid.
     * Creates a todo task and update it in the TaskManager class and in the Storage class.
     * @throws InvalidCommandException If task description is empty
     */
    @Override
    public void action() throws InvalidCommandException {
        String taskDescription;
        try {
            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("Oops! The task description of todo cannot be empty :(");
            }

            taskDescription = input.substring(firstSpaceIndex + 1);
            ToDo todoTask = new ToDo(taskDescription);
            taskManager.addTask(todoTask);
            data.saveData(taskManager);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for todo tasks should be\ntodo <Task Description>");
            System.out.println("_____________________________________________");
            taskDescription = "";
        } catch (IOException e) {
            System.out.println("Unable to save todo task: " + e.getMessage());
        }
    }
}
