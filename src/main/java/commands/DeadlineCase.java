package commands;

import exceptions.InvalidCommandException;
import tasks.Deadlines;
import tasks.TaskManager;

public class DeadlineCase implements DefaultCase {
    private TaskManager taskManager;
    private String input;

    public DeadlineCase(String input, TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = input;
    }

    @Override
    public void action() throws InvalidCommandException {
        String taskDescription;
        try {

            int firstSpaceIndex = input.indexOf(" ");
            //Return everything after first space or empty string if no space
            if ((firstSpaceIndex == -1) || firstSpaceIndex == input.length() - 1) {
                throw new InvalidCommandException("Luigi is sad because there is no task description )':");
            }
            taskDescription = input.substring(firstSpaceIndex + 1);

            if (!taskDescription.contains("/by")) {
                throw new InvalidCommandException("Oops! You did not specify a deadline date D:");
            }

            String[] parts = taskDescription.split("/by", 2);
            taskDescription = parts[0];
            String deadlineString = parts[1];
            Deadlines deadlineTask = new Deadlines(taskDescription, deadlineString);
            taskManager.addTask(deadlineTask);
            System.out.println("_____________________________________________\n" + "Sure thing! I've added this task: ");
            System.out.println(deadlineTask.getDescription());
            System.out.println("You currently have " + this.taskManager.getTotalTasks() + " task(s) in the list.");
            System.out.println("_____________________________________________");
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            System.out.println("_____________________________________________");
            System.out.println("Input format for deadline tasks should be\ndeadline <Task Description> /by <deadline date>");
            System.out.println("_____________________________________________");
            taskDescription = "";
        }
    }
}
