package commands;

import exceptions.InvalidCommandException;
import storage.Data;
import tasks.Events;
import tasks.TaskManager;
import utility.StringChecker;

import java.io.IOException;

/**
 * Adds an event task
 */
public class EventsCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;
    private Data data;

    public EventsCase(String input, TaskManager taskManager, Data data) {
        this.taskManager = taskManager;
        this.input = input;
        this.data = data;
    }

    /**
     * Adds an event task.
     * If task description or from or to date are empty, throw exception
     * @throws InvalidCommandException If user did not include a task description or the from and to dates.
     */
    @Override
    public String action() throws InvalidCommandException {
        String responseString;
        String taskDescription;
        try {
            taskDescription = StringChecker.checkString(input);

            if (!(taskDescription.contains("/from") && taskDescription.contains("/to"))) {
                throw new InvalidCommandException("The schedule timing is missing! ._.");
            }

            String[] parts = taskDescription.split("/from", 2);
            taskDescription = parts[0];
            String[] fromToString = parts[1].split("/to", 2);
            String fromString = fromToString[0];
            String toString = fromToString[1];
            Events eventsTask = new Events(taskDescription, fromString, toString);
            responseString = taskManager.addTask(eventsTask);
            data.saveData(taskManager);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            responseString = "Input format for events tasks should be\nevent <Task Description> /from <from date> /to <to date>";
        } catch (IOException e) {
            System.out.println("Unable to save Events Task: " + e.getMessage());
            responseString = "Unable to save Events Task: " + e.getMessage();
        }
        return responseString;
    }

}
