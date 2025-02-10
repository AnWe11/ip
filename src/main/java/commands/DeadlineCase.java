package commands;

import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import storage.Data;
import tasks.Deadlines;
import tasks.TaskManager;
import utility.StringChecker;

import java.io.IOException;

/**
 * Adds a deadline task.
 */
public class DeadlineCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;
    private Data data;

    public DeadlineCase(String input, TaskManager taskManager, Data data) {
        this.taskManager = taskManager;
        this.input = input;
        this.data = data;
    }

    /**
     * Adds a deadline task.
     * If task description or date is empty, throw exception.
     * @throws InvalidCommandException If task description is empty.
     */
    @Override
    public String action() throws InvalidCommandException {
        String responseString;
        String taskDescription;
        try {
            taskDescription = StringChecker.checkString(input);
            if (!taskDescription.contains("/by")) {
                throw new InvalidCommandException("Oops! You did not specify a deadline date D:");
            }

            String[] parts = taskDescription.split("/by", 2);
            taskDescription = parts[0];
            String deadlineString = parts[1];
            Deadlines deadlineTask = new Deadlines(taskDescription, deadlineString);
            responseString = taskManager.addTask(deadlineTask);
            data.saveData(taskManager);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            responseString = "Input format for deadline tasks should be\ndeadline <Task Description> /by <deadline date>";
        } catch (IOException e) {
            System.out.println("Unable to save deadline Task: " + e.getMessage());
            responseString = "Unable to save deadline Task: " + e.getMessage();
        } catch (InvalidDateException e) {
            System.out.println("Invalid date / date format " + e.getMessage());
            responseString = "Invalid date / date format " + e.getMessage();
        }
        return responseString;
    }
}
