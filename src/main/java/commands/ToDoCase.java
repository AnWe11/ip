package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.TaskManager;
import tasks.ToDo;
import utility.StringChecker;

import java.io.IOException;

/**
 * Adds a todo task.
 */
public class ToDoCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;
    private Data data;

    public ToDoCase(String input, TaskManager taskManager, Data data) {
        this.taskManager = taskManager;
        this.input = input;
        this.data = data;
    }

    /**
     * Adds a todo task.
     * If task description is empty, throw exception.
     * @throws InvalidCommandException If task description is empty.
     */
    @Override
    public String action() throws InvalidCommandException {
        String taskDescription;
        String responseString;
        try {
            taskDescription = StringChecker.checkString(input);
            ToDo todoTask = new ToDo(taskDescription);
            responseString = taskManager.addTask(todoTask);
            data.saveData(taskManager);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            responseString = "Input format for todo tasks should be\ntodo <Task Description>";
        } catch (IOException e) {
            responseString = "Unable to save todo task: " + e.getMessage();
        }
        return responseString;
    }
}
